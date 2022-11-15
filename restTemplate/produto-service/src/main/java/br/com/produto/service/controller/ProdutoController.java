package br.com.produto.service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.produto.service.dtos.ProdutoRequestDTO;
import br.com.produto.service.dtos.ProdutoResponseDTO;
import br.com.produto.service.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private final ProdutoService service;

	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	public ResponseEntity<ProdutoResponseDTO> save(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
		return ResponseEntity.ok(this.service.save(produtoRequestDTO));
	}

	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirPeloId(@PathVariable(name = "id") Long id) {
		this.service.excluirPeloId(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> buscarPeloId(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(this.service.buscarPeloId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable(name = "id") Long id,
			@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
		return ResponseEntity.ok(this.service.atualizar(id, produtoRequestDTO));
	}

}
