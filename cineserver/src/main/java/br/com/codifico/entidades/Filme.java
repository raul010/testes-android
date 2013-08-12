package br.com.codifico.entidades;

import br.com.codifico.util.enums.Genero;

public class Filme {
	private String nome;
	private Genero genero;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
}
