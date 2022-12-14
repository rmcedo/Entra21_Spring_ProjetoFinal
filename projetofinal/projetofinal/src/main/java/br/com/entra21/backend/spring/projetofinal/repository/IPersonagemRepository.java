package br.com.entra21.backend.spring.projetofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.entra21.backend.spring.projetofinal.model.Personagem;
import br.com.entra21.backend.spring.projetofinal.model.Programador;

@Repository
@EnableJpaRepositories
public interface IPersonagemRepository extends JpaRepository<Personagem,Integer>{

	@Query("From Personagem WHERE idade >= :idadeParam")
	
	List<Personagem> qualIdade(@Param("idadeParam")Integer idade);
	
	
}
