package br.com.produto.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.produto.service.dtos.ProdutoRequestDTO;
import br.com.produto.service.dtos.ProdutoResponseDTO;
import br.com.produto.service.entities.Produto;
import br.com.produto.service.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository repository;

	private final ModelMapper modelMapper;

	public ProdutoService(ProdutoRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	public ProdutoResponseDTO save(ProdutoRequestDTO produtoRequestDTO) {
		this.repository.findByNome(produtoRequestDTO.getNome()).ifPresent(produto -> {
			throw new RuntimeException("Produto já cadastrado.");
		});

		Produto produto = this.modelMapper.map(produtoRequestDTO, Produto.class);
		produto = this.repository.save(produto);
		return this.modelMapper.map(produto, ProdutoResponseDTO.class);
	}

	public List<ProdutoResponseDTO> findAll() {
		return this.repository.findAll().stream().map(produto -> {
			return this.modelMapper.map(produto, ProdutoResponseDTO.class);
		}).collect(Collectors.toList());
	}

	public ProdutoResponseDTO buscarPeloId(Long id) {
		return this.repository.findById(id).map(produto -> {
			return modelMapper.map(produto, ProdutoResponseDTO.class);
		}).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
	}

	public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequestDTO) {
		return this.repository.findById(id).map(produto -> {

			if (!produto.getNome().equals(produtoRequestDTO.getNome())) {
				this.repository.findByNome(produtoRequestDTO.getNome()).ifPresent(p -> {
					throw new RuntimeException("Produto já cadastrado.");
				});
			}

			produto = this.modelMapper.map(produtoRequestDTO, Produto.class);
			produto = this.repository.save(produto);
			return this.modelMapper.map(produto, ProdutoResponseDTO.class);
		}).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
	}

	public void excluirPeloId(Long id) {
		this.repository.findById(id).ifPresentOrElse(produto -> {
			this.repository.delete(produto);
		}, () -> {
			throw new RuntimeException("Produto não encontrado.");
		});
	}

}
