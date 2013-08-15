package br.com.codifico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.codifico.util.enums.Cidade;
import br.com.codifico.util.enums.Estado;

@Entity
public class Endereco {
	@Enumerated(EnumType.STRING)
	private Cidade cidade;
	@Id @GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Column
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
