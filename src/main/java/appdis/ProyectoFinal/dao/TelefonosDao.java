package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Telefonos;



/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class TelefonosDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	 private EntityManager em;

	    
	 	public void insert(Telefonos telefonos) {
	 em.persist(telefonos);
	 	}

	 	
	 	public void update(Telefonos telefonos)   {
	 em.merge(telefonos);
	 	}

	 	
	 	public Telefonos read(int id) {
			return em.find(Telefonos.class, id);
		}

		public void delete(int id) {
			Telefonos tl = read(id);
			em.remove(tl);
		}

	
	 	
		@SuppressWarnings("unchecked")
		public List<Telefonos> getTelefonos(String filtro) {
			String jpql = " SELECT tl FROM telefonos tl WHERE id_telf LIKE :filtro";
			Query q = em.createQuery(jpql, Telefonos.class);
			q.setParameter("filtro", filtro);
			return q.getResultList();
		}
	 	
	 }
