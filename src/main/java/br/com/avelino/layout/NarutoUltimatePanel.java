package br.com.avelino.layout;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class NarutoUltimatePanel extends MouseClickPanel {

	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel model = new DefaultTableModel();

	public NarutoUltimatePanel() {
		
		this.setLayout(null);
		
		model.addColumn("First Name");
		model.addColumn("Last Name");
		model.addColumn("Sport");
		model.addColumn("# of Years");
		model.addColumn("Vegetarian");
		
		model.addRow(new Object [] {"Mary", "Campione", "Snowboarding", new Integer(5), new Boolean(false)});
		model.addRow(new Object [] {"Mary", "Campione", "Snowboarding", new Integer(5), new Boolean(false)});
		model.addRow(new Object [] {"Mary", "Campione", "Snowboarding", new Integer(5), new Boolean(false)});
		model.addRow(new Object [] {"Mary", "Campione", "Snowboarding", new Integer(5), new Boolean(false)});
		model.addRow(new Object [] {"Mary", "Campione", "Snowboarding", new Integer(5), new Boolean(false)});
		
		JTable table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(10, 300);
		scrollPane.setSize(600, 150);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		super.add(scrollPane);
		
		JButton botaoTeste = new JButton("Teste");
		botaoTeste.setLocation(350, 10);
		botaoTeste.setSize(WIDTH_TEXT, HEIGHT_TEXT);
		botaoTeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				MouseClickLayout frame = (MouseClickLayout) SwingUtilities.getRoot(component);
		        frame.getModel().addRow(new Object [] {"Rogerio", "Campione", "Snowboarding", new Integer(5), new Boolean(false)});
			}
		});
		
		super.add(botaoTeste);
		super.setSize(700, 500);
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
	
}
