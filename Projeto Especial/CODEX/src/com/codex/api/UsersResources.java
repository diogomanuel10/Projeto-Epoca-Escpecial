package com.codex.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.codex.imp.UsersManager;
import com.codex.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;


@Path("/users")
public class UsersResources {

	
	//Put Password User
	
	// PUT PASSWORD USER
		@Path("/{username}")
		@PUT
		@Consumes("application/x-www-form-urlencoded")
		public Response changePassword(@PathParam("username") String username, @FormParam("password") String password,
				@FormParam("NovaPassword") String NovaPassword, @FormParam("token") String token) {

			UsersManager userManager = new UsersManager();

			try {
				// Verify JWT token
				Jwts.parser().setSigningKey(userManager.getKey()).parseClaimsJws(token);
				// OK, we can trust this JWT

				// Get user data
				String userIDAuthour = (String) Jwts.parser().setSigningKey(userManager.getKey()).parseClaimsJws(token)
						.getBody().get("username");

				userManager.changePassword(username, password, NovaPassword, userIDAuthour);
				return Response.serverError().status(200).type("text/plain").entity("Change Successfully").build();
			} catch (SignatureException e) {
				// don't trust the JWT!
				return Response.serverError().status(401).type("text/plain").entity("You do not have permissions!").build();
			}

		}

// Login

@Path("/auth")
@POST
public Response auth(@FormParam("username") String username, @FormParam("pass") String password) {
	UsersManager userm = UsersManager.getInstance();
	if (UsersManager.checkCredentials(username, password)) {

		

		Map<String, Object> user = new HashMap<String, Object>();
		user.put("username", username);

		// Create JWT token
		String newToken = Jwts.builder().setClaims(user).setIssuer("SnippetWorld")
				.signWith(SignatureAlgorithm.HS512, userm.getKey()).compact();

		return Response.ok().entity(newToken).build();
	} else {
		return Response.serverError().status(401).type("text/plain").entity("User Inválido!").build();
	}
}


// GET ALL USERS
@GET
@Produces(MediaType.APPLICATION_JSON)
public List<User> getUsers() {

	UsersManager userManager = UsersManager.getInstance();
	System.out.println("oi");
	return userManager.getUsers();
}

// POST A NEW USER
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response insertUser(@FormParam("username") String username, @FormParam("email") String email,
			@FormParam("password") String password, @FormParam("nome") String nome, @Context UriInfo uriInfo) {

		UsersManager userm = UsersManager.getInstance();

		userm.createUser(username, email, password, nome);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(username);
		return Response.created(builder.build()).build();

	}
	
	

}


