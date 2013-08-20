package br.com.codifico.model;

import br.com.codifico.util.enums.Cidade;
import br.com.codifico.util.enums.Estado;


public class Endereco {
	private Cidade cidade;
	
	private Estado estado;
	
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
