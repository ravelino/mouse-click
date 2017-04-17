package br.com.avelino.to;

public class ClickAutomaticoTO {

	private int qtdRepetir;
	private int eixoX; 
	private int eixoY;
	private int milessegundos;
	
	
	
	public ClickAutomaticoTO(String qtdRepetir, String eixoX, String eixoY,String milessegundos) {
		super();
		this.qtdRepetir = Integer.valueOf(qtdRepetir);
		this.eixoX = Integer.valueOf(eixoX);
		this.eixoY = Integer.valueOf(eixoY);
		this.milessegundos = Integer.valueOf(milessegundos);
	}
	public int getQtdRepetir() {
		return qtdRepetir;
	}
	public void setQtdRepetir(int qtdRepetir) {
		this.qtdRepetir = qtdRepetir;
	}
	public int getEixoX() {
		return eixoX;
	}
	public void setEixoX(int eixoX) {
		this.eixoX = eixoX;
	}
	public int getEixoY() {
		return eixoY;
	}
	public void setEixoY(int eixoY) {
		this.eixoY = eixoY;
	}
	public int getMilessegundos() {
		return milessegundos;
	}
	public void setMilessegundos(int milessegundos) {
		this.milessegundos = milessegundos;
	}
}
