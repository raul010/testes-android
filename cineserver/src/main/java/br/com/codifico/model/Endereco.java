package br.com.codifico.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.com.codifico.util.enums.Cidade;
import br.com.codifico.util.enums.Estado;

@Entity
public class Endereco {
	@Transient
	private Cidade cidade;
	@Transient
	private Estado estado;
	@Transient
	private String dados;
	@Id @GeneratedValue
	private int id;
	
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
