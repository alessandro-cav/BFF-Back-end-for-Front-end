package br.com.produto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.produto.dtos.ProdutoRequestDTO;
import br.com.produto.dtos.ProdutoResponseDTO;
import br.com.produto.dtos.ProdutoResponseDTO2;
import br.com.produto.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private final ProdutoService service;

	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("/edicao")
	public ResponseEntity<List<ProdutoResponseDTO2>> findAll02 () {
		return ResponseEntity.ok(this.service.findAll02());
	}
	
	@PostMapping
	public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO){
		return this.service.criarProduto(produtoRequestDTO);
	}

}
