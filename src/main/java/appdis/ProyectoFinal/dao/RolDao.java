package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Rol;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class RolDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	 private EntityManager em;

	    
	 	public void insert(Rol rol) {
	 em.persist(rol);
	 	}

	 	
	 	public void update(Rol rol)   {
	 em.merge(rol);
	 	}

	 	
	 	public Rol read(int id) {
			return em.find(Rol.class, id);
		}

		public void delete(int id) {
			Rol rol = read(id);
			em.remove(rol);
		}
		
		public void deleteRol(String cedula) {
			String jpql = "DELETE FROM Rol r WHERE r.persona.cedula =:cedula";
			Query q = em.createQuery(jpql, Rol.class);
			q.setParameter("cedula", cedula);
			q.executeUpdate();
			em.getTransaction().commit();

		}
		
		public List<Rol> getAutores(){
			String jpql = "SELECT r FROM Rol r";
			
			Query q = em.createQuery(jpql, Rol.class);
			
			return q.getResultList();
		}

		public Rol getUserPass(String user, String pass) {
			String jpql = " SELECT r FROM Rol r WHERE usuario = :user AND contrasenia = :pass";
			Query q = em.createQuery(jpql, Rol.class);
			q.setParameter("user", user);
			q.setParameter("pass", pass);

			return (Rol) q.getSingleResult();
		}
	
	 	
		@SuppressWarnings("unchecked")
		public List<Rol> getRol(String filtro) {
			String jpql = " SELECT r FROM Rol r WHERE id_rol LIKE :filtro";
			Query q = em.createQuery(jpql, Rol.class);
			q.setParameter("filtro", filtro);
			return q.getResultList();
		}
	 	
	 }
