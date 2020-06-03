package appdis.ProyectoFinal.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import appdis.ProyectoFinal.dao.BancaVirtualDao;
import appdis.ProyectoFinal.dao.ClienteDao;
import appdis.ProyectoFinal.dao.CreditoDao;
import appdis.ProyectoFinal.dao.CuentaDao;
import appdis.ProyectoFinal.dao.InstituicionFinancieraDao;
import appdis.ProyectoFinal.dao.NotificacionesDao;
import appdis.ProyectoFinal.dao.PersonaDao;
import appdis.ProyectoFinal.dao.SolicitudCreditoDao;
import appdis.ProyectoFinal.dao.TelefonosDao;
import appdis.ProyectoFinal.dao.TransferenciaDao;
import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.BancaVirtual;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Credito;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.InstitucionFinanciera;
import appdis.ProyectoFinal.modelo.Notificaciones;
import appdis.ProyectoFinal.modelo.Persona;
import appdis.ProyectoFinal.modelo.SolicitudCredito;
import appdis.ProyectoFinal.modelo.Telefonos;
import appdis.ProyectoFinal.modelo.Transferencia;



@Stateless
public class gestionON implements DaoProyectoLocal{

	@Inject
	BancaVirtualDao  bvdao;

	@Inject
	ClienteDao cldao;
	
	@Inject
	CreditoDao crdao;

	
	@Inject
	CuentaDao  cudao;
    
	@Inject
	InstituicionFinancieraDao infdao;
	
	@Inject
	NotificacionesDao notdao;
	
	@Inject
	PersonaDao  pedao;

	@Inject
	SolicitudCreditoDao soldao;
	
	@Inject
	TelefonosDao telfdao;
	
	@Inject
	TransferenciaDao trdao;
	
	
	
	
	
	
	

	public void guardarBanca(BancaVirtual bv) throws Exception {

		BancaVirtual aux = bvdao.read(bv.getId_banca());

		if (aux != null) {
			bvdao.update(bv);
		} else {
			bvdao.insert(bv);
		}
	}

	public void actualizarBanca(BancaVirtual bv) throws Exception {

		BancaVirtual  aux = bvdao.read(bv.getId_banca());

		if (aux != null) {
			bvdao.update(bv);
		} else {
			bvdao.insert(bv);
		}
	}

	public  List<BancaVirtual> buscarBanca(int id) throws Exception {
		return bvdao.getBanca(id + "%");
	}

	public void eliminarBanca(int id) throws Exception {
		bvdao.delete(id);
		
	}



	
	public void guardarCliente(Cliente cl) throws Exception {

		Cliente aux = cldao.read(cl.getId_cliente());

		if (aux != null) {
			cldao.update(cl);
		} else {
			cldao.insert(cl);
		}
	}

	public void actualizarCliente(Cliente cl) throws Exception {

		Cliente aux = cldao.read(cl.getId_cliente());

		if (aux != null) {
			cldao.update(cl);
		} else {
			cldao.insert(cl);
		}
	}
	
	public  List<Cliente> buscarCliente(int id) throws Exception {
		return cldao.getCliente(id + "%");
		
	}

	public void eliminarCliente(int id) throws Exception {
		cldao.delete(id);
	}

	
	
	
	
	public void guardarCredito(Credito cr) throws Exception {

		Credito aux = crdao.read(cr.getId_credito());

		if (aux != null) {
			crdao.update(cr);
		} else {
			crdao.insert(cr);
		}
	}

	public void actualizarCredito(Credito cr) throws Exception {

		Credito aux = crdao.read(cr.getId_credito());

		if (aux != null) {
			crdao.update(cr);
		} else {
			crdao.insert(cr);
		}
	}

	public  List<Credito> buscarCredito(int id) throws Exception {
		return crdao.getCredito(id + "%");
		
	}

	public void eliminarCredito(int id) throws Exception {
		crdao.delete(id);
	}


	
	

	

}
