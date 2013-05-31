package br.com.javamagazine.jee6.loja.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.javamagazine.jee6.loja.entity.Cliente;
import br.com.javamagazine.jee6.loja.exception.ClienteExistenteException;

@Stateless
public class ClienteServices {

	@PersistenceContext
	private EntityManager em;

	public Cliente findByEmailAndSenha(final String email, final String senha) {
		final String querySql = "SELECT c FROM Cliente c WHERE c.email = :email AND c.senha = :senha";
		final Query query = em.createQuery(querySql);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		try {
			return (Cliente) query.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}
	}

	public Cliente adicionar(final Cliente cliente) throws ClientExistenteException {
		try {
			em.persist(cliente);
			return cliente;
		} catch (final PersistenceException e) {
			throw new ClienteExistenteException();
		}
	}

}
