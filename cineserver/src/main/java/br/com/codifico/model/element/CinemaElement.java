package br.com.codifico.model.element;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.codifico.model.Cinema;

@XmlRootElement
@Entity(name="cinema_element")
//@NamedQueries({
//	@NamedQuery(name="Cinema.findAll", query="SELECT c FROM Cinema c")
//})
public class CinemaElement extends Cinema {
	
}
