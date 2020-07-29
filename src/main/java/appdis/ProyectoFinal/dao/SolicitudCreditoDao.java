package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.SolicitudCredito;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class SolicitudCreditoDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	private EntityManager em;

	public void insert(SolicitudCredito solicitud) {
		em.persist(solicitud);
	}

	public void update(SolicitudCredito solicitud) {
		em.merge(solicitud);
	}

	public SolicitudCredito read(int id) {
		return em.find(SolicitudCredito.class, id);
	}

	public void delete(int id) {
		SolicitudCredito sc = read(id);
		em.remove(sc);
	}

	@SuppressWarnings("unchecked")
	public List<SolicitudCredito> getSolicitud(String filtro) {
		String jpql = " SELECT sc FROM SolicitudCredito sc WHERE id_sol LIKE :filtro";
		Query q = em.createQuery(jpql, SolicitudCredito.class);
		q.setParameter("filtro", filtro);
		return q.getResultList();
	}

}
