package br.com.codifico.dao.interfaces;


public interface GenericDao<T> {

	T create(T t);

	void delete(Object id);

	T find(Object id);

	T update(T t);
}
