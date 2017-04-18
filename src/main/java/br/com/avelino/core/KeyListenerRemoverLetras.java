package br.com.avelino.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
/**
 * 
 * @author ravelino
 *
 */
public class KeyListenerRemoverLetras implements KeyListener {
	
	private JTextField field;
	
	public KeyListenerRemoverLetras(JTextField field) {
		this.field = field;
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar() ;
        
        if (!(
        	  (c==KeyEvent.VK_BACK_SPACE) || 
        	  (c==KeyEvent.VK_DELETE) ||
        	  (c== KeyEvent.VK_ENTER) ||
        	  (c == KeyEvent.VK_TAB)  ||
        	  (Character.isDigit(c)))
        	) {
           
        	e.consume() ;
       }
	}

}
