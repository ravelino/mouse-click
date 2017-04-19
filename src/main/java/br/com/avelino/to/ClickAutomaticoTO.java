package br.com.avelino.to;

public class ClickAutomaticoTO {
	
	private String identificador;

	private Long qtdRepetir;
	
	private Integer eixoX;
	
	private Integer eixoY;
	
	private Integer milessegundos;
	
	private Integer itemLista;
	
	private Integer tamanhoLista;
	
	private ClickAutomaticoTO() {}
	
	public static ClickAutomaticoTO builder() {
		return new ClickAutomaticoTO();
	}
	
	public String getIdentificador() {
		return identificador;
	}

	public ClickAutomaticoTO identificador(String identificador) {
		this.identificador = identificador;
		return this;
	}

	public Long getQtdRepetir() {
		return qtdRepetir;
	}
	public ClickAutomaticoTO qtdRepetir(Long qtdRepetir) {
		this.qtdRepetir = qtdRepetir;
		return this;
	}
	
	public Integer getMilessegundos() {
		return milessegundos;
	}
	public ClickAutomaticoTO milessegundos(Integer milessegundos) {
		this.milessegundos = milessegundos;
		return this;
	}
	
	public Integer getEixoX() {
		return eixoX;
	}
	public ClickAutomaticoTO eixoX(Integer eixoX) {
		this.eixoX = eixoX;
		return this;
	}
	public Integer getEixoY() {
		return eixoY;
	}
	public ClickAutomaticoTO eixoY(Integer eixoY) {
		this.eixoY = eixoY;
		return this;
	}
	public Integer getItemLista() {
		return itemLista;
	}
	public ClickAutomaticoTO itemLista(Integer itemLista) {
		this.itemLista = itemLista;
		return this;
	}
	public Integer getTamanhoLista() {
		return tamanhoLista;
	}
	public ClickAutomaticoTO tamanhoLista(Integer tamanhoLista) {
		this.tamanhoLista = tamanhoLista;
		return this;
	}

	@Override
	public String toString() {
		return "ClickAutomaticoTO [identificador=" + identificador + ", qtdRepetir=" + qtdRepetir + ", eixoX=" + eixoX
				+ ", eixoY=" + eixoY + ", milessegundos=" + milessegundos + ", itemLista=" + itemLista
				+ ", tamanhoLista=" + tamanhoLista + "]";
	}
	
}
