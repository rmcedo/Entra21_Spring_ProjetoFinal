package br.com.entra21.backend.spring.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.entra21.backend.spring.projetofinal.model.Usuario;
import br.com.entra21.backend.spring.projetofinal.repository.IUsuarioRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/usuarios")

public class UsuarioController{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	
	@GetMapping("/maior_que/{idade}")
	public List<Usuario> maiorQue(@PathVariable("idade") Integer idade){
		
		return usuarioRepository.maiorIdade(idade);
		
	}
	
	@PostMapping("/login")
	
	public Usuario login(@RequestBody Usuario credencial) {
		
		return usuarioRepository.login(credencial.getEmail(),credencial.getSenha());
	}
	 
}
