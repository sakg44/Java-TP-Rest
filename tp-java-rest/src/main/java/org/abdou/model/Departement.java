package org.abdou.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * Entity implementation class for Entity: Departement
 *
 */
@Entity(name="Departement")
@Table( name="departement")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Departement implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	@Column(length=50)
	
	private String nom;
	@Column(length=50)
	private String code;
	
	@OneToMany(mappedBy="departement")
	private Collection<Commune> communes;
	
	

	public Collection<Commune> getCommunes() {
		return communes;
	}
	public void setCommunes(Collection<Commune> communes) {
		this.communes = communes;
	}
	public Departement() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
   
}
