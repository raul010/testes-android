package br.com.programacaocinema.view;

import br.com.codifico.model.apresentacao.FilmeCartaz;

public class FilmeCartazView extends FilmeCartaz {
	@Override
	public String toString() {
		return this.getNome() + "\n" + this.getDiaSemana() + "\n" + this.getHorarios();
	}
}
