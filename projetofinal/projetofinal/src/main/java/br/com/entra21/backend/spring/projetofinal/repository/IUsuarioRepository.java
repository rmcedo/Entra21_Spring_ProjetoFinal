package br.com.entra21.backend.spring.projetofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.entra21.backend.spring.projetofinal.model.Usuario;

@Repository
@EnableJpaRepositories
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("From Usuario WHERE idade >= :idadeParam")
	
	List<Usuario> maiorIdade(@Param("idadeParam")Integer idade);
	// No controller seria chamado esse método que traria todos os usuários
	// Percorrer a lista procurando quem é maior de idade
	// Cada item que atende ao critério seria filtrado em uma nova lista
	
	// Tudo isse é substituido por uma busca que traz exatamente os dados que eu preciso

	@Query("From Usuario WHERE email = :emailParam and senha = :senhaParam")
	Usuario login(@Param("emailParam")String email,@Param("senhaParam") String senha);
	
} 