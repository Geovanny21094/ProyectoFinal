package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.InstitucionFinanciera;

@Stateless
public class InstituicionFinancieraDao {

	/**
	 * 
	 *
	 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
	 *
	 */

	@PersistenceContext(name = "testjpaPersistenceUnit")
	private EntityManager em;

	public void insert(InstitucionFinanciera institucion) {
		em.persist(institucion);
	}

	public void update(InstitucionFinanciera institucion) {
		em.merge(institucion);
	}

	public InstitucionFinanciera read(int id) {
		return em.find(InstitucionFinanciera.class, id);
	}

	public void delete(int id) {
		InstitucionFinanciera inf = read(id);
		em.remove(inf);
	}

	@SuppressWarnings("unchecked")
	public List<InstitucionFinanciera> getInstitucion(String filtro) {
		String jpql = " SELECT inf FROM InstitucionFinanciera inf WHERE id_inst LIKE :filtro";
		Query q = em.createQuery(jpql, InstitucionFinanciera.class);
		q.setParameter("filtro", filtro);
		return q.getResultList();
	}

}
