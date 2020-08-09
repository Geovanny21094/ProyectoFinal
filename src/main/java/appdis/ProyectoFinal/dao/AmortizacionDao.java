package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Amortizacion;

@Stateless
public class AmortizacionDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	private EntityManager em;

	public void insert(Amortizacion amortizacion) {
		if (read(amortizacion.getId_Amoritizacion()) != null) {
			em.persist(amortizacion);
		} else {
			update(amortizacion);
		}
	}

	public void update(Amortizacion amortizacion) {
		em.merge(amortizacion);
	}

	public Amortizacion read(int id) {
		return em.find(Amortizacion.class, id);
	}

	public void delete(int id) {
		Amortizacion cr = read(id);
		em.remove(cr);
	}
	
	@SuppressWarnings("unchecked")
	public List<Amortizacion> getAmortizacionesPagadas(int filtro) {
		String estado="Pagada";
		String jpql = "SELECT cr FROM Amortizacion cr WHERE id_credito = :filtro AND estado LIKE :estado";
		Query q = em.createQuery(jpql, Amortizacion.class);
		q.setParameter("filtro", filtro);
		q.setParameter("estado", estado);
		if (q.getResultList().isEmpty()) {
			List<Amortizacion> amo = null;
			return amo;
		} else {
			return q.getResultList();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Amortizacion> getAmortizaciones(int filtro) {
		String estado="Por Pagar";
		String vencida="Vencida";
		String jpql = "SELECT cr FROM Amortizacion cr WHERE id_credito = :filtro AND estado LIKE :estado OR estado LIKE :vencida";
		Query q = em.createQuery(jpql, Amortizacion.class);
		q.setParameter("filtro", filtro);
		q.setParameter("estado", estado);
		q.setParameter("vencida", vencida);

		if (q.getResultList().isEmpty()) {
			List<Amortizacion> amo = null;
			return amo;
		} else {
			return q.getResultList();
		}
	}

	@SuppressWarnings("unchecked")
	public Amortizacion getAmortizacion(int idAmortizacion) {
		String jpql = " SELECT cr FROM Amortizacion cr WHERE id_Amoritizacion = :idAmortizacion";
		Query q = em.createQuery(jpql, Amortizacion.class);
		q.setParameter("idAmortizacion", idAmortizacion);
		return (Amortizacion) q.getSingleResult();
	}

}
