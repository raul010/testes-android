package br.com.codifico.model;

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
