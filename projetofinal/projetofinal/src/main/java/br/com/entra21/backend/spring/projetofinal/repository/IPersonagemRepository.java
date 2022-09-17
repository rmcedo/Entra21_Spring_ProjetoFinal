package br.com.entra21.backend.spring.projetofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.backend.spring.projetofinal.model.Personagem;
import br.com.entra21.backend.spring.projetofinal.model.Programador;

public interface IPersonagemRepository extends JpaRepository<Personagem,Integer>{

}
