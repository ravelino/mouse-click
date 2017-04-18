package br.com.avelino.core;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import br.com.avelino.layout.IMouseClickDefaultPanel;

public class TeclaObserver implements NativeKeyListener {
	
	private IMouseClickDefaultPanel panel;
	
	public TeclaObserver(IMouseClickDefaultPanel panel) {
		this.panel = panel;
	}
	

	public void nativeKeyPressed(NativeKeyEvent e) {
		if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals("G")) {
			final Integer mousePositionX = MousePosition.getEscutaMouse().getPositionX();
			final Integer mousePositionY = MousePosition.getEscutaMouse().getPositionY();
			panel.getTableModel().addRow(new Object [] {mousePositionX, mousePositionY});
		} else if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals("P")) {
			panel.timersStop();
		} else if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals("C")) {
			panel.timersStart();
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {}

	public void nativeKeyTyped(NativeKeyEvent e) {}
}
