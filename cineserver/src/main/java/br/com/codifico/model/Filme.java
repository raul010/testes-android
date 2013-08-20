package br.com.codifico.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.codifico.util.enums.Genero;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Filme  {
	@Column
	private String nome;

	@Enumerated(EnumType.STRING)
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	@CollectionTable(name = "genero_filme")
	@Column(length = 30)
	private List<Genero> genero;
	
	@ManyToMany(mappedBy="filmes", targetEntity=Cinema.class)
	private List<Cinema> cinemas;

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
	public List<Cinema> getCinemas() {
		return cinemas;
	}
	public void setCinemas(List<Cinema> cinemas) {
		this.cinemas = cinemas;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="filme_seq")
	@SequenceGenerator(name="filme_seq", sequenceName="filme_seq")
	private int id;
}
