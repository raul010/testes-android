package br.com.codifico.dao.interfaces;

import java.util.List;

import br.com.codifico.model.Cinema;

public interface CinemaDao extends GenericDao<Cinema>{
	public List<? extends Cinema> findAll(Class<? extends Cinema> clazz);
	
}
