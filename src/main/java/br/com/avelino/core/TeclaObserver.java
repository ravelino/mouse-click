package br.com.avelino.core;

import javax.swing.JOptionPane;

import org.apache.commons.lang.math.NumberUtils;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import br.com.avelino.layout.IMouseClickDefaultPanel;

public class TeclaObserver implements NativeKeyListener {
	
	private IMouseClickDefaultPanel panel;
	
	public TeclaObserver(IMouseClickDefaultPanel panel) {
		this.panel = panel;
	}
	

	public void nativeKeyPressed(NativeKeyEvent e) {
		
		
		if (NativeKeyEvent.VC_LEFT == e.getKeyCode()) {
			
			panel.timersStop();
				
			final Integer mousePositionX = MousePosition.getEscutaMouse().getPositionX();
			final Integer mousePositionY = MousePosition.getEscutaMouse().getPositionY();
			
			final String mili = JOptionPane.showInputDialog("Defina o intervalo ddo click em milissegundos.");
			
			if (NumberUtils.isDigits(mili)) {
				panel.getTableModel().addRow(new Object [] {mousePositionX, mousePositionY, mili});
			} else {
				JOptionPane.showMessageDialog(null, "Digite apenas números");
			}
			
		} else if (NativeKeyEvent.VC_UP == e.getKeyCode()) {
			panel.timersStop();
			panel.timersStart(Boolean.TRUE);
		} else if (NativeKeyEvent.VC_RIGHT == e.getKeyCode()) {
			panel.timersStop();
		} 
	}

	public void nativeKeyReleased(NativeKeyEvent e) {}

	public void nativeKeyTyped(NativeKeyEvent e) {}
}
