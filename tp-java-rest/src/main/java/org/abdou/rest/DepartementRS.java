package org.abdou.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	
	@GET @Path("retrieveAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDepartements() {
		List<Departement> departements=myDept.getAllDept();
			return Response.ok().entity(departements).build();
		
	}
	
	@POST @Path("create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response setDepartement(@FormParam("nom") String nom,@FormParam("code") String code ) throws URISyntaxException {
		long id=myDept.createDepartement(nom, code);
		//return Response.ok().entity("le departement creer avec id :" +id).build();
		URI uri1 = new URI("http://localhost:8080/tp-java-rest/departement.jsp");
		return Response.temporaryRedirect(uri1).build();
	} 
	
	@POST @Path("modifie")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateCommune(@FormParam("id") long id,@FormParam("nom") String nom,@FormParam("code") String code) throws URISyntaxException
	{
		myDept.updatebyId(id, nom, code);
		//return Response.ok().entity("le departement dont id=" +id+ "est update").build();
		URI uri1 = new URI("http://localhost:8080/tp-java-rest/departement.jsp");
		return Response.temporaryRedirect(uri1).build();
	}
	
	
	@GET @Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDepartement(@PathParam("id") long id1) throws URISyntaxException {
		Departement myDepartement= myDept.deletebyId(id1);
		//return Response.ok().entity("le maire "+  myDepartement.getNom()+ " est suprimer").build();
		URI uri1 = new URI("http://localhost:8080/tp-java-rest/departement.jsp");
		return Response.temporaryRedirect(uri1).build();
	} 

}
