package br.com.avelino.layout;

import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JTextField;

public class MouseClickPanel extends Panel {
	
	protected static final int WIDTH_TEXT = 100;
	protected static final int HEIGHT_TEXT = 22;

	private static final long serialVersionUID = -4644599627227048051L;
	
	protected JTextField valueX = new JTextField();
	protected JTextField valueY = new JTextField();
	protected JTextField valueRepetir = new JTextField();
	protected JTextField valueMilli = new JTextField();
	
	protected JButton buttonStartStop = new JButton();
	
	public JTextField getValueX() {
		return valueX;
	}
	
	public void setValueX(Integer valueX) {
		this.valueX.setText(String.valueOf(valueX));
	}
	
	public JTextField getValueY() {
		return valueY;
	}
	
	public void setValueY(Integer valueY) {
		this.valueY.setText(String.valueOf(valueY));
	}
	
	public JTextField getValueRepetir() {
		return valueRepetir;
	}
	
	public void setValueRepetir(Integer valueRepetir) {
		this.valueRepetir.setText(String.valueOf(valueRepetir));
	}
	
	public JTextField getValueMilli() {
		return valueMilli;
	}
	
	public void setValueMilli(Integer valueMilli) {
		this.valueMilli.setText(String.valueOf(valueMilli));
	}
	
	public JButton getButtonStartStop() {
		return buttonStartStop;
	}
}
