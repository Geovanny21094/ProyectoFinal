package appdis.ProyectoFinal.dao;

import java.sql.Date;
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

	public void update(Transaccion transaccion) {
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



		//	Query q = em.createQuery(jpql, Transferencia.class);


		//	Query q = em.createQuery(jpql, Transferencia.class);


		Query q = em.createQuery(jpql, Transaccion.class);

		q.setParameter("filtro", filtro);
		return q.getResultList();
	}

//	public List<Transaccion> getTransaccioFiltro(String username) {
//		return em.createQuery("select d from Transaccion d where d.cuenta.numeroCuenta = :username and d.Fecha BETWEEN (CURRENT_DATE() - INTERVAL 1 MONTH) AND CURRENT_DATE()").setParameter("username", username).getResultList();
//	}

	public List<Transaccion> getTransaccioFiltron(String numeroCuenta) {
//			String jpql = "SELECT s FROM Transaccion s WHERE s.cuenta.numeroCuenta= :numeroCuenta AND s.Fecha = CURRENT_DATE() -30";
		String jpql = "SELECT e FROM Transaccion e WHERE cuenta.numeroCuenta= :numeroCuenta AND e.Fecha BETWEEN '2020-05-10' AND '2020-06-29' order by e.Fecha";
		Query q = em.createQuery(jpql, Transaccion.class);

			q.setParameter("numeroCuenta", numeroCuenta);
		return q.getResultList();
	}
	
	public List<Transaccion> getTransaccioFiltron2(String numeroCuenta, Date fechaIni, Date fechaFin) {
//		String jpql = "SELECT s FROM Transaccion s WHERE s.cuenta.numeroCuenta= :numeroCuenta AND s.Fecha = CURRENT_DATE() -30";
	String jpql = "SELECT e FROM Transaccion e WHERE cuenta.numeroCuenta= :numeroCuenta AND e.Fecha BETWEEN :fechaIni AND :fechaFin";
	Query q = em.createQuery(jpql, Transaccion.class);

	q.setParameter("numeroCuenta", numeroCuenta);
	q.setParameter("fechaIni", fechaIni);
	q.setParameter("fechaFin", fechaFin);
	return q.getResultList();



<<<<<<< HEAD
		//	q.setParameter("filtro", filtro);
			return q.getResultList();
		}
=======
//			q.setParameter("filtro", filtro);
//			return q.getResultList();
//		}
>>>>>>> 557a18322c9594bd9e6dce748286a6b719467b55
	 	
		
	
		
		
}		
		
		
		


