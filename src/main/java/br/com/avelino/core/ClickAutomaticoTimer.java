/**
 * 
 */
package br.com.avelino.core;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.List;

import javax.swing.Timer;

import br.com.avelino.layout.DefaultPanel;
import br.com.avelino.to.ClickAutomaticoTO;

/**
 * @author ravelino
 *
 */
public class ClickAutomaticoTimer implements ActionListener {
	
	private Timer timer;
	
	private ClickAutomaticoTO clickAutomaticoTO;
	
	private List<ClickAutomaticoTimer> listClickAutomaticoTimer;
	
	private DefaultPanel panel;
	
	
	public ClickAutomaticoTimer(ClickAutomaticoTO clickAutomaticoTO, DefaultPanel panel) {
		this.clickAutomaticoTO = clickAutomaticoTO;
		this.panel = panel;
		this.timer = new Timer(clickAutomaticoTO.getMilessegundos(), this);
	}
	
	
	public void start(List<ClickAutomaticoTimer> listClickAutomaticoTimer) {
		this.listClickAutomaticoTimer = listClickAutomaticoTimer;
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
    
	public synchronized void actionPerformed(ActionEvent ev) {
		Boolean restart = Boolean.FALSE;
		
		try {
			if (clickAutomaticoTO.getQtdRepetir() > 0) {
				final Robot r = new Robot();
				r.mouseMove(clickAutomaticoTO.getEixoX(), clickAutomaticoTO.getEixoY());
				r.mousePress(InputEvent.BUTTON1_MASK);
				r.mouseRelease(InputEvent.BUTTON1_MASK);
				clickAutomaticoTO.qtdRepetir(clickAutomaticoTO.getQtdRepetir() - 1);
				
				if (clickAutomaticoTO.getTamanhoLista() == clickAutomaticoTO.getItemLista()) {
					restart = clickAutomaticoTO.getTamanhoLista() == 1;
					listClickAutomaticoTimer.get(0).start(listClickAutomaticoTimer);
				} else {
					listClickAutomaticoTimer.get(clickAutomaticoTO.getItemLista()).start(listClickAutomaticoTimer);
				}
			} else {
				panel.setLabelComecarButtonStartStop();
			}
			
			if (!restart) {		
				timer.stop();
			}	
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
