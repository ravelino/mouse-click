package br.com.avelino.core;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import br.com.avelino.layout.MouseClickPanel;

public class TeclaObserver implements NativeKeyListener {
	
	private MouseClickPanel panel;
	
	public TeclaObserver(MouseClickPanel frame) {
		this.panel = frame;
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		
		if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals("F12")) {
			panel.setValueX(MousePosition.getEscutaMouse().getPositionX());
			panel.setValueY(MousePosition.getEscutaMouse().getPositionY());
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {}

	public void nativeKeyTyped(NativeKeyEvent e) {}
}
