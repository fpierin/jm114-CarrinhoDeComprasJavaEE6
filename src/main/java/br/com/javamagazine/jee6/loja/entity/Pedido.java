package br.com.javamagazine.jee6.loja.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 813440582621834761L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date data;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@NotNull
	private Cliente cliente;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "item_pedido", joinColumns = @JoinColumn(name = "id_pedido"))
	private Set<ItemPedido> itens;

	@Column(name = "total")
	@NotNull
	private Double valorTotal;

	public Pedido() {
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(final Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ItemPedido> getItens() {
		if (itens == null) {
			itens = new HashSet<ItemPedido>();
		}
		return itens;
	}

	public void setItens(final Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(final Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemPedido> getItensOrdenadosEmLista() {
		return new ArrayList<ItemPedido>(getItens());
	}

	public void adicionarItem(final Produto produto, final Integer quantidade) {
		final ItemPedido itemExistente = getItem(produto);
		if (itemExistente != null) {
			atualizarQuantidade(produto, itemExistente.getQuantidade());
		} else {
			getItens().add(new ItemPedido(produto, quantidade));
			calcularTotal();
		}
	}

	private void calcularTotal() {
		valorTotal = 0D;
		for (final ItemPedido item : getItens()) {
			valorTotal += item.gePrecoTotal();
		}

	}

	private void atualizarQuantidade(final Produto produto, final int novaQuantidade) {
		final ItemPedido item = getItem(produto);
		if (item == null) {
			throw new IllegalArgumentException("Item não encontrado para produto " + produto);
		}
		item.atualizarQuantidade(novaQuantidade);
	}

	private ItemPedido getItem(final Produto produto) {
		final ItemPedido itemAProcurar = new ItemPedido(produto);
		for (final ItemPedido item : getItens()) {
			if (item.equals(itemAProcurar)) {
				return item;
			}
		}
		return null;
	}

	public void removerItem(final Produto produto) {
		getItens().remove(new ItemPedido(produto));
		calcularTotal();
	}

}
