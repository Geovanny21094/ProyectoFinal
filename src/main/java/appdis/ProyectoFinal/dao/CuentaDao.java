package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Cuenta;



/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class CuentaDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	 private EntityManager em;

	    
	 	public void insert(Cuenta cuenta) {
	 em.persist(cuenta);
	 	}

	 	
	 	public void update(Cuenta cuenta)   {
	 em.merge(cuenta);
	 	}

	 	
	 	public Cuenta read(int id) {
			return em.find(Cuenta.class, id);
		}

		public void delete(int id) {
			Cuenta cu = read(id);
			em.remove(cu);
		}

	
	 	
		public List<Cuenta> getCuenta(String filtro) {
			String jpql = " SELECT cu FROM cuenta cu WHERE id_cuenta LIKE :filtro";
			Query q = em.createQuery(jpql, Cuenta.class);
			q.setParameter("filtro", filtro);
			return q.getResultList();
		}
	 	
	 }

	
	
	
	
	
	
	
	