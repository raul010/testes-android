package br.com.codifico.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Repository;

@Repository
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Cinema {
	@Column(length=100)
	private String nome;
    
	@OneToOne(cascade=CascadeType.PERSIST)
	private Endereco endereco;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="join_cinema_filme")
	private List<Filme> filmes;
	
	public Cinema() {
		filmes = new ArrayList<Filme>();
	}
	
	public void addFilme(Filme filme) {
		this.filmes.add(filme);
	}
	
	public List<Filme> getListaDeFilmes() {
		return Collections.unmodifiableList(filmes);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cinema_seq")
	@SequenceGenerator(name="cinema_seq", sequenceName="cinema_seq")
	private int id;
}
