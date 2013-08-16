package br.com.codifico.dao;

import org.springframework.stereotype.Repository;

import br.com.codifico.dao.interfaces.CinemaDao;
import br.com.codifico.model.Cinema;

@Repository
public class CinemaDaoImpl extends GenericDaoImpl<Cinema> implements CinemaDao {
}
