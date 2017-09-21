package br.com.avelino.layout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class ConfiguracaoInternalLayer extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel mouse = new JLabel("Capturar Click Mouse");
	private JLabel keybord = new JLabel("Capturar acão Teclado");
	private JLabel stop = new JLabel("Parar processos");
	private JLabel start = new JLabel("Começar processos");
	
	public ConfiguracaoInternalLayer(MouseClickFrame frame) {
		this.setLayout(null);
		this.setBounds(0,0,450,300);
		this.setSize(450, 300);
		this.setLocation(75, 75);
		this.setResizable(false);
		this.setMaximizable(false);
		this.setClosable(true);
		this.setTitle("Configurações");
		
		
		mouse.setLocation(22, 2);
		keybord.setLocation(2, 12);
		stop.setLocation(62, 22);
		start.setLocation(42, 32);
		
		
		this.add(mouse);
		this.add(keybord);
		this.add(stop);
		this.add(start);
		
		this.addInternalFrameListener(new InternalFrameListener() {
			
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {}
			
			@Override
			public void internalFrameIconified(InternalFrameEvent e) {}
			
			@Override
			public void internalFrameDeiconified(InternalFrameEvent e) {}
			
			@Override
			public void internalFrameDeactivated(InternalFrameEvent e) {}
			
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				frame.getDefaultPanel().setEnabled(true);
			}
			
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {}
			
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {}
		});
		
		frame.add(this);
	}
}
