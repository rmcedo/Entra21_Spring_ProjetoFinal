package br.com.entra21.backend.spring.projetofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.entra21.backend.spring.projetofinal.model.Cliente;

@Repository
@EnableJpaRepositories
public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Query("From Cliente WHERE depositoInicial >= :depositoParam")
	
	List<Cliente> qtdDeposito(@Param("depositoParam") String depositoInicial);

}
