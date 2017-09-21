package br.com.avelino.core;


import javax.swing.JOptionPane;

import org.apache.commons.lang.math.NumberUtils;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import br.com.avelino.layout.IMouseClickDefaultPanel;

import java.awt.event.KeyEvent;

public class TeclaObserver implements NativeKeyListener {
	
	private IMouseClickDefaultPanel panel;
	
	public TeclaObserver(IMouseClickDefaultPanel panel) {
		this.panel = panel;
	}
	

	public void nativeKeyPressed(NativeKeyEvent e) {
		
		final Integer mousePositionX = MousePosition.getEscutaMouse().getPositionX();
		final Integer mousePositionY = MousePosition.getEscutaMouse().getPositionY();
		if (NativeKeyEvent.VC_LEFT == e.getKeyCode()) {
			
			panel.timersStop();
				
			final String mili = JOptionPane.showInputDialog("Defina o intervalo do click em milissegundos.");
			
			if (NumberUtils.isDigits(mili)) {
				panel.getTableModel().addRow(new Object [] {mousePositionX, mousePositionY,"", mili,""});
			} else {
				JOptionPane.showMessageDialog(null, "Digite apenas números");
			}
			
		} else if (NativeKeyEvent.VC_UP == e.getKeyCode()) {
			panel.timersStop();
			panel.timersStart(Boolean.TRUE);
		} else if (NativeKeyEvent.VC_RIGHT == e.getKeyCode()) {
			panel.timersStop();
		} else if (NativeKeyEvent.VC_K == e.getKeyCode()) {
			panel.timersStop();
			
			String tecla = (String) JOptionPane.showInputDialog(null, 
			        "Qual Tecla deseja que seja pressionada?",
			        "Escolha a Tecla",
			        JOptionPane.QUESTION_MESSAGE, 
			        null, 
			        KeyBord.getArray(), 
			        KeyBord.F1.name());
			
			if (tecla != null) {
				final String mili = JOptionPane.showInputDialog("Defina o intervalo ddo click em milissegundos.");
				
				if (NumberUtils.isDigits(mili)) {
					panel.getTableModel().addRow(new Object [] {mousePositionX, mousePositionY, tecla, mili,""});
				} else {
					JOptionPane.showMessageDialog(null, "Digite apenas números");
				}
			}
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {}

	public void nativeKeyTyped(NativeKeyEvent e) {}
	
	public static enum KeyBord {
		
		F1(KeyEvent.VK_F1),
		F2(KeyEvent.VK_F2),
		F3(KeyEvent.VK_F3),
		F4(KeyEvent.VK_F4),
		F5(KeyEvent.VK_F5),
		F6(KeyEvent.VK_F6),
		F7(KeyEvent.VK_F7),
		F8(KeyEvent.VK_F8),
		F9(KeyEvent.VK_F9),
		F10(KeyEvent.VK_F10),
		F11(KeyEvent.VK_F11),
		F12(KeyEvent.VK_F12),
		ZERO(KeyEvent.VK_0),
		ONE(KeyEvent.VK_1),
		TWO(KeyEvent.VK_2),
		THREE(KeyEvent.VK_3),
		FOUR(KeyEvent.VK_4),
		FIVE(KeyEvent.VK_5),
		SIX(KeyEvent.VK_6),
		SEVEN(KeyEvent.VK_7),
		EIGHT(KeyEvent.VK_8),
		NINE(KeyEvent.VK_9),
		A(KeyEvent.VK_A),
		B(KeyEvent.VK_B),
		C(KeyEvent.VK_C),
		D(KeyEvent.VK_D),
		E(KeyEvent.VK_E),
		F(KeyEvent.VK_F),
		G(KeyEvent.VK_G),
		H(KeyEvent.VK_H),
		I(KeyEvent.VK_I),
		J(KeyEvent.VK_J),
		K(KeyEvent.VK_K),
		L(KeyEvent.VK_L),
		M(KeyEvent.VK_M),
		N(KeyEvent.VK_N),
		O(KeyEvent.VK_O),
		P(KeyEvent.VK_P),
		Q(KeyEvent.VK_Q),
		R(KeyEvent.VK_R),
		S(KeyEvent.VK_S),
		T(KeyEvent.VK_T),
		U(KeyEvent.VK_U),
		X(KeyEvent.VK_X),
		W(KeyEvent.VK_W),
		Y(KeyEvent.VK_Y),
		Z(KeyEvent.VK_Z),
		UP(KeyEvent.VK_UP),
		LEFT(KeyEvent.VK_LEFT),
		RIGHT(KeyEvent.VK_RIGHT),
		DOWN(KeyEvent.VK_DOWN),
		TAB(KeyEvent.VK_TAB),
		SHIFT_LEFT(KeyEvent.VK_SHIFT),
		SHIFT_RIGHT(KeyEvent.VK_SHIFT),
		CONTROL_LEFT(KeyEvent.VK_CONTROL),
		CONTROL_RIGHT(KeyEvent.VK_CONTROL),
		ENTER(KeyEvent.VK_ENTER),
		SPACE(KeyEvent.VK_SPACE);
		
		private int keyCode;
		
		KeyBord(int keyCode) {
			this.keyCode = keyCode;
		}
		
		public static String [] getArray() {
			String [] names = new String [values().length];
			int i = 0;
			for (KeyBord kb : values()) {
				names[i++] = kb.name();
			}
			return names;
		}
		
		public int getKeyCode() {
			return keyCode;
		}
	}
}
