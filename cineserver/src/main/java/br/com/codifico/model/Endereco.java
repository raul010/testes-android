package br.com.codifico.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

import br.com.codifico.util.enums.Cidade;
import br.com.codifico.util.enums.Estado;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Endereco {
	@Enumerated(EnumType.STRING)
	private Cidade cidade;
	
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
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="endereco_seq")
	@SequenceGenerator(name="endereco_seq", sequenceName="endereco_seq")
	private int id;
}
