package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Credito;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class CreditoDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	private EntityManager em;

	public void insert(Credito credito) {
			em.persist(credito);

	}

	public void update(Credito credito) {
		em.merge(credito);
	}

	public Credito read(int id) {
		return em.find(Credito.class, id);
	}

	public void delete(int id) {
		Credito cr = read(id);
		em.remove(cr);
	}
	
	@SuppressWarnings("unchecked")
	public List<Credito> getCreditos() {
		String jpql = " SELECT cr FROM Credito cr";
		Query q = em.createQuery(jpql, Credito.class);
//		q.setParameter("filtro", filtro);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Credito> getCreditosCuenta(int filtro) {
		String jpql = " SELECT cr FROM Credito cr WHERE id_cuenta = :filtro";
		Query q = em.createQuery(jpql, Credito.class);
		q.setParameter("filtro", filtro);
		return q.getResultList();
	}

}
