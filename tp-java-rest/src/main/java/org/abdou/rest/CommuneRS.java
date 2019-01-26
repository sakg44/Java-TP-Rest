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
import javax.ws.rs.core.Response.ResponseBuilder;

import org.abdou.ejb.CommuneEJB;
import org.abdou.httpExtension.LINK;
import org.abdou.model.Commune;


@Path("commune")
public class CommuneRS {
	
	@EJB
	private CommuneEJB comEJB= new CommuneEJB();  

		@GET @Path("retrieve/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getCommune( @PathParam("id") long id) {
			
				Commune commune=comEJB.getById(id);
			
				return Response.ok().entity(commune).build();
			
			
		}
		
		@GET @Path("retrieveAll")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getCommunes() {
			List<Commune> communes=comEJB.getAllCommune();
				return Response.ok().entity(communes).build();
			
		}
		
		
		@POST @Path("create")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Response setCommune(@FormParam("commune") String name) throws URISyntaxException {
			long id= comEJB.createCommune(name);
			//return Response.ok().entity("la commune creer avec id :" +id).build();
			URI uri1 = new URI("http://localhost:8080/tp-java-rest/commune.jsp");
			return Response.temporaryRedirect(uri1).build();
		} 
		
		@POST @Path("modifie")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Response updateCommune(@FormParam("id") long id,@FormParam("newCommune") String name) throws URISyntaxException
		{
			comEJB.updatebyId(id, name);
			URI uri1 = new URI("http://localhost:8080/tp-java-rest/commune.jsp");
			//return Response.ok().entity("la commune dont id=" +id+ "est update").build();
			return Response.temporaryRedirect(uri1).build();
		}
		
		@GET @Path("delete/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deletCommune(@PathParam("id") long id1) throws URISyntaxException {
		
			Commune com= comEJB.deletebyId(id1);
			URI uri1 = new URI("http://localhost:8080/tp-java-rest/commune.jsp");
			return Response.ok().build();//entity("la commune "+ com.getNom() + " est suprimer").build();
			//return Response.temporaryRedirect(uri1).build();
		} 
		
	  @LINK @Path("/{idc}/maire/{idm}")
	  public Response updateRelation( @PathParam("idc") long idc,@PathParam("idm") long idm) {
		  
		  Commune commune = comEJB.communeLinkWithMaire(idc, idm);
		  return Response.ok().entity("la commune "+commune.getNom()+ " est en relation avec le maire= "+commune.getMaire()).build();
	  }
}
