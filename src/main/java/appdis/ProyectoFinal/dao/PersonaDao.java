package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Persona;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class PersonaDao {

	@PersistenceContext
	private EntityManager em;

	public void insert(Persona persona) {
		em.persist(persona);
	}

	public void update(Persona persona) {
		em.merge(persona);
	}

	public Persona read(String cedula)   {
 		return em.find(Persona.class, cedula);
 	}

	public void delete(String cedula) {
		Persona pe = read(cedula);
		em.remove(pe);
	}
	

	public List<Persona> getPersona(String filtro) {
		String jpql = " SELECT pe FROM persona pe WHERE cedula_persona LIKE :filtro";
		Query q = em.createQuery(jpql, Persona.class);
		q.setParameter("filtro", filtro);
		return q.getResultList();
	}

}
