package org.abdou.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.abdou.ejb.DepartementEJB;
import org.abdou.model.Departement;



@Path("departement")
public class DepartementRS {
	
	@EJB
	DepartementEJB myDept;

	@GET @Path("retrieve/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getDepartement(@PathParam("id") long id) {
		
		Departement myDepartement=myDept.getById(id);
		return Response.ok().entity(myDepartement).build();
	}
	
	
	@GET @Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDepartement(@PathParam("id") long id1) {
		Departement myDepartement= myDept.deletebyId(id1);
		return Response.ok().entity("le maire "+  myDepartement.getNom()+ " est suprimer").build();
	} 

}
