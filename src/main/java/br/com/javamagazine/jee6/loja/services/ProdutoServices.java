package br.com.javamagazine.jee6.loja.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.javamagazine.jee6.loja.entity.Produto;

@Stateless
public class ProdutoServices {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Produto> findAll() {
		final String query = "SELECT p FROM Produto p ORDER BY p,titulo";
		return em.createQuery(query).getResultList();
	}

}
