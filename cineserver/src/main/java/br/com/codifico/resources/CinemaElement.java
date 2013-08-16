package br.com.codifico.resources;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.codifico.model.Cinema;

@XmlRootElement
@Entity(name="cinema_element")
public class CinemaElement extends Cinema {
	
}
