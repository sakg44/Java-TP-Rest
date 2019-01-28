package org.abdou.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.abdou.model.Departement;
import org.abdou.model.Maire;

@Stateless
public class DepartementEJB {
	@PersistenceContext(unitName="tp-rest")
	private EntityManager em;
	
	
	public long createDepartement( String name,String code) {
		Departement dep= new Departement();
		dep.setNom(name);
		dep.setCode(code);
		em.persist(dep);
		return dep.getId();
	}
	
	public Departement getById(long id) {
		
		return em.find(Departement.class, id);
	}
	
	public long updatebyId(long id, String name, String code) {
		em.find(Departement.class, id).setNom(name);
		em.find(Departement.class, id).setCode(code);
		return id;
	}
	public List<Departement> getAllDept() {
		Query query = em.createQuery("select departement from Departement departement");
		List<Departement> dpt=query.getResultList();
		return dpt;
	}
	
	public Departement deletebyId(long id) {
		Departement dep=em.find(Departement.class, id);
		em.remove(dep);
		
		return dep;
	}

}
