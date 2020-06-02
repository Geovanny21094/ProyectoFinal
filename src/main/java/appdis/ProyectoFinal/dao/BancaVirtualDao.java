package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.BancaVirtual;
import appdis.ProyectoFinal.modelo.Persona;


/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class BancaVirtualDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	 private EntityManager em;

	    
	 	public void insert(BancaVirtual banca) {
	 em.persist(banca);
	 	}

	 	
	 	public void update(BancaVirtual banca)   {
	 em.merge(banca);
	 	}

	 	
	 	public BancaVirtual read(int id) {
			return em.find(BancaVirtual.class, id);
		}

		public void delete(int id) {
			BancaVirtual bv = read(id);
			em.remove(bv);
		}

	
	 	
		public List<BancaVirtual> getBanca(String filtro) {
			String jpql = " SELECT bv FROM BancaVirtual bv WHERE id_banca LIKE :filtro";
			Query q = em.createQuery(jpql, BancaVirtual.class);
			q.setParameter("filtro", filtro);
			return q.getResultList();
		}
	 	
	 }
