package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Transaccion;
import appdis.ProyectoFinal.modelo.Transferencia;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class TransaccionDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	 private EntityManager em;

	    
	 	public void insert(Transaccion transaccion) {
	 em.persist(transaccion);
	 	}

	 	
	 	public void update(Transaccion transaccion)   {
	 em.merge(transaccion);
	 	}

	 	
		public Transaccion read(int id) {
			return em.find(Transaccion.class, id);
		}

		public void delete(int id) {
			Transaccion tra = read(id);
			em.remove(tra);
		}

	 	
		@SuppressWarnings("unchecked")
		public List<Transaccion> getTransaccion(int filtro) {
			String jpql = "SELECT tra FROM Transaccion tra WHERE tra.cuenta.id_cuenta LIKE :filtro";

<<<<<<< HEAD
		//	Query q = em.createQuery(jpql, Transferencia.class);
=======
>>>>>>> 37f59b178b53d8a3dff46d18f969a154da3344f0

			Query q = em.createQuery(jpql, Transaccion.class);

			q.setParameter("filtro", filtro);
			return q.getResultList();
		}
	 	
		
		
		
		
		
		
		
		
		
		
}

