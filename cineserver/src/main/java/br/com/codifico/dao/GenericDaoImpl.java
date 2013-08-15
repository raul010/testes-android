package br.com.codifico.dao;

import java.util.Map;

import br.com.codifico.dao.interfaces.GenericDao;


public class GenericDaoImpl<T> implements GenericDao<T> {

	@Override
	public long countAll(Map<?, ?> clausulas) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T create(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

}
