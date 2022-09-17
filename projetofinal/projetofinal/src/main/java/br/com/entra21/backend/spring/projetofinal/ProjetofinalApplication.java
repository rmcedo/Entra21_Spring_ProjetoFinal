package br.com.entra21.backend.spring.projetofinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ProjetofinalApplication implements CommandLineRunner{

	@Autowired
	private JdbcTemplate jdbc;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetofinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//String sql = "INSERT INTO programador (nome, qtd_linguagem) VALUES (?, ?)";
		String sql = "INSERT INTO personagem (habilidade, nome_heroi, nome_real, acessorio, idade) VALUES (?,?,?,?,?)";
		
		
		int result=jdbc.update(sql,"Deus do Trovão", "Thor", "Thor Oddinson", 1 , 5000);
		//int result = jdbc.update(sql, "Rafael", 2);
		
	}

}
