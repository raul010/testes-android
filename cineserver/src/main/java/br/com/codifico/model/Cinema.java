package br.com.codifico.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Cinema implements Serializable {
	@Transient
	private String nome;
	@Transient
	private Endereco endereco;
	@Transient
	private List<Filme> filmes;
	@Id @GeneratedValue
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
