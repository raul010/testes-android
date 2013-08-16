package br.com.codifico.dao.apresentacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.codifico.model.Filme;
import br.com.codifico.util.JsoupUtil;
import br.com.codifico.util.enums.DataApos;

@Entity(name="filme_cartaz")
public class FilmeCartaz extends Filme {
	@Enumerated(EnumType.STRING)
	private DataApos dia;
	
	//@ElementCollection 
	//@CollectionTable(name="horario_filme")
	private List<String> horarios;
	
	@Column(name="dia_semana",length=10,nullable=false)
	private String diaSemana;
	
	public FilmeCartaz(int diaIteracao) {
		switch (diaIteracao) {
		case 0:
			dia = DataApos.HOJE;
			break;
		case 1:
			dia = DataApos.UM_DIA;
			break;
		case 2:
			dia = DataApos.DOIS_DIAS;
			break;
		case 3:
			dia = DataApos.TRES_DIAS;
			break;
		case 4:
			dia = DataApos.QUATRO_DIAS;
			break;
		case 5:
			dia = DataApos.CINCO_DIAS;
			break;
		case 6:
			dia = DataApos.SEIS_DIAS;
			break;
		case 7:
			dia = DataApos.SETE_DIAS;
			break;
		case 8:
			dia = DataApos.OITO_DIAS;
			break;

		default:
			throw new RuntimeException();
		}
		diaSemana = JsoupUtil.diaDaSemana(dia.getDia());
		
		horarios = new ArrayList<String>();
	}
	public FilmeCartaz() {
	}

	public DataApos getdia() {
		return dia;
	}

	public String getDiaSemana() {
		return diaSemana;
	}
	
	public List<String> getHorarios() {
		return Collections.unmodifiableList(horarios);
	}

	public void addHorario(String horarios) {
		this.horarios.add(horarios);
	}

}
