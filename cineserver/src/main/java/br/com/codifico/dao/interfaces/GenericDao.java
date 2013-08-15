package br.com.codifico.dao.interfaces;

import java.util.Map;

public interface GenericDao<T> {

	long countAll(Map<?, ?> clausulas);

	T create(T t);

	void delete(Object id);

	T find(Object id);

	T update(T t);
}
