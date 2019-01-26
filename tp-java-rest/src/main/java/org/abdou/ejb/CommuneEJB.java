package org.abdou.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.abdou.model.Commune;
import org.abdou.model.Maire;


@Stateless  
public class CommuneEJB {
	
	@PersistenceContext(unitName="tp-rest")
	private EntityManager em ;
	
	public long createCommune( String name) {
		Commune com= new Commune();
		com.setNom(name) ;
		em.persist(com);
		return com.getId();
	}
	
	public Commune getById(long id) {
		
		return em.find(Commune.class, id);
	}
	
	public List<Commune> getAllCommune() {
		Query query = em.createQuery("select commune  from Commune commune");
		List<Commune> communes=query.getResultList();
		return communes;
	}
	
	
	public long updatebyId(long id, String name) {
		em.find(Commune.class, id).setNom(name);
		return id;
	}
	
	public Commune deletebyId(long id) {
		Commune com=em.find(Commune.class, id);
		em.remove(com);
		
		
		return com;
	}
	
	public Commune communeLinkWithMaire(long idc, long idm) {
		Commune com=em.find(Commune.class, idc);
		Maire maire=em.find(Maire.class, idm);
		com.setMaire(maire);
		return com;
	}
	
	

}
