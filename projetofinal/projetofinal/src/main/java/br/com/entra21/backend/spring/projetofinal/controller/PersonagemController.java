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
import br.com.entra21.backend.spring.projetofinal.model.Personagem;
import br.com.entra21.backend.spring.projetofinal.repository.IPersonagemRepository;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/personagens")

public class PersonagemController {
	
	private final String PATH = "localhost:8080/personagens";
	@Autowired
	
	private IPersonagemRepository personagemRepository;
	
	
	@GetMapping("/qual_idade/{idade}")
	public List<Personagem> qualIdade(@PathVariable("idade")Integer idade) {
	
	return personagemRepository.qualIdade(idade);
			}
	
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	
	public List<Personagem> listar(){
		
		List<Personagem> response = personagemRepository.findAll();
		
		response.forEach(personagem ->{
			setMaturidadeNivel3(personagem);
		});
		return response;
		
		
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	
	public List<Personagem> buscar(@PathVariable("id") int id){
		
		List<Personagem> response = personagemRepository.findById(id).stream().toList();
		return response;
		
		
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	
	public @ResponseBody Personagem adicionar(@RequestBody Personagem novoPersonagem) {
		
		return personagemRepository.save(novoPersonagem);
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	
	public @ResponseBody Personagem atualizar(@PathVariable("id") int id, @RequestBody Personagem personagemNovosDados){
		
		Personagem atual = personagemRepository.findById(id).get();
		
		atual.setAcessorio(personagemNovosDados.isAcessorio());
		atual.setHabilidade(personagemNovosDados.getHabilidade());
		atual.setIdade(personagemNovosDados.getIdade());
		atual.setNome_heroi(personagemNovosDados.getNome_heroi());
		atual.setNome_real(personagemNovosDados.getNome_real());
	
		atual = personagemRepository.save(atual);
		setMaturidadeNivel3(atual);
		
		return atual;
		
		
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	
	public @ResponseBody boolean deletar(@PathVariable ("id") int id) {
		
		personagemRepository.deleteById(id);
		return !personagemRepository.existsById(id);
		
	}
	
	
	
	private void setMaturidadeNivel3(Personagem personagem) {



		ArrayList<String> headers = new ArrayList();

		headers.add("Accept : application/json");

		headers.add("Content-type : application/json");



		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_NULL);

		try {

		personagem.setLinks(null);

		String nomeAtual = personagem.getNome_real();

		personagem.setNome_real("Nome diferente");

		String jsonUpdate = mapper.writeValueAsString(personagem);

		personagem.setNome_real(nomeAtual);

		personagem.setId(null);

		String jsonCreate = mapper.writeValueAsString(personagem);

		personagem.setLinks(new ArrayList<>());

		personagem.getLinks().add(new ItemNivel3("GET", PATH, null, null));

		personagem.getLinks().add(new ItemNivel3("GET", PATH + "/" + personagem.getId(), null, null));

		personagem.getLinks().add(new ItemNivel3("POST", PATH, headers, jsonCreate));

		personagem.getLinks().add(new ItemNivel3("PUT", PATH + "/" + personagem.getId(), headers, jsonUpdate));

		} catch (JsonProcessingException e) {

		e.printStackTrace();

		}



		}

}
