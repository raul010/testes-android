package br.com.codifico.util.enums;

public enum Cidade {
	SAO_PAULO("São Paulo");

	private String estado;

	private Cidade(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}
}
