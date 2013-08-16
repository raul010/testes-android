package br.com.codifico.dao;

import org.springframework.stereotype.Repository;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;

/**
 * Ficou obsoleto mas é um bom exemplo de Anotações com Herança
 * CinemaDao pai dos Repositorios, CinemaDaoImpl e CinemaElementDaoImpl
 * @author Raul
 *
 */
@Repository("CinemaElement")
public class CinemaElementDaoImpl extends GenericDaoImpl<Cinema> implements CinemaDao {

}
