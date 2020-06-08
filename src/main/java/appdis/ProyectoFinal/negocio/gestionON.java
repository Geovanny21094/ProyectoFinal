package appdis.ProyectoFinal.negocio;

import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;

import appdis.ProyectoFinal.dao.BancaVirtualDao;
import appdis.ProyectoFinal.dao.ClienteDao;
import appdis.ProyectoFinal.dao.CreditoDao;
import appdis.ProyectoFinal.dao.CuentaDao;
import appdis.ProyectoFinal.dao.EnviarCorreo;
import appdis.ProyectoFinal.dao.InstitucionFinancieraDao;
import appdis.ProyectoFinal.dao.NotificacionesDao;
import appdis.ProyectoFinal.dao.PersonaDao;
import appdis.ProyectoFinal.dao.RolDao;
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
import appdis.ProyectoFinal.modelo.Rol;
import appdis.ProyectoFinal.modelo.SolicitudCredito;
import appdis.ProyectoFinal.modelo.Telefonos;
import appdis.ProyectoFinal.modelo.Transferencia;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Stateless
public class gestionON implements DaoProyectoLocal {

	@Inject
	BancaVirtualDao bvdao;

	@Inject
	ClienteDao cldao;

	@Inject
	CreditoDao crdao;

	@Inject
	CuentaDao cudao;

	@Inject
	EnviarCorreo enviarCorreo;

	@Inject
	InstitucionFinancieraDao infdao;

	@Inject
	NotificacionesDao notdao;

	@Inject
	PersonaDao pedao;

	@Inject
	SolicitudCreditoDao soldao;

	@Inject
	TelefonosDao telfdao;

	@Inject
	TransferenciaDao trdao;

	@Inject
	RolDao rdao;

	/* Banca Virtual */
	public void guardarBanca(BancaVirtual bv) throws Exception {

		BancaVirtual aux = bvdao.read(bv.getId_banca());

		if (aux != null) {
			bvdao.update(bv);
		} else {
			bvdao.insert(bv);
		}
	}

	public void actualizarBanca(BancaVirtual bv) throws Exception {

		BancaVirtual aux = bvdao.read(bv.getId_banca());

		if (aux != null) {
			bvdao.update(bv);
		} else {
			bvdao.insert(bv);
		}
	}

	public void enviarCorreo(String asunto, String mensaje, String correoDestino) throws Exception {

		if (asunto != "" && mensaje != "" && correoDestino != "") {
			enviarCorreo.enviarMail(asunto, mensaje, correoDestino);
		}

	}

	public List<BancaVirtual> buscarBanca(int id) throws Exception {
		return bvdao.getBanca(id + "%");
	}

	public void eliminarBanca(int id) throws Exception {
		bvdao.delete(id);

	}

	/* Cliente */
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

	public List<Cliente> buscarCliente(int id) throws Exception {
		return cldao.getCliente(id + "%");

	}

	public Cliente buscarCliente(String cedula) throws Exception {
		return cldao.readCedula(cedula);

	}
	
	public List<Cliente> listarClinetes() throws Exception {
		return cldao.getClientes();
	}

	public void eliminarCliente(int id) throws Exception {
		cldao.delete(id);
	}

	public boolean isValidUserPassC(String user, String pass) throws Exception {
		Cliente aux = cldao.getUserPass(user, pass);
		if (aux != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isValidUserPassR(String user, String pass) throws Exception {
		Rol aux = rdao.getUserPass(user, pass);
		if (aux != null) {
			return true;
		} else {
			return false;
		}
	}

	public Cliente getCorreo(String user) throws Exception {
		Cliente aux = cldao.getCorreo(user);
		return aux;
	}

	public String getUser(Cliente cl) throws Exception {

		String user = "";

		Random r = new Random();
		int num = r.nextInt(9999);

		String nombre = cl.getPersona().getNombres();
		String apellido = cl.getPersona().getApellidos();

		String a = nombre.substring(0, 1).toLowerCase();
		String b = apellido.substring(0, apellido.length()).toLowerCase();

		user = a + b + num;

		cl.setUsuario(user);

		return user;
	}

	public String getPassword(Cliente cl) throws Exception {

		String NUMEROS = "0123456789";

		String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

		String pass = "";

		String key = NUMEROS + MAYUSCULAS + MINUSCULAS;

		for (int i = 0; i < 8; i++) {
			pass += (key.charAt((int) (Math.random() * key.length())));
		}
		cl.setContrasenia(pass);

		return pass;
	}

	/* Credito */
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

	public List<Credito> buscarCredito(int id) throws Exception {
		return crdao.getCredito(id + "%");

	}

	public void eliminarCredito(int id) throws Exception {
		crdao.delete(id);
	}

	/* Cuenta */
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

	public String numeroCuenta() throws Exception {
		String numCuenta = "";
		String IDENTIFICADOR = "C000";
		int id = 0;

		List<Cuenta> cuentas = cudao.getCuenta();

		for (Cuenta cu : cuentas) {
			id = cu.getId_cuenta();
		}
		id++;

		return IDENTIFICADOR + id;
	}
	
	public List<Cuenta> listarCuentas() throws Exception{
		return cudao.getCuenta();
	}


	public Cuenta buscarCuenta(String numeroCuenta) throws Exception {
		Cuenta aux = cudao.getCuenta(numeroCuenta);
		return aux;
		
	}

	public void eliminarCuenta(int id) throws Exception {
		cudao.delete(id);
	}

	/* Intitucion Financiera */
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

	public List<InstitucionFinanciera> buscarInstitucion(int id) throws Exception {
		return infdao.getInstitucion(id + "%");

	}

	public void eliminarInstitucion(int id) throws Exception {
		infdao.delete(id);
	}

	/* Notificaciones */
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

	public List<Notificaciones> buscarNotificaciones(int id) throws Exception {
		return notdao.getNotificaciones(id + "%");

	}

	public void eliminarNotificaciones(int id) throws Exception {
		notdao.delete(id);
	}

	/* Persona */
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

	public List<Persona> buscarPersona(String cedula) throws Exception {
		return pedao.getPersona(cedula + "%");
	}

	public void eliminarPersona(String cedula) throws Exception {
		pedao.delete(cedula);

	}

	/* SolicitudCredito */
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

	public List<SolicitudCredito> buscarSolicitud(int id) throws Exception {
		return soldao.getSolicitud(id + "%");

	}

	public void eliminarSolicitud(int id) throws Exception {
		soldao.delete(id);
	}

	/* Telefonos */
	public void guardarTelefonos(Telefonos telf) throws Exception {

		Telefonos aux = telfdao.read(telf.getId_telf());

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

	public List<Telefonos> buscarTelefono(int id) throws Exception {
		return telfdao.getTelefonos(id + "%");

	}

	public void eliminarTelefono(int id) throws Exception {
		telfdao.delete(id);
	}

	/* Transferencia */
	public void guardarTransferencia(Transferencia tr) throws Exception {

		Transferencia aux = trdao.read(tr.getId_transferencia());

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

	public List<Transferencia> buscarTrasnferencia(int id) throws Exception {
		return trdao.getTransferencia(id + "%");

	}

	public void eliminarTransferencia(int id) throws Exception {
		trdao.delete(id);
	}

	/* Rol */
	public void guardarRol(Rol r) throws Exception {

		Rol aux = rdao.read(r.getRol_id());

		if (aux != null) {
			rdao.update(r);
		} else {
			rdao.insert(r);
		}
	}
	
	public List<Rol> listarRol() throws Exception {
		return rdao.getAutores();
	}

	public void actualizarRol(Rol r) throws Exception {

		Rol aux = rdao.read(r.getRol_id());

		if (aux != null) {
			rdao.update(r);
		} else {
			rdao.insert(r);
		}
	}

	public String getUserRol(Rol rol) throws Exception {

		String user = "";

		Random r = new Random();
		int num = r.nextInt(9999);

		String nombre = rol.getPersona().getNombres();
		String apellido = rol.getPersona().getApellidos();

		String a = nombre.substring(0, 1).toLowerCase();
		String b = apellido.substring(0, apellido.length()).toLowerCase();

		user = a + b + num;

		rol.setUsuario(user);

		return user;
	}

	public String getPasswordRol(Rol rol) throws Exception {

		String NUMEROS = "0123456789";

		String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

		String pass = "";

		String key = NUMEROS + MAYUSCULAS + MINUSCULAS;

		for (int i = 0; i < 8; i++) {
			pass += (key.charAt((int) (Math.random() * key.length())));
		}
		rol.setContrasenia(pass);

		return pass;
	}

	public List<Rol> buscarRol(int id) throws Exception {
		return rdao.getRol(id + "%");

	}

	public void eliminarRol(int id) throws Exception {
		rdao.delete(id);
	}

	@Override
	public boolean validarCedula(String cedula) throws Exception {
		boolean cedulaValida = false;

		try {

			if (cedula.length() == 10) {
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {

					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaValida = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaValida = true;
					} else {
						cedulaValida = false;
					}
				} else {
					cedulaValida = false;
				}
			} else {
				cedulaValida = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaValida = false;
		} catch (Exception err) {
			cedulaValida = false;
			throw new Exception("Error al validar cedula");
		}

		if (!cedulaValida) {
			throw new Exception("Cedula Correcta");
		}
		return cedulaValida;
	}

}
