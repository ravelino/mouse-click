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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.avelino.core.ClickAutomaticoTimer;
import br.com.avelino.core.HookConfigure;
import br.com.avelino.core.KeyListenerRemoverLetras;
import br.com.avelino.to.ClickAutomaticoTO;

public class DefaultPanel extends Panel implements IMouseClickDefaultPanel {

	private static final long serialVersionUID = 1302762546797380758L;

	private static final String LABEL_COMECAR = "Começar";
	private static final String LABEL_PARAR = "Parar";


	protected static final int WIDTH_TEXT = 100;
	protected static final int HEIGHT_TEXT = 22;
	protected static final int HEIGHT_LABEL = 30;

	private DefaultTableModel tableModel = new DefaultTableModel();
	private JTextField valueRepetir = new JTextField();
	private JTextField valueMilli = new JTextField();

	protected JButton buttonStartStop = new JButton();
	
	protected JButton buttonClearTable = new JButton();

	private List<ClickAutomaticoTimer> listClickAutomaticoTimer = new ArrayList<ClickAutomaticoTimer>();

	public DefaultPanel() {

		this.setLayout(null);

		initTableModel();
		HookConfigure.initHooks(this);

		initButtonStartStop();
		initRepetir();
		initMilessegundos();
		initButtonClear();
		addEventButton();
		addEventButtonClear();
		addEventRemoveLetras(valueMilli);
		addEventRemoveLetras(valueRepetir);

		super.setSize(700, 500);
	}

	private void initTableModel() {
		tableModel.addColumn("Posicão X");
		tableModel.addColumn("Posição Y");
		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(10, 60);
		scrollPane.setSize(600, 150);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		super.add(scrollPane);
	}
	
	private void initButtonClear() {
		buttonClearTable = new JButton("Limpar lista");
		buttonClearTable.setLocation(460, 400);
		buttonClearTable.setSize(180, HEIGHT_TEXT);
		this.add(buttonClearTable);
	}
	
	private void addEventButtonClear() {
		buttonClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valueRepetir.setText("");
				valueMilli.setText("");
				while (tableModel.getRowCount() > 0) {
					tableModel.removeRow(0);
				}
			}
		});
	}

	private void addEventButton() {
		buttonStartStop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final Integer linhas = tableModel.getRowCount();
				
				if (linhas > 0) {
					String label = buttonStartStop.getText();

					if (LABEL_PARAR.equals(label)) {
						timersStop();
					} else {
						buttonStartStop.setText(LABEL_PARAR);

						for (int i = 0; i < linhas; i++) {
							final Integer eixoX = (Integer) tableModel.getValueAt(i, 0);
							final Integer eixoY = (Integer) tableModel.getValueAt(i, 1);
							
							final Optional<String> optionalRepetir = Optional.of(valueRepetir.getText()).filter((valor)-> valor.length() > 0);
							final Optional<String> optionalMilli = Optional.of(valueMilli.getText()).filter((valor)-> valor.length() > 0);
							final String rep = optionalRepetir.orElse("0");
							final String milli = optionalMilli.orElse("0");
							
							final ClickAutomaticoTO to = 
									ClickAutomaticoTO
										.builder()
										.eixoX(eixoX)
										.eixoY(eixoY)
										.qtdRepetir(Long.valueOf(rep))
										.milessegundos(Integer.valueOf(milli))
										.itemLista(i + 1)
										.tamanhoLista(linhas);
							
								listClickAutomaticoTimer.add(new ClickAutomaticoTimer(to));
						}
						
						timersStart();
					}
				}
			}
		});
	}

	public void timersStart() {
		buttonStartStop.doClick();
		if (tableModel.getRowCount() > 0) {
			listClickAutomaticoTimer.get(0).start(listClickAutomaticoTimer);
		}
	}

	public void timersStop() {
		buttonStartStop.setText(LABEL_COMECAR);
		for (ClickAutomaticoTimer timer : listClickAutomaticoTimer) {
			timer.stop();
		}
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

		valueRepetir.setLocation(90, 13);
		valueRepetir.setSize(WIDTH_TEXT, HEIGHT_TEXT);
		this.add(valueRepetir);
	}

	private void initMilessegundos() {
		JLabel labelMilesec = new JLabel("Milissegundos");
		labelMilesec.setLocation(220, 10);
		labelMilesec.setSize(110, HEIGHT_LABEL);
		this.add(labelMilesec);

		valueMilli.setLocation(340, 13);
		valueMilli.setSize(WIDTH_TEXT, HEIGHT_TEXT);
		this.add(valueMilli);
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	private void addEventRemoveLetras(JTextField field) {
		field.addKeyListener(new KeyListenerRemoverLetras(field));
	}
}
