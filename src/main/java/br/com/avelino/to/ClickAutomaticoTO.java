package br.com.avelino.to;

public class ClickAutomaticoTO {
	
	private String identificador;

	private Integer qtdRepetir;
	
	private Integer eixoX;
	
	private Integer eixoY;
	
	private String tecla;
	
	private Integer milessegundos;
	
	private Integer itemLista;
	
	private Integer tamanhoLista;
	
	private String descricao;
	
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

	public Integer getQtdRepetir() {
		return qtdRepetir;
	}
	public ClickAutomaticoTO qtdRepetir(Integer qtdRepetir) {
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

	public String getTecla() {
		return tecla;
	}

	public ClickAutomaticoTO tecla(String tecla) {
		this.tecla = tecla;
		return this;
	}

	public String getDescricao() {
		return descricao;
	}

	public ClickAutomaticoTO descricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	@Override
	public String toString() {
		return new StringBuilder(identificador).append(";")
				.append(qtdRepetir).append(";")
				.append(eixoX).append(";")
				.append(eixoY).append(";")
				.append(tecla).append(";")
				.append(milessegundos).append(";")
				.append(itemLista).append(";")
				.append(tamanhoLista).append(";")
				.append(descricao)
				.toString();
	}
}
