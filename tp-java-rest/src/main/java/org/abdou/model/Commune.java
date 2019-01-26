package org.abdou.model ;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name="Commune")
@Table(name="commune")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Commune implements Serializable {
		
	
	private static final long serialVersionUID = 1L;
    	@Id 
	   @GeneratedValue(strategy=GenerationType.AUTO)
	    @XmlAttribute
	    private long id;
	   @Column(name="nom")
		private String nom;
	   
	   
		@Column(name="code_postal")
		private String codePostal; 
		
		public Commune() {
			super();
		}
       
		@OneToOne
		@JoinColumn(name="commune_maire")
		private Maire maire;
        
		
		@ManyToOne
		@JoinColumn(name="commune_departement")
		private Departement departement;

		public Commune(long id, String nom) {
			super();
			this.id = id;
			this.nom = nom;
		}



		public Maire getMaire() {
			return maire;
		}



		public Departement getDepartement() {
			return departement;
		}



		public void setDepartement(Departement departement) {
			this.departement = departement;
		}



		public void setMaire(Maire maire) {
			this.maire = maire;
		}



		public String getCodePostal() {
			return codePostal;
		}



		public void setCodePostal(String codePostal) {
			this.codePostal = codePostal;
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
		
		
}
