package br.com.codifico.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Repository;

@Repository
@Entity
public class Cinema {
	@Column(length=100)
	private String nome;
    
	@ManyToMany
	@JoinTable(name="join_cinema_endereco")
	private List<Endereco> enderecos;
	
	@ManyToMany
	@JoinTable(name="join_cinema_filme")
	private List<Filme> filmes;
	
	@Id @GeneratedValue
	private int id;
	
	public Cinema() {
		filmes = new ArrayList<Filme>();
		enderecos = new ArrayList<Endereco>();
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

	public List<Endereco> getEnderecos() {
		return Collections.unmodifiableList(enderecos);
	}

	public void addEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
	}
}
