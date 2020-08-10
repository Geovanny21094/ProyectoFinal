package appdis.ProyectoFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import appdis.ProyectoFinal.modelo.CuentasDestino;

@Stateless
public class CuentasDestinoDao {

	@PersistenceContext(name = "testjpaPersistenceUnit")
	private EntityManager em;

	public void insert(CuentasDestino cueDes) {
		if (read(cueDes.getId()) != null) {
			em.persist(cueDes);
		} else {
			update(cueDes);
		}

	}

	public void update(CuentasDestino cueDes) {
		em.merge(cueDes);
	}

	public CuentasDestino read(int id) {
		return em.find(CuentasDestino.class, id);
	}

	public void delete(int id) {
		CuentasDestino cu = read(id);
		em.remove(cu);
	}

	public List<CuentasDestino> listarCuentas() {
		String jpql = "SELECT cuDes FROM CuentasDestino cuDes";
		Query q = em.createQuery(jpql, CuentasDestino.class);
		return q.getResultList();
	}

	public List<CuentasDestino> getCuentas(String cuentaOrigen) {
		String jpql = "SELECT cuDes FROM CuentasDestino cuDes WHERE cuDes.cuenta.numeroCuenta = :cuentaOrigen";
		Query q = em.createQuery(jpql, CuentasDestino.class);
		q.setParameter("cuentaOrigen", cuentaOrigen);
		return q.getResultList();
	}

	public CuentasDestino getCuenta(String numeroCuentaBeneficiario) {
		String jpql = "SELECT cuDes FROM CuentasDestino cuDes WHERE numeroCuentaBeneficiario = :numeroCuentaBeneficiario";
		Query q = em.createQuery(jpql, CuentasDestino.class);
		q.setParameter("numeroCuentaBeneficiario", numeroCuentaBeneficiario);
		return (CuentasDestino) q.getSingleResult();
	}

}
