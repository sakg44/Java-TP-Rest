package org.abdou.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abdou.model.Departement;

@Stateless
public class DepartementEJB {
	@PersistenceContext(unitName="tp-rest")
	private EntityManager em;
	
	
	public long createDepartement( String name) {
		Departement dep= new Departement();
		dep.setNom(name);
		em.persist(dep);
		return dep.getId();
	}
	
	public Departement getById(long id) {
		
		return em.find(Departement.class, id);
	}
	
	public long updatebyId(long id, String name) {
		em.find(Departement.class, id).setNom(name);
		return id;
	}
	
	public Departement deletebyId(long id) {
		Departement dep=em.find(Departement.class, id);
		em.remove(dep);
		
		return dep;
	}

}
