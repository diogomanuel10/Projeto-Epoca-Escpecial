package com.codex.model;

public class Exercício {
	
	public String datacriacao;
	public String titulo;
	public String exercicio;
	public String dificuldade;
	public User autores;
	public String tags;
	public Solucao solucao;
	public CasoTeste casoteste;
	
	
	
	public Exercício() {
		super();
	}
	public Exercício(String datacriacao, String titulo, String exercicio, String dificuldade, User autores,
			String tags, Solucao solucao, CasoTeste casoteste) {
		super();
		
		this.datacriacao = datacriacao;
		this.titulo = titulo;
		this.exercicio = exercicio;
		this.dificuldade = dificuldade;
		this.autores = autores;
		this.tags = tags;
		this.solucao = solucao;
		this.casoteste = casoteste;
	}
	public String getDatacriacao() {
		return datacriacao;
	}
	public void setDatacriacao(String datacriacao) {
		this.datacriacao = datacriacao;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getExercicio() {
		return exercicio;
	}
	public void setExercicio(String exercicio) {
		this.exercicio = exercicio;
	}
	public String getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}
	public User getAutores() {
		return autores;
	}
	public void setAutores(User autores) {
		this.autores = autores;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Solucao getSolucao() {
		return solucao;
	}
	public void setSolucao(Solucao solucao) {
		this.solucao = solucao;
	}
	public CasoTeste getCasoteste() {
		return casoteste;
	}
	public void setCasoteste(CasoTeste casoteste) {
		this.casoteste = casoteste;
	}
	
	
	
	
	
	

}
