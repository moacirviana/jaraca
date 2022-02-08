package br.jus.tream.jaraca.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.jus.tream.jaraca.model.Pedido;

@Repository
public class PedidoRepo {
	@PersistenceContext
	private EntityManager em;
	
	public List<Pedido> listarTodos(){
		TypedQuery<Pedido> query = em.createQuery("Select p From Pedido p", Pedido.class);
		return query.getResultList();
	}
}
