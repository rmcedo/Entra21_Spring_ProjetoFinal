package br.com.entra21.backend.spring.projetofinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.entra21.backend.spring.projetofinal.model.Programador;
import br.com.entra21.backend.spring.projetofinal.repository.IProgramadorRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/programadores")

public class ProgramadorController {

	@Autowired

	private IProgramadorRepository programadorRepository;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)

	public List<Programador> listar() {
		return programadorRepository.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)

	public List<Programador> buscar(@PathVariable("id") int id) {

		List<Programador> response = programadorRepository.findById(id).stream().toList();

		return response;
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)

	public @ResponseBody Programador adicionar(@RequestBody Programador novoProgramador) {

		return programadorRepository.save(novoProgramador);

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)

	public @ResponseBody Optional<Programador> atualizar(@PathVariable("id") int param,
			@RequestBody Programador programadorNovosDados) {

		Programador atual = programadorRepository.findById(param).get();
		
		atual.setNome(programadorNovosDados.getNome());
		
		atual.setQtd_linguagem(programadorNovosDados.getQtd_linguagem());
		
		programadorRepository.save(atual);
		
		return programadorRepository.findById(param);
		
			
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	
	public @ResponseBody boolean deletar(@PathVariable ("id") int id) {
		
		programadorRepository.deleteById(id);
		return !programadorRepository.existsById(id);
		
			
		
	}

}
