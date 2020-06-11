package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Notificaciones;



/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class NotificacionesDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	 private EntityManager em;

	    
	 	public void insert(Notificaciones notificaciones) {
	 em.persist(notificaciones);
	 	}

	 	
	 	public void update(Notificaciones notificaciones)   {
	 em.merge(notificaciones);
	 	}

	 	
	 	public Notificaciones read(int id) {
			return em.find(Notificaciones.class, id);
		}

		public void delete(int id) {
			Notificaciones nt = read(id);
			em.remove(nt);
		}

	
	 	
		@SuppressWarnings("unchecked")
		public List<Notificaciones> getNotificaciones(int filtro) {
			String jpql = "SELECT nt FROM Notificaciones nt WHERE nt.cliente.id_cliente = :filtro";
			Query q = em.createQuery(jpql, Notificaciones.class);
			q.setParameter("filtro", filtro);
			return q.getResultList();
		}
	 	
	 }
