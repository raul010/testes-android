package br.com.codifico.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Cinema {
	private String nome;
	private Endereco endereco;
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

}
