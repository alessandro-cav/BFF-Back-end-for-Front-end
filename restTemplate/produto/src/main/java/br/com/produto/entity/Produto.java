package br.com.produto.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Produto {

	private Long id;

	private String nome;

	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate validade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
}
