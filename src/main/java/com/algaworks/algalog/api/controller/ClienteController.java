package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalagoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CatalagoClienteService catalagoClienteService;


	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/{ClienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long ClienteId) {
		return clienteRepository.findById(ClienteId)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalagoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{ClienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long ClienteId, 
			@RequestBody Cliente cliente){
		if(!clienteRepository.existsById(ClienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(ClienteId);
		cliente = catalagoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{ClienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long ClienteId){
		if(!clienteRepository.existsById(ClienteId)) {
			return ResponseEntity.notFound().build();
		}
		catalagoClienteService.excluir(ClienteId);
		return ResponseEntity.noContent().build();
	}
	
}

