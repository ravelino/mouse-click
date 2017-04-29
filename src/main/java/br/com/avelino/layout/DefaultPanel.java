package br.com.avelino.layout;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.StringUtils;

import br.com.avelino.core.ClickAutomaticoTimer;
import br.com.avelino.core.HookConfigure;
import br.com.avelino.core.KeyListenerRemoverLetras;
import br.com.avelino.db.MouseRegisterDao;
import br.com.avelino.to.ClickAutomaticoTO;

public class DefaultPanel extends Panel implements IMouseClickDefaultPanel {

	private static final long serialVersionUID = 1302762546797380758L;

	private static final String LABEL_COMECAR = "Começar";
	private static final String LABEL_PARAR = "Parar";


	protected static final int WIDTH_TEXT = 100;
	protected static final int HEIGHT_TEXT = 22;
	protected static final int HEIGHT_LABEL = 30;
	
	private DefaultPanel panel = this;

	private DefaultTableModel tableModel = new DefaultTableModel() {
		private static final long serialVersionUID = -115961926037268221L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return (column == 2);
		}
	};
	
	private JTextField valueRepetir = new JTextField();

	private JButton buttonStartStop = new JButton();
	
	private JButton buttonSalvar = new JButton();
	
	private JButton buttonCarregar = new JButton();
	
	private JButton buttonClear = new JButton();
	
	private JButton buttonRemoverItem = new JButton();
	
	private List<ClickAutomaticoTimer> listClickAutomaticoTimer = new ArrayList<ClickAutomaticoTimer>();
	
	private String identificador;
	
	private MouseRegisterDao mouseRegisterDao = new MouseRegisterDao();

	public DefaultPanel() {

		this.setLayout(null);

		initTableModel();
		HookConfigure.initHooks(this);

		initButtonStartStop();
		initRepetir();
		initButtonClear();
		initButtonSalvar();
		initButtonCarregar();
		initButtonRemoverItem();
		addEventButtonStartStop();
		addEventButtonClear();
		addEventButtonSalvar();
		addEventButtonCarregar();
		addEventButtonRemoverItem();
		addEventRemoveLetras(valueRepetir);

		super.setSize(700, 500);
	}

	private void addEventButtonRemoverItem() {
		buttonRemoverItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final List<ClickAutomaticoTO> fileSaveList = mouseRegisterDao.findAll();
				
				if (fileSaveList.size() > 0) {
					final List<String> indentificadores = createListToSelect(fileSaveList);
				
					final String identificador = (String) JOptionPane.showInputDialog(panel, 
					        "Qual item deseja remover?",
					        "Selecione um item da lista",
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        indentificadores.toArray(), 
					        indentificadores.get(0));
					
					if (StringUtils.isNotEmpty(identificador)) {
						mouseRegisterDao.deleteByIdentificador(identificador);
					}
					
				} else {
					JOptionPane.showMessageDialog(panel, "Você não tem itens na lista");
				}
			}
		});
	}

	private void initTableModel() {
		tableModel.addColumn("Posicão X");
		tableModel.addColumn("Posição Y");
		tableModel.addColumn("Milissegundos");
		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(10, 60);
		scrollPane.setSize(600, 320);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		super.add(scrollPane);
	}
	
	private void initButtonRemoverItem() {
		buttonRemoverItem = new JButton("Remover item salvo");
		buttonRemoverItem.setLocation(460, 400);
		buttonRemoverItem.setSize(180, HEIGHT_TEXT);
		this.add(buttonRemoverItem);
	}
	
	
	private void initButtonSalvar() {
		buttonSalvar = new JButton("Salvar lista");
		buttonSalvar.setLocation(260, 400);
		buttonSalvar.setSize(180, HEIGHT_TEXT);
		this.add(buttonSalvar);
	}
	
	private void initButtonCarregar() {
		buttonCarregar = new JButton("Carregar lista salva");
		buttonCarregar.setLocation(50, 400);
		buttonCarregar.setSize(180, HEIGHT_TEXT);
		this.add(buttonCarregar);
	}
	
	private void initButtonClear() {
		buttonClear = new JButton("Limpar lista");
		buttonClear.setLocation(250, 10);
		buttonClear.setSize(180, HEIGHT_TEXT);
		this.add(buttonClear);
	}
	
	private void addEventButtonCarregar() {
		buttonCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				final List<ClickAutomaticoTO> fileSaveList = mouseRegisterDao.findAll();
				
				if (fileSaveList.size() > 0) {
					final List<String> indentificadores = createListToSelect(fileSaveList);
					
					buttonClear.doClick();
					
					identificador = (String) JOptionPane.showInputDialog(panel, 
					        "Qual item deseja carregar?",
					        "Selecione um item da lista",
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        indentificadores.toArray(), 
					        indentificadores.get(0));
					
					populateTableModel(fileSaveList);
				} else {
					JOptionPane.showMessageDialog(panel, "Você não tem itens na lista");
				}
				
				
			}
		});
	}
	
	private void populateTableModel(List<ClickAutomaticoTO> fileSaveList) {
		
		if (StringUtils.isNotEmpty(identificador)) {
			
			final List<ClickAutomaticoTO> listIdentica = mouseRegisterDao.findByIdentificador(identificador);
			
			listIdentica.forEach(item -> {
				tableModel.addRow(new Object [] {item.getEixoX(), item.getEixoY(), item.getMilessegundos()});
			});
			
			valueRepetir.setText(String.valueOf(listIdentica.get(0).getQtdRepetir()));
		}
	}

	private List<String> createListToSelect(List<ClickAutomaticoTO> fileSaveList) {
		final List<String> identificadores = new ArrayList<>();
		
		fileSaveList.forEach(item -> {
			if (!identificadores.contains(item.getIdentificador())) {
				identificadores.add(item.getIdentificador());
			}
		});
		
		return identificadores;
	}
	
	private void addEventButtonClear() {
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				identificador = null;
				valueRepetir.setText("");
				while (tableModel.getRowCount() > 0) {
					tableModel.removeRow(0);
				}
			}
		});
	}
	
	private void addEventButtonSalvar() {
		buttonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tableModel.getRowCount() > 0) {
					final String identificador = JOptionPane.showInputDialog("Qual o nome que gostaria de salvar a lista?");
					if (StringUtils.isNotEmpty(identificador)) {
						final List<ClickAutomaticoTO> listClickAutomaticoTO = createListClickAutomaticoTO();
						listClickAutomaticoTO.forEach(item -> item.identificador(identificador));
						mouseRegisterDao.insert(listClickAutomaticoTO);
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Você não tem itens na lista");
				}
			}
		});
	}

	private void addEventButtonStartStop() {
		buttonStartStop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final Integer linhas = tableModel.getRowCount();
				
				if (linhas > 0) {
					
					/*if (StringUtils.isNotEmpty(identificador)) {
						populateTableModel(mouseRegisterDao.findByIdentificador(identificador));
					}*/

					String label = buttonStartStop.getText();

					if (LABEL_PARAR.equals(label)) {
						timersStop();
					} else {
						buttonStartStop.setText(LABEL_PARAR);
						final List<ClickAutomaticoTO> listClickAutomaticoTO = createListClickAutomaticoTO();
						listClickAutomaticoTimer.clear();
						listClickAutomaticoTO.forEach(item-> listClickAutomaticoTimer.add(new ClickAutomaticoTimer(item, panel)));
						timersStart(Boolean.FALSE);
					}
				}
			}
		});
	}
	
	private List<ClickAutomaticoTO> createListClickAutomaticoTO() {
		final Integer linhas = tableModel.getRowCount();
		
		final List<ClickAutomaticoTO> listClickAutomaticoTO = new ArrayList<ClickAutomaticoTO>();
		
		for (int i = 0; i < linhas; i++) {
			
			final Integer eixoX = Integer.valueOf(tableModel.getValueAt(i, 0).toString());
			final Integer eixoY = Integer.valueOf(tableModel.getValueAt(i, 1).toString());
			final Integer milli = Integer.valueOf(tableModel.getValueAt(i, 2).toString());
			
			final Optional<String> optionalRepetir = Optional.of(valueRepetir.getText()).filter((valor)-> valor.length() > 0);
			final String rep = optionalRepetir.orElse("0");
			
			final ClickAutomaticoTO to = 
					ClickAutomaticoTO
						.builder()
						.eixoX(eixoX)
						.eixoY(eixoY)
						.qtdRepetir(Integer.valueOf(rep))
						.milessegundos(milli)
						.itemLista(i + 1)
						.tamanhoLista(linhas);
			
			listClickAutomaticoTO.add(to);
		}
		
		return listClickAutomaticoTO; 
	}

	public void timersStart(Boolean doClick) {
		if (doClick) {
			buttonStartStop.doClick();
		} else {
			if (tableModel.getRowCount() > 0) {
				listClickAutomaticoTimer.get(0).start(listClickAutomaticoTimer);
			}
		}	
	}

	public void timersStop() {
		buttonStartStop.setText(LABEL_COMECAR);
		listClickAutomaticoTimer.forEach(item -> item.stop());
	}

	private void initButtonStartStop() {
		buttonStartStop = new JButton("Começar");
		buttonStartStop.setLocation(460, 10);
		buttonStartStop.setSize(WIDTH_TEXT, HEIGHT_TEXT);
		this.add(buttonStartStop);
	}

	private void initRepetir() {
		JLabel labelRepetir = new JLabel("Repetir");
		labelRepetir.setLocation(10, 10);
		labelRepetir.setSize(80, HEIGHT_LABEL);
		this.add(labelRepetir);
		valueRepetir.setText("1");
		valueRepetir.setLocation(90, 13);
		valueRepetir.setSize(WIDTH_TEXT, HEIGHT_TEXT);
		this.add(valueRepetir);
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	private void addEventRemoveLetras(JTextField field) {
		field.addKeyListener(new KeyListenerRemoverLetras(field));
	}
	
	public void setLabelComecarButtonStartStop() {
		buttonStartStop.setText(LABEL_COMECAR);
	}
}
