package br.com.avelino.to;

public class ClickAutomaticoTO {
	
	private String identificador;

	private Integer qtdRepetir;
	
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

	@Override
	public String toString() {
		return "ClickAutomaticoTO [identificador=" + identificador + ", qtdRepetir=" + qtdRepetir + ", eixoX=" + eixoX
				+ ", eixoY=" + eixoY + ", milessegundos=" + milessegundos + ", itemLista=" + itemLista
				+ ", tamanhoLista=" + tamanhoLista + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eixoX == null) ? 0 : eixoX.hashCode());
		result = prime * result + ((eixoY == null) ? 0 : eixoY.hashCode());
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result + ((itemLista == null) ? 0 : itemLista.hashCode());
		result = prime * result + ((milessegundos == null) ? 0 : milessegundos.hashCode());
		result = prime * result + ((qtdRepetir == null) ? 0 : qtdRepetir.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClickAutomaticoTO other = (ClickAutomaticoTO) obj;
		if (eixoX == null) {
			if (other.eixoX != null)
				return false;
		} else if (!eixoX.equals(other.eixoX))
			return false;
		if (eixoY == null) {
			if (other.eixoY != null)
				return false;
		} else if (!eixoY.equals(other.eixoY))
			return false;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		if (itemLista == null) {
			if (other.itemLista != null)
				return false;
		} else if (!itemLista.equals(other.itemLista))
			return false;
		if (milessegundos == null) {
			if (other.milessegundos != null)
				return false;
		} else if (!milessegundos.equals(other.milessegundos))
			return false;
		if (qtdRepetir == null) {
			if (other.qtdRepetir != null)
				return false;
		} else if (!qtdRepetir.equals(other.qtdRepetir))
			return false;
		return true;
	}
	
}
