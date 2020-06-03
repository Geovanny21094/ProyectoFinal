package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Cliente;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class ClienteDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	private EntityManager em;

	public void insert(Cliente cliente) {
		em.persist(cliente);
	}

	public void update(Cliente cliente) {
		em.merge(cliente);
	}

	public Cliente read(int id_cliente) {
		return em.find(Cliente.class, id_cliente);
	}

	public void delete(int id_cliente) {
		Cliente cl = read(id_cliente);
		em.remove(cl);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getCliente(String filtro) {
		String jpql = " SELECT cl FROM Cliente cl WHERE id_cliente LIKE :filtro";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("filtro", filtro);
		return q.getResultList();
	}

}
