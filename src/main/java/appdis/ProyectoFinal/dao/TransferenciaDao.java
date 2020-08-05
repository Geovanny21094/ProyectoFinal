package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Transferencia;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class TransferenciaDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	private EntityManager em;

	public void insert(Transferencia transferencia) {
		if (read(transferencia.getId_transferencia()) != null) {
			em.persist(transferencia);
		} else {
			update(transferencia);
		}
	}

	public void update(Transferencia transferencia) {
		em.merge(transferencia);
	}

	public Transferencia read(int id) {
		return em.find(Transferencia.class, id);
	}

	public void delete(int id) {
		Transferencia tr = read(id);
		em.remove(tr);
	}

	@SuppressWarnings("unchecked")
	public List<Transferencia> getTransferencia(String filtro) {
		String jpql = " SELECT tr FROM transferencia tr WHERE id_transferencia LIKE :filtro";
		Query q = em.createQuery(jpql, Transferencia.class);
		q.setParameter("filtro", filtro);
		return q.getResultList();
	}

}