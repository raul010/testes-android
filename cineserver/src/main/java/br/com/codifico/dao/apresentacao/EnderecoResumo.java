package br.com.codifico.dao.apresentacao;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.codifico.model.Endereco;

@Entity(name="endereco_resumo")
public class EnderecoResumo extends Endereco {
	
	@Column(name="dados_recebidos")
	private String dadosRecebidos;

	public String getDadosRecebidos() {
		return dadosRecebidos;
	}

	public void setDadosRecebidos(String dadosRecebidos) {
		this.dadosRecebidos = dadosRecebidos;
	}
	
}
