package br.com.codifico.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.codifico.util.enums.Genero;

@Entity
public class Filme {
	@Id @GeneratedValue
	private int id;

	@Column 
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection
	@CollectionTable(name="genero_filme")
	@Column(length=30)
	private List<Genero> genero;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Genero> getGenero() {
		return genero;
	}

	public void setGenero(List<Genero> genero) {
		this.genero = genero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
