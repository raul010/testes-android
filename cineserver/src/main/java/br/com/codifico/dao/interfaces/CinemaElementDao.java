package br.com.codifico.dao.interfaces;

import java.util.List;

import br.com.codifico.model.element.CinemaElement;

public interface CinemaElementDao extends GenericDao<CinemaElement>{
	public List<CinemaElement> findAll();
	
}
