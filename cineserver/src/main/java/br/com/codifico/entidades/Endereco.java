package br.com.codifico.entidades;

import br.com.codifico.util.enums.Cidade;
import br.com.codifico.util.enums.Estado;

public class Endereco {
	private Cidade cidade;
	private Estado estado;
	private String dados;
	
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
	public String getDados() {
		return dados;
	}
	public void setDados(String dados) {
		this.dados = dados;
	}
	
}
