package br.com.javamagazine.jee6.loja.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class ItemPedido implements Serializable, Comparable<ItemPedido> {

	private static final long serialVersionUID = 7848426935997596400L;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@Column(name = "preco_unitario")
	@NotNull
	private Double precoUnitario;

	@NotNull
	private Integer quantidade;

	@Column(name = "preco_total")
	@NotNull
	private Double precoTotal;

	public ItemPedido() {
	}

	public ItemPedido(final Produto produto, final Integer quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
		precoUnitario = produto.getPreco();
		calcularTotal();
	}

	private void calcularTotal() {
		precoTotal = precoUnitario * quantidade;
	}

	public ItemPedido(final Produto produto) {
		quantidade = null;
		setProduto(produto);
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Double gePrecoTotal() {
		return null;
	}

	public void atualizarQuantidade(final Integer novaQuantidade) {
		quantidade = novaQuantidade;
		calcularTotal();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(final Produto produto) {
		this.produto = produto;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(final Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(final Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	@Override
	public int compareTo(final ItemPedido itemPedido) {
		return produto.getTitulo().compareTo(itemPedido.getProduto().getTitulo());
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
