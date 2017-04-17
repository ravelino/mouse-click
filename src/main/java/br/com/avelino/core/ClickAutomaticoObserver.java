package br.com.avelino.core;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.Timer;

import br.com.avelino.layout.MouseClickPanel;
import br.com.avelino.to.ClickAutomaticoTO;

public class ClickAutomaticoObserver {

	private Timer timer;
	
	private static final String LABEL_COMECAR = "Começar";
	
	public ClickAutomaticoObserver (final MouseClickPanel panel, final ClickAutomaticoTO caTO) {
		
		
		timer = new Timer(caTO.getMilessegundos(), new ActionListener() {

			public synchronized void actionPerformed(ActionEvent e) {
				
				try {
					
					if (caTO.getQtdRepetir() > 0) {
						Robot r = new Robot();
						r.mouseMove(caTO.getEixoX(), caTO.getEixoY());
						r.mousePress(InputEvent.BUTTON1_MASK);
						r.mouseRelease(InputEvent.BUTTON1_MASK);
					} else {
						panel.getButtonStartStop().setText(LABEL_COMECAR);
						timer.stop();
					}
					
					caTO.setQtdRepetir(caTO.getQtdRepetir() -1);
					
				} catch (AWTException e1) {
					e1.printStackTrace();
				}

			}
		});
	}

	public Timer getTimer() {
		return timer;
	}
}
