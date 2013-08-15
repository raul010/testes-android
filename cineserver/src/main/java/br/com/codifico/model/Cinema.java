package br.com.codifico.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.stereotype.Repository;

@Repository
@Entity
public class Cinema {
	@Transient
	private String nome;
    
	@OneToOne
	private Endereco endereco;
	
	@ManyToMany
	@JoinTable(name="join_cinema_filme")
	private List<Filme> filmes;
	
	@Id @GeneratedValue
	private int id;
	
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

}
