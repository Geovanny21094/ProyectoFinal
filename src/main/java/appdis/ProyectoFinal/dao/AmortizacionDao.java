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
	public List<Amortizacion> getAmortizaciones(int filtro) {
		String jpql = " SELECT cr FROM Amortizacion cr WHERE id_credito = :filtro";
		Query q = em.createQuery(jpql, Amortizacion.class);
		q.setParameter("filtro", filtro);
		return q.getResultList();
	}

}
