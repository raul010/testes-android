package br.com.codifico.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.codifico.model.Filme;
import br.com.codifico.util.JsoupUtil;
import br.com.codifico.util.enums.EnumDia;

public class FilmeCartaz extends Filme {
	private EnumDia enumDia;
	private List<String> horarios;
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String diaSemana;
	
	public FilmeCartaz(int diaIteracao) {
		
		switch (diaIteracao) {
		case 0:
			enumDia = EnumDia.HOJE;
			break;
		case 1:
			enumDia = EnumDia.AMANHA;
			break;
		case 2:
			enumDia = EnumDia.DEPOIS;
			break;

		default:
			throw new RuntimeException();
		}
		diaSemana = JsoupUtil.diaDaSemana(enumDia.getDia());
		
		horarios = new ArrayList<String>();
		
	}

	public EnumDia getEnumDia() {
		return enumDia;
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
