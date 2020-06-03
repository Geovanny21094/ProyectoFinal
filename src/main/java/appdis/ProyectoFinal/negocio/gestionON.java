package appdis.ProyectoFinal.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import appdis.ProyectoFinal.dao.BancaVirtualDao;
import appdis.ProyectoFinal.dao.ClienteDao;
import appdis.ProyectoFinal.dao.CreditoDao;
import appdis.ProyectoFinal.dao.CuentaDao;
import appdis.ProyectoFinal.dao.InstitucionFinancieraDao;
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
	InstitucionFinancieraDao infdao;
	
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
	
	/*Banca Virtual*/
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



	/*Cliente*/
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

	
	/*Credito*/
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


	
	/*Cuenta*/
	public void guardarCuenta(Cuenta cu) throws Exception {

		Cuenta aux = cudao.read(cu.getId_cuenta());

		if (aux != null) {
			cudao.update(cu);
		} else {
			cudao.insert(cu);
		}
	}

	public void actualizarCuenta(Cuenta cu) throws Exception {

		Cuenta aux = cudao.read(cu.getId_cuenta());

		if (aux != null) {
			cudao.update(cu);
		} else {
			cudao.insert(cu);
		}
	}

	public  List<Cuenta> buscarCuenta(int id) throws Exception {
		return cudao.getCuenta(id + "%");
		
	}

	public void eliminarCuenta(int id) throws Exception {
		cudao.delete(id);
	}


	
	
	/*Intitucion Financiera*/
	public void guardarInstitucion(InstitucionFinanciera inf) throws Exception {

		InstitucionFinanciera aux = infdao.read(inf.getId_inst());

		if (aux != null) {
			infdao.update(inf);
		} else {
			infdao.insert(inf);
		}
	}

	public void actualizarInstitucion(InstitucionFinanciera inf) throws Exception {

	InstitucionFinanciera aux = infdao.read(inf.getId_inst());

		if (aux != null) {
			infdao.update(inf);
		} else {
			infdao.insert(inf);
		}
	}

	public  List<InstitucionFinanciera> buscarInstitucion(int id) throws Exception {
		return infdao.getInstitucion(id + "%");
		
	}

	public void eliminarInstitucion(int id) throws Exception {
		infdao.delete(id);
	}


	/*Notificaciones*/
	public void guardarNotificaciones(Notificaciones not) throws Exception {

		Notificaciones aux = notdao.read(not.getId_not());

		if (aux != null) {
			notdao.update(not);
		} else {
			notdao.insert(not);
		}
	}

	public void actualizarNotificaciones(Notificaciones not) throws Exception {

	Notificaciones aux = notdao.read(not.getId_not());

		if (aux != null) {
			notdao.update(not);
		} else {
			notdao.insert(not);
		}
	}

	public  List<Notificaciones> buscarNotificaciones(int id) throws Exception {
		return notdao.getNotificaciones(id + "%");
		
	}

	public void eliminarNotificaciones(int id) throws Exception {
		notdao.delete(id);
	}


	
	/*Persona*/
	public void guardarPersona(Persona per) throws Exception {

		Persona aux = pedao.read(per.getCedula());

		if (aux != null) {
			pedao.update(per);
		} else {
			pedao.insert(per);
		}
	}

	public void actualizarPersona(Persona per) throws Exception {

	Persona aux = pedao.read(per.getCedula());

		if (aux != null) {
			pedao.update(per);
		} else {
			pedao.insert(per);
		}
	}

	public  List<Persona> buscarPersona(String cedula) throws Exception {
		return pedao.getPersona(cedula + "%");
	}

	public void eliminarPersona(String cedula) throws Exception {
		pedao.delete(cedula);
		
	}


	
	
	/*SolicitudCredito*/
	public void guardarSolicitud(SolicitudCredito sol) throws Exception {

		SolicitudCredito aux = soldao.read(sol.getId_sol());

		if (aux != null) {
			soldao.update(sol);
		} else {
			soldao.insert(sol);
		}
	}

	public void actualizarSolicitud(SolicitudCredito sol) throws Exception {

	SolicitudCredito aux = soldao.read(sol.getId_sol());

		if (aux != null) {
			soldao.update(sol);
		} else {
			soldao.insert(sol);
		}
	}

	public  List<SolicitudCredito> buscarSolicitud(int id) throws Exception {
		return soldao.getSolicitud(id + "%");
		
	}

	public void eliminarSolicitud(int id) throws Exception {
		soldao.delete(id);
	}


	
	
	/*Telefonos*/
	public void guardarTelefonos(Telefonos telf) throws Exception {

		Telefonos aux =   telfdao.read(telf.getId_telf());

		if (aux != null) {
			telfdao.update(telf);
		} else {
			telfdao.insert(telf);
		}
	}

	public void actualizarTelefono(Telefonos telf) throws Exception {

	Telefonos aux = telfdao.read(telf.getId_telf());

		if (aux != null) {
			telfdao.update(telf);
		} else {
			telfdao.insert(telf);
		}
	}

	public  List<Telefonos> buscarTelefono(int id) throws Exception {
		return telfdao.getTelefonos(id + "%");
		
	}

	public void eliminarTelefono(int id) throws Exception {
		telfdao.delete(id);
	}

	

	
	
	/*Transferencia*/
	public void guardarTransferencia(Transferencia tr) throws Exception {

		Transferencia aux =   trdao.read(tr.getId_transferencia());

		if (aux != null) {
			trdao.update(tr);
		} else {
			trdao.insert(tr);
		}
	}

	public void actualizarTransferencia(Transferencia tr) throws Exception {

	Transferencia aux = trdao.read(tr.getId_transferencia());

		if (aux != null) {
			trdao.update(tr);
		} else {
			trdao.insert(tr);
		}
	}

	public  List<Transferencia> buscarTrasnferencia(int id) throws Exception {
		return trdao.getTransferencia(id + "%");
		
	}

	public void eliminarTransferencia(int id) throws Exception {
		trdao.delete(id);
	}

	

	
	
	
	
	
	
}
