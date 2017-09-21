package br.com.avelino.layout;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.avelino.core.FileHelper;
import br.com.avelino.db.MouseRegisterDao;
import br.com.avelino.to.ClickAutomaticoTO;

public class MouseClickFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
//	private static final String LAYOUT_PADRAO = "Padrão";
//	private static final String LAYOUT_NARUTO = "Naruto";
	private MouseClickFrame mouseClickFrame = this;
	private JMenuBar menuBar;
	
	private ConfiguracaoInternalLayer configLayer = new ConfiguracaoInternalLayer(mouseClickFrame);
	
	private Panel defaultPanel = new DefaultPanel();
	
	private MouseRegisterDao mouseRegisterDao = new MouseRegisterDao();
	//private Panel naruto = new NarutoUltimatePanel();
	
	public MouseClickFrame () {
		
		super.setTitle("Mouse Click - By Ravelino");
		super.setSize(700, 500);
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createMenu();
		/*JRadioButtonMenuItem radioPadrao = new JRadioButtonMenuItem(LAYOUT_PADRAO, Boolean.TRUE);
		JRadioButtonMenuItem radioNaruto = new JRadioButtonMenuItem(LAYOUT_NARUTO);
		
		radioPadrao.addActionListener(trocarLayout(this));
        radioNaruto.addActionListener(trocarLayout(this));*/
		
//		ButtonGroup bg = new ButtonGroup();
        /*bg.add(radioPadrao);
        bg.add(radioNaruto);
        
        layout.add(radioPadrao);
        layout.add(radioNaruto);*/
		
		super.add(defaultPanel);
		//super.add(naruto);
		super.setVisible(true);
	}
	
	private void createMenu() {
		menuBar = new JMenuBar();
		JMenu options = new JMenu("Opções");
		//JMenu layout = new JMenu("Laout");
		JMenuItem about = new JMenuItem("Sobre");
		JMenuItem dbImport = new JMenuItem("Importar DataBase");
		JMenuItem dbExport = new JMenuItem("Exportar DataBase");
		JMenuItem menuConfig = new JMenuItem("Configurações");
		dbExportAddActionListner(dbExport);
		dbImportAddActionListner(dbImport);
		
		menuConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//configLayer.show();
				defaultPanel.setEnabled(false);
				configLayer.setVisible(true);
				System.out.println("Chegou");
			}
		});
		
		options.add(dbImport);
		options.add(new JSeparator());
		options.add(dbExport);
		options.add(new JSeparator());
		options.add(menuConfig);
		options.add(new JSeparator());
		options.add(about);
		
		menuBar.add(options);
		
		super.setJMenuBar(menuBar);
	}

	private void dbImportAddActionListner(JMenuItem dbImport) {
		dbImport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
				fc.setFileFilter(filter);
				fc.setMultiSelectionEnabled(false);
				fc.setAcceptAllFileFilterUsed(false);
		        int returnVal = fc.showOpenDialog(MouseClickFrame.this);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            List<ClickAutomaticoTO> list = FileHelper.readFile(file);
		            mouseRegisterDao.insert(list);
		            JOptionPane.showMessageDialog(mouseClickFrame, "Importação realizada com sucesso!!!");
		        }
			}
		});
	}

	private void dbExportAddActionListner(JMenuItem dbExport) {
		dbExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(MouseClickFrame.this);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            FileHelper.writeFile(file.getAbsolutePath(), mouseRegisterDao.findAll());
		            JOptionPane.showMessageDialog(mouseClickFrame, "Exportação realizada com sucesso!!!");
		        }
			}
		});
	}

	/*private ActionListener trocarLayout(final MouseClickFrame frame) {
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				JRadioButtonMenuItem radio = (JRadioButtonMenuItem) e.getSource();
				
				if (LAYOUT_PADRAO.equals(radio.getText())) {
					frame.getNaruto().setVisible(Boolean.FALSE);
					frame.getDefaultPanel().setVisible(Boolean.TRUE);
				} else {
					frame.getDefaultPanel().setVisible(Boolean.FALSE);
					frame.getNaruto().setVisible(Boolean.TRUE);
				}
			}
		};
	}*/

	public Panel getDefaultPanel() {
		return defaultPanel;
	}

	public Panel getNaruto() {
		return null;
	}
}
