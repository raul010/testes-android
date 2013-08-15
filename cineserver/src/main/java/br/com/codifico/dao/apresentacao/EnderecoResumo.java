package br.com.codifico.dao.apresentacao;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.codifico.model.Endereco;

@Entity
public class EnderecoResumo extends Endereco {
	@Column
	private String dados;

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}
	
	
}
