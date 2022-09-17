package br.com.entra21.backend.spring.projetofinal.controller;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.entra21.backend.spring.projetofinal.model.ItemNivel3;
import br.com.entra21.backend.spring.projetofinal.model.Programador;
import br.com.entra21.backend.spring.projetofinal.repository.IProgramadorRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/programadores")

public class ProgramadorController {

	
	
	private final String PATH = "localhost:8080/programadores";
	@Autowired

	private IProgramadorRepository programadorRepository;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)

	public List<Programador> listar() {
		
	List<Programador> response = programadorRepository.findAll();
	
	response.forEach(programador ->{
		setMaturidadeNivel3(programador);
	});
	
	return response;
	
		
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
	
	private void setMaturidadeNivel3(Programador programador) {



		ArrayList<String> headers = new ArrayList();

		headers.add("Accept : application/json");

		headers.add("Content-type : application/json");



		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_NULL);

		try {

		programador.setLinks(null);

		String nomeAtual = programador.getNome();

		programador.setNome("Nome diferente");

		String jsonUpdate = mapper.writeValueAsString(programador);

		programador.setNome(nomeAtual);

		programador.setId(null);

		String jsonCreate = mapper.writeValueAsString(programador);

		programador.setLinks(new ArrayList<>());

		programador.getLinks().add(new ItemNivel3("GET", PATH, null, null));

		programador.getLinks().add(new ItemNivel3("GET", PATH + "/" + programador.getId(), null, null));

		programador.getLinks().add(new ItemNivel3("POST", PATH, headers, jsonCreate));

		programador.getLinks().add(new ItemNivel3("PUT", PATH + "/" + programador.getId(), headers, jsonUpdate));

		} catch (JsonProcessingException e) {

		e.printStackTrace();

		}



		}

}
