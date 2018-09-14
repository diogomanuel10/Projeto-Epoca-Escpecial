package com.codex.model;

public class User {
	
	public String username;
	public String password;
	public String email;
	public String nome;

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public User(String username, String nome) {
		super();
		this.username = username;
		this.nome = nome;
	}
	public User(String username, String password, String email, String nome) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		
	}
	
	public User() {
	super();	
	}
	
	
	
}


