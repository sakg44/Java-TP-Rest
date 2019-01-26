package org.abdou.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.abdou.model.Commune;
import org.abdou.model.Maire;


@Stateless
public class MaireEJB {
	
	@PersistenceContext(unitName="tp-rest")
	private EntityManager em;
	
	
	public Maire getById(long id) {
		return em.find(Maire.class, id);
		
	}
	public List<Maire> getAllMaire() {
		Query query = em.createQuery("select maire  from Maire maire");
		List<Maire> maires=query.getResultList();
		return maires;
	}
	
	public long createMaire(String nom, String prenom) {
		
		  Maire myMaire= new Maire();
		  myMaire.setNom(nom);
		  myMaire.setPrenom(prenom);
		  em.persist(myMaire);
		  return myMaire.getId();
	}
	
	public long updatebyId(long id, String nom, String prenom) {
		em.find(Maire.class, id).setNom(nom);
		em.find(Maire.class, id).setPrenom(prenom);
		return id;
	}
	
	public Maire deletebyId(long id) {
		Maire maire=em.find(Maire.class, id);
		em.remove(maire);
		
		return maire;
	}
	

}
