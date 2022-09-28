package br.com.entra21.backend.spring.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.entra21.backend.spring.projetofinal.model.Cliente;
import br.com.entra21.backend.spring.projetofinal.repository.IClienteRepository;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteRepository clienteRepository;
	
	@GetMapping("/qtd_deposito/{depositoInicial}")
	public List<Cliente> qtdDeposito(@PathVariable("depositoInicial") String depositoInicial){
		
		return clienteRepository.qtdDeposito(depositoInicial);
	}
	
}
