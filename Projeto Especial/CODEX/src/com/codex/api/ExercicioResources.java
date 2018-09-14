package com.codex.api;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.codex.imp.ExercicioManager;
import com.codex.imp.UsersManager;
import com.codex.model.Exerc�cio;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Path("/exercicios")
public class ExercicioResources {

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response insertExercise(@FormParam("titulo") String title, @FormParam("token") String token, @FormParam("exercicio") String exercicio,
			@FormParam("linguagem") String linguagem, @FormParam("dificuldade") String dificuldade, @FormParam("tags") String tags, 
			@FormParam("input") String input, @FormParam("output") String output, @Context UriInfo uriInfo) {

		UsersManager userM = new UsersManager();
		ExercicioManager exManager = ExercicioManager.getInstance();

		try {
			// Verify JWT token
			Jwts.parser().setSigningKey(userM.getKey()).parseClaimsJws(token);
			// OK, we can trust this JWT

			// Get user data
			String Autoruser = (String) Jwts.parser().setSigningKey(userM.getKey()).parseClaimsJws(token)
			.getBody().get("username");

			exManager.createExercise(title,Autoruser,exercicio,dificuldade,tags,linguagem,input,output);

			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(title);
			return Response.created(builder.build()).build();

		} catch (SignatureException e) {
			// don't trust the JWT!
			return Response.serverError().status(401).type("text/plain").entity("No valid token!").build();
		}
		}
	
	@Path("/users/{username}")
	@GET
	public List<Exerc�cio> getExUser(@PathParam("username") String username) {
		ExercicioManager exManager = ExercicioManager.getInstance();
		return exManager.getExUser(username);

	}
	
	// GET EXERCISES BY DIFICULTY - TAG - DURATION - LANGUAGE OR ALL
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Exerc�cio> getGamesDificulty(@QueryParam("dificuldade") String dificuldade, @QueryParam("tag") String tag,
				@QueryParam("linguagem") String linguagem) {

			ExercicioManager exManager = ExercicioManager.getInstance();

			if (dificuldade != null) {
				return exManager.getExDificuldade(dificuldade);
			} else if (tag != null) {
				return exManager.getExTags(tag);
			} else if (linguagem != null) {
				return exManager.getExLinguagem(linguagem);
			} 
			return exManager.getExercicios();
		}
		// GET A SPECIFIC EXERCISE by exerciseID
		@Path("/{titulo}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Exerc�cio> getExercicio(@PathParam("titulo") String titulo) {

			ExercicioManager exManager = ExercicioManager.getInstance();
			return exManager.getExTitulo(titulo);

		}
		
		// DELETE A SPECIFIC EXERCISE
		@Path("/{titulo}")
		@DELETE
		public Response removeExercise(@PathParam("titulo") String titulo, @FormParam("token") String token) {

			UsersManager userM = new UsersManager();
			ExercicioManager exManager = ExercicioManager.getInstance();

			try {
				// Verify JWT token
				Jwts.parser().setSigningKey(userM.getKey()).parseClaimsJws(token);
				// OK, we can trust this JWT

				// Get user data
				String userIDAuthour = (String) Jwts.parser().setSigningKey(userM.getKey()).parseClaimsJws(token)
						.getBody().get("username");

				exManager.removeExercise(titulo, userIDAuthour);
				return Response.serverError().status(200).type("text/plain").entity("Exercicio Removido").build();

			} catch (SignatureException e) {
				// don't trust the JWT!
				return Response.serverError().status(401).type("text/plain").entity("N�o tens permiss�es!").build();
			}

		}
		
	

}
