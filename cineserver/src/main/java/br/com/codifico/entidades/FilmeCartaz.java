package br.com.codifico.entidades;

import br.com.codifico.util.JsoupUtil;
import br.com.codifico.util.enums.EnumDia;


public class FilmeCartaz extends Filme {
	private EnumDia enumDia;
	private Horario horario;
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
		
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	
	public EnumDia getEnumDia() {
		return enumDia;
	}

	public String getDiaSemana() {
		return diaSemana;
	}
}
