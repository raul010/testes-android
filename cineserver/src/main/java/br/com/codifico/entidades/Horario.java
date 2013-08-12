package br.com.codifico.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Horario {
	List<String> horarios;
	
	public Horario() {
		horarios = new ArrayList<String>();
	}
	
	public void addHorario(String horario) {
		this.horarios.add(horario);
	}

	public List<String> getListaDeHorarios() {
		return Collections.unmodifiableList(horarios);
	}
	
	
	
}
