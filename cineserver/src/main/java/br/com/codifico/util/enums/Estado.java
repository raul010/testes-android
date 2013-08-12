package br.com.codifico.util.enums;

public enum Estado {
	SAO_PAULO("São Paulo");
	
	private String estado;
	
	private Estado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}
}
