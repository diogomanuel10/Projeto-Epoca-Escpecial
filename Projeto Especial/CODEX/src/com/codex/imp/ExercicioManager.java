package com.codex.imp;

import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import com.codex.data.ExercicioData;
import com.codex.model.CasoTeste;
import com.codex.model.Exerc�cio;
import com.codex.model.Solucao;
import com.codex.model.User;
import com.codex.imp.ExercicioManager;
import com.codex.imp.UsersManager;

public class ExercicioManager {
	
static ExercicioManager exManager = null;
	
	public static ExercicioManager getInstance() {
		if (exManager == null) {
			exManager = new ExercicioManager();

		}
return exManager;
	}

	// CREATE A NEW EXERCISE
		public void createExercise(String titulo, String userIDAuthour, String exercicio,
				String dificuldade, String tags, String linguagem, String input, String output) {

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			String data = dateFormat.format(date);
			System.out.println(dateFormat.format(date)); // 2016/11/16 12:08:43

			List<User> users = UsersManager.getInstance().getUser(userIDAuthour);

			String username = null;
			String password = null;
			String nome = null;
			String email = null;

			for (User user : users) {
				username = user.getUsername();
				password = user.getPassword();
				nome = user.getNome();
				email = user.getEmail();
			}
			User userC = new User(username,password, email, nome);
			Solucao solucao = new Solucao(linguagem);
			CasoTeste caso = new CasoTeste(input, output);

			ExercicioData exerciseData = ExercicioData.getInstance();
			exerciseData.createExercise(new Exerc�cio(data, titulo,exercicio, dificuldade, userC, tags, solucao, caso));

		}
		
		//Buscar todos os Exerc�cios
		
		public List<Exerc�cio> getExercicios() {
			ExercicioData exData = ExercicioData.getInstance();
			return exData.getExercicios();
		}
		
		//Buscar espec�fico exerc�cio por dificuldade
		
		public List<Exerc�cio> getExDificuldade(String dificuldade) {
			ExercicioData exData = ExercicioData.getInstance();
			return exData.getExDificuldade(dificuldade);
		}
		
		//Buscar espec�fico exerc�cio por utilizador
		
				public List<Exerc�cio> getExUser(String username) {
					ExercicioData exData = ExercicioData.getInstance();
					return exData.getExUser(username);
				}
				
				//Buscar espec�fico exerc�cio por linguagem
				
				public List<Exerc�cio> getExLinguagem(String linguagem) {
					ExercicioData exData = ExercicioData.getInstance();
					return exData.getExLinguagem(linguagem);
				}
				
//Buscar espec�fico exerc�cio por titulo
				
				public List<Exerc�cio> getExTitulo(String titulo) {
					ExercicioData exData = ExercicioData.getInstance();
					return exData.getExTitulo(titulo);
				}
				
		
//Buscar espec�fico exerc�cio por tag
				
				public List<Exerc�cio> getExTags(String tags) {
					ExercicioData exData = ExercicioData.getInstance();
					return exData.getExTags(tags);
				}
				
				
				
				// DELETE A SPECIFIC EXERCISE
				public Response removeExercise(String titulo, String userIDAuthor) {

					List<Exerc�cio> ex = ExercicioData.getInstance().getExTitulo(titulo);
					String userEx = null;
					for (Exerc�cio exs : ex) {
						userEx = exs.autores.getUsername();
					}
					// CHECKS IF THE USER LOGGED IS THE AUTHOR OF THE EXERCISE
					if (userIDAuthor.equals(userEx)) {
						ExercicioData exData = ExercicioData.getInstance();
						System.out.println("REMOVIDO COM SUCESSO");
						return exData.removeEx(titulo);
						
					} else {
						System.out.println("VOCE NAO PODE REMOVER ESTE EXERCISE PQ NAO TEM PERMISSOES");
					}
					return null;
				}
}
