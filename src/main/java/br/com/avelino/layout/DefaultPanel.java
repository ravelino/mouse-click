package br.com.avelino.layout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import br.com.avelino.core.ClickAutomaticoObserver;
import br.com.avelino.core.MouseObserver;
import br.com.avelino.core.TeclaObserver;
import br.com.avelino.to.ClickAutomaticoTO;

public class DefaultPanel extends MouseClickPanel {

private static final long serialVersionUID = 1302762546797380758L;
	
	
	private static final int MARGEM_ESQUERDA_LABEL = 10;
	private static final int MARGEM_ESQUERDA_TEXT = 120;
	private static final int WIDTH_LABEL = 110;
	private static final int HEIGHT_LABEL = 30;
	
	
	private static final String LABEL_COMECAR = "Começar";
	private static final String LABEL_PARAR = "Parar";
	private static final String LABEL_ATUAL_POS = "";
	
	
	
	private static JLabel labelPosAtual = new JLabel(LABEL_ATUAL_POS);
	
	private JButton buttonStartStop;
	
	private ClickAutomaticoObserver clickAutomaticoObserver;
	
	public DefaultPanel() {
		super.setLayout(null);
		initEixoX();
		initEixoY();
		initRepetir();
		initMilessegundos();
		initPosAtual();
		initButtonStartStop();
		
		initLabelCaptureF12();
		
		addEventMouseListner(this);
		addEventButton();
		
		try {
			GlobalScreen.registerNativeHook();
			//GlobalScreen.getInstance().addNativeKeyListener(new TeclaObserver(this));
			GlobalScreen.addNativeKeyListener(new TeclaObserver(this));
		} catch (NativeHookException ex) {
			System.err.println(ex.getMessage());
			System.exit(1);
		}
		
		super.setVisible(true);
		super.setSize(700, 500);
	}

	private void addEventButton() {
		buttonStartStop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				
				String label = btn.getText();
				
				if (LABEL_PARAR.equals(label)) {
					btn.setText(LABEL_COMECAR);
					clickAutomaticoObserver.getTimer().stop();
				} else {
					btn.setText(LABEL_PARAR);
					
					ClickAutomaticoTO clickAutomaticoTO = 
							new ClickAutomaticoTO(valueRepetir.getText(),valueX.getText(),
									valueY.getText(), valueMilli.getText());
					
					actionStart(clickAutomaticoTO);
				}
			}
		});
	}

	private void addEventMouseListner(MouseClickPanel panel) {
		
		MouseObserver mo = new MouseObserver(panel);

		mo.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
            	NumberFormat nf = NumberFormat.getInstance();
                nf.setMaximumFractionDigits(0);
            	StringBuilder sb = new StringBuilder("Eixo X = ");
            	sb.append(nf.format(e.getPoint().getX()));
            	sb.append(" || Eixo Y = ");
            	sb.append(nf.format(e.getPoint().getY()));
            	labelPosAtual.setText(sb.toString());
            }

            public void mouseDragged(MouseEvent e) {}
        });

		mo.start();
	}
	
	private void actionStart(ClickAutomaticoTO clickAutomaticoTO) {
		if (clickAutomaticoTO.getQtdRepetir() > 0) {
			clickAutomaticoObserver = new ClickAutomaticoObserver(this, clickAutomaticoTO);
			clickAutomaticoObserver.getTimer().start();
		}
	}
	
	private void initButtonStartStop() {
		buttonStartStop = new JButton("Começar");
		buttonStartStop.setLocation(60, 143);
		buttonStartStop.setSize(WIDTH_TEXT, HEIGHT_TEXT);
		//this.getContentPane().add(buttonStartStop);
		this.add(buttonStartStop);
	}

	private void initPosAtual() {
		labelPosAtual.setLocation(MARGEM_ESQUERDA_LABEL, 180);
		labelPosAtual.setSize(350, HEIGHT_LABEL);
		//this.getContentPane().add(labelPosAtual);
		this.add(labelPosAtual);
	}

	private void initMilessegundos() {
		JLabel labelMilesec = new JLabel("Milissegundos");
		labelMilesec.setLocation(MARGEM_ESQUERDA_LABEL, 100);
		labelMilesec.setSize(WIDTH_LABEL, HEIGHT_LABEL);
		//this.getContentPane().add(labelMilesec);
		this.add(labelMilesec);
		
		valueMilli.setLocation(MARGEM_ESQUERDA_TEXT, 103);
		valueMilli.setSize(WIDTH_TEXT, HEIGHT_TEXT);
		this.add(valueMilli);
	}
	
	private void initLabelCaptureF12() {
		JLabel labelF12 = new JLabel("Aperte F12 para gravar a posição do mouse.");
		labelF12.setLocation(MARGEM_ESQUERDA_LABEL, 240);
		labelF12.setSize(350, HEIGHT_LABEL);
		labelF12.setForeground (Color.GREEN);
		
		//labelF12.setBackground(Color.GREEN);
		labelF12.setOpaque(true);
		this.add(labelF12);
	}

	private void initRepetir() {
		JLabel labelRepetir = new JLabel("Qtd. Repetir");
		labelRepetir.setLocation(MARGEM_ESQUERDA_LABEL, 70);
		labelRepetir.setSize(WIDTH_LABEL, HEIGHT_LABEL);
		this.add(labelRepetir);
		
		valueRepetir.setLocation(MARGEM_ESQUERDA_TEXT, 73);
		valueRepetir.setSize(WIDTH_TEXT, HEIGHT_TEXT);
		this.add(valueRepetir);
	}

	private void initEixoY() {
		JLabel labelY = new JLabel("Eixo Y");
		labelY.setLocation(MARGEM_ESQUERDA_LABEL,40);
		labelY.setSize(WIDTH_LABEL, HEIGHT_LABEL);
		this.add(labelY);
		
		valueY.setLocation(MARGEM_ESQUERDA_TEXT, 43);
		valueY.setSize(WIDTH_TEXT, HEIGHT_TEXT);
		this.add(valueY);
	}

	private void initEixoX() {
		JLabel labelX = new JLabel("Eixo X");
		labelX.setLocation(MARGEM_ESQUERDA_LABEL, 10);
		labelX.setSize(WIDTH_LABEL, HEIGHT_LABEL);
		this.add(labelX);
		
		valueX.setLocation(MARGEM_ESQUERDA_TEXT, 13);
		valueX.setSize(WIDTH_TEXT, HEIGHT_TEXT);
		this.add(valueX);
	}
}
