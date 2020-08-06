package appdis.ProyectoFinal.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Persona;

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

//	public Cliente readCedula(String cedula) {
//		return em.find(Cliente.class, cedula);
//	}

	public void delete(int id_cliente) {
		Cliente cl = read(id_cliente);
		em.remove(cl);
	}
	
	
	@SuppressWarnings("unchecked")
	public Cliente readCedula(String cedula) {
		String jpql = " SELECT cl FROM Cliente cl WHERE cl.persona.cedula =:cedula";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("cedula", cedula);
		return (Cliente) q.getSingleResult();
	}
	

	@SuppressWarnings("unchecked")
	public List<Cliente> getClientes() {
		String jpql = " SELECT cl FROM Cliente cl";
		Query q = em.createQuery(jpql, Cliente.class);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Cliente getCorreo(String usuario) {
		String jpql = " SELECT cl FROM Cliente cl WHERE cl.usuario =:usuario";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("usuario", usuario);
		return (Cliente) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public Cliente getUserPass(String user, String pass) {
		String jpql = "SELECT cl FROM Cliente cl WHERE usuario = :user AND contrasenia = :pass";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("user", user);
		q.setParameter("pass", pass);

		return (Cliente) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getCliente(String filtro) {
		String jpql = " SELECT cl FROM Cliente cl WHERE id_cliente LIKE :filtro";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("filtro", filtro);
		return q.getResultList();
	}

}
