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

import org.abdou.ejb.MaireEJB;

import org.abdou.model.Maire;

@Path("maire")
public class MaireRS {
   
	@EJB
	MaireEJB myMaireEJB;
	
	@GET @Path("retrieve/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getMaire(@PathParam("id") long id) {
		
		Maire maire=myMaireEJB.getById(id);
		return Response.ok().entity(maire).build();
	}
	
	@GET @Path("retrieveAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommunes() {
		List<Maire> maires=myMaireEJB.getAllMaire();
			return Response.ok().entity(maires).build();
		
	}
	
	@POST @Path("create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response setMaire(@FormParam("nom") String nom,@FormParam("prenom") String prenom ) throws URISyntaxException {
		long id=myMaireEJB.createMaire(nom, prenom);
		//return Response.ok().entity("le maire creer avec id :" +id).build();
		URI uri1 = new URI("http://localhost:8080/tp-java-rest/maire.jsp");
		return Response.temporaryRedirect(uri1).build();
	} 
	
	@POST @Path("modifie")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateCommune(@FormParam("id") long id,@FormParam("nom") String nom,@FormParam("prenom") String prenom) throws URISyntaxException
	{
		myMaireEJB.updatebyId(id, nom, prenom);
		//return Response.ok().entity("le maire dont id=" +id+ "est update").build();
		URI uri1 = new URI("http://localhost:8080/tp-java-rest/maire.jsp");
		return Response.temporaryRedirect(uri1).build();
	}
	
	@GET @Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletMaire(@PathParam("id") long id1) {
		Maire maire= myMaireEJB.deletebyId(id1);
		return Response.ok().entity("le maire "+ maire.getPrenom()+" "+ maire.getNom().toUpperCase() + " est suprimer").build();
	} 
	
}
