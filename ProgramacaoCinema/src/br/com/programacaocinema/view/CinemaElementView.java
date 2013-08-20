package br.com.programacaocinema.view;

import br.com.codifico.model.element.CinemaElement;

public class CinemaElementView extends CinemaElement {
	@Override
	public String toString() {
		return this.getNome();
	}
}
