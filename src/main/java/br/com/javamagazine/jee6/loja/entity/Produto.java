package br.com.javamagazine.jee6.loja.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 5070090026152374958L;

	@Id
	private Long id;

	@NotNull
	private String titulo;

	private String descricao;

	@NotNull
	private Double preco;

	@Column(name = "nome_imagem")
	private String nomeImagem;

	public Produto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(final Double preco) {
		this.preco = preco;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(final String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

}
