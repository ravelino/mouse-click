package br.com.avelino.layout;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

public class MouseClickFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final String LAYOUT_PADRAO = "Padrão";
	private static final String LAYOUT_NARUTO = "Naruto";
	
	private JMenuBar menuBar;
	
	private Panel defaultPanel = new DefaultPanel();
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
		
		
		menuBar = new JMenuBar();
		JMenu options = new JMenu("Opções");
		JMenu layout = new JMenu("Laout");
		JMenuItem about = new JMenuItem("Sobre");
		//options.add(layout);
		options.add(new JSeparator());
		
		options.add(about);
		menuBar.add(options);
		
		
		JRadioButtonMenuItem radioPadrao = new JRadioButtonMenuItem(LAYOUT_PADRAO, Boolean.TRUE);
		JRadioButtonMenuItem radioNaruto = new JRadioButtonMenuItem(LAYOUT_NARUTO);
		
		radioPadrao.addActionListener(trocarLayout(this));
        radioNaruto.addActionListener(trocarLayout(this));
		
		ButtonGroup bg = new ButtonGroup();
        bg.add(radioPadrao);
        bg.add(radioNaruto);
        
        layout.add(radioPadrao);
        layout.add(radioNaruto);
		
		super.setJMenuBar(menuBar);
		super.add(defaultPanel);
		//super.add(naruto);
		super.setVisible(true);
	}

	private ActionListener trocarLayout(final MouseClickFrame frame) {
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
	}

	public Panel getDefaultPanel() {
		return defaultPanel;
	}

	public Panel getNaruto() {
		return null;
	}
}
