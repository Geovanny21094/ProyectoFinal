
package appdis.ProyectoFinal.negocio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;

import appdis.ProyectoFinal.dao.AmortizacionDao;
import appdis.ProyectoFinal.dao.BancaVirtualDao;
import appdis.ProyectoFinal.dao.ClienteDao;
import appdis.ProyectoFinal.dao.CreditoDao;
import appdis.ProyectoFinal.dao.CuentaDao;
import appdis.ProyectoFinal.dao.CuentasDestinoDao;
import appdis.ProyectoFinal.dao.EnviarCorreo;
import appdis.ProyectoFinal.dao.InstitucionFinancieraDao;
import appdis.ProyectoFinal.dao.NotificacionesDao;
import appdis.ProyectoFinal.dao.PersonaDao;
import appdis.ProyectoFinal.dao.RolDao;
import appdis.ProyectoFinal.dao.SolicitudCreditoDao;
import appdis.ProyectoFinal.dao.TelefonosDao;
import appdis.ProyectoFinal.dao.TransaccionDao;
import appdis.ProyectoFinal.dao.TransferenciaDao;
import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Amortizacion;
import appdis.ProyectoFinal.modelo.BancaVirtual;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Credito;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.CuentasDestino;
import appdis.ProyectoFinal.modelo.InstitucionFinanciera;
import appdis.ProyectoFinal.modelo.Notificaciones;
import appdis.ProyectoFinal.modelo.Persona;
import appdis.ProyectoFinal.modelo.Rol;
import appdis.ProyectoFinal.modelo.SolicitudCredito;
import appdis.ProyectoFinal.modelo.Telefonos;
import appdis.ProyectoFinal.modelo.Transaccion;
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

	@Inject
	TransaccionDao tradao;

	@Inject
	CuentasDestinoDao cueDesDao;

	@Inject
	AmortizacionDao AmoDao;

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

	public void enviarCorreo1(String asunto, String mensaje, String correoDestino, String archivo) throws Exception {

		if (asunto != "" && mensaje != "" && correoDestino != "") {
			enviarCorreo.enviarMail1(asunto, mensaje, correoDestino, archivo);
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

		cldao.update(cl);

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
		}
	}

	public List<Credito> buscarCreditos() throws Exception {
		return crdao.getCreditos();
	}

	public List<Credito> buscarCreditosAprobar() throws Exception {
		return crdao.getCreditosAprobar();
	}

	
	public List<Credito> buscarCreditoCuenta(int idCuenta) throws Exception {
		return crdao.getCreditosCuenta(idCuenta);

	}
	
	public Credito buscarCreditos(int idCuenta) throws Exception{
		Credito aux = crdao.getCredito(idCuenta);
		return aux;
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

	public Cuenta buscarCuenta(int idCliente) throws Exception {
		Cuenta aux = cudao.getCuentaL(idCliente);
		return aux;
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

	public List<Cuenta> listarCuentas() throws Exception {
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
		return notdao.getNotificaciones(id);

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
		pedao.update(per);

	}

	public Persona buscarPersonaa(String cedula) throws Exception {
		Persona aux = pedao.read(cedula);
		return aux;
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

	/* Transaccion */
	public void guardarTransaccion(Transaccion tra) throws Exception {

		Transaccion aux = tradao.read(tra.getId_transaccion());

		if (aux != null) {
			tradao.update(tra);
		} else {
			tradao.insert(tra);
		}
	}

	public void actualizarTransaccion(Transaccion tra) throws Exception {

		Transaccion aux = tradao.read(tra.getId_transaccion());

		if (aux != null) {
			tradao.update(tra);
		} else {
			tradao.insert(tra);
		}
	}

	public List<Transaccion> buscarTransaccionDias(String numeroCuenta) throws Exception {
		return tradao.getTransaccioFiltron(numeroCuenta);
	}

	public List<Transaccion> buscarTransaccionDias2(String numeroCuenta, Date fechaIni, Date fechaFin)
			throws Exception {
		return tradao.getTransaccioFiltron2(numeroCuenta, fechaIni, fechaFin);
	}

	public List<Transaccion> buscarTransaccion(int id) throws Exception {
		return tradao.getTransaccion(id);
	}

	public void eliminarTransaccion(int id) throws Exception {
		tradao.delete(id);
	}

	public void guardarCuentaDestino(CuentasDestino cuenDes) throws Exception {
		CuentasDestino aux = cueDesDao.read(cuenDes.getId());

		if (aux != null) {
			cueDesDao.update(cuenDes);
		} else {
			cueDesDao.insert(cuenDes);
		}
	}

	public void actualizarCuentaDestino(CuentasDestino cuenDes) throws Exception {
		CuentasDestino aux = cueDesDao.read(cuenDes.getId());

		if (aux != null) {
			cueDesDao.update(cuenDes);
		} else {
			cueDesDao.insert(cuenDes);
		}
	}

	public CuentasDestino buscarCuentaDestino(String numeroCuentaDestinatario) throws Exception {
		CuentasDestino aux = cueDesDao.getCuenta(numeroCuentaDestinatario);
		return aux;
	}

	public List<CuentasDestino> buscarCuentasDestino(String cuentaOrigen) throws Exception {
		return cueDesDao.getCuentas(cuentaOrigen);
	}

	public void guardarAmortizacion(Amortizacion amortizacion) throws Exception {
		Amortizacion aux = AmoDao.read(amortizacion.getId_Amoritizacion());

		if (aux != null) {
			AmoDao.update(amortizacion);
		} else {
			AmoDao.insert(amortizacion);
		}
	}

	public void actualizarAmortizacion(Amortizacion amortizacion) throws Exception {
		Amortizacion aux = AmoDao.read(amortizacion.getId_Amoritizacion());

		if (aux != null) {
			AmoDao.update(amortizacion);
		} else {
			AmoDao.insert(amortizacion);
		}
	}

	public List<Amortizacion> buscarAmortizaciones(int id_credito) throws Exception {
		return AmoDao.getAmortizaciones(id_credito);
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

	public void eliminarRol(String cedula) throws Exception {
		rdao.deleteRol(cedula);
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

	public void categorizacion(Credito c) throws Exception {

		String DNI = c.getCuenta().getCliente().getPersona().getCedula();
		String numeroCuenta=c.getCuenta().getNumeroCuenta();
		int plazoCreditos=c.getCuotas();
		int montoCredito=(int) c.getMonto();
		int tasaPago=(int) c.getTasaPostpago();
		int avaluoVivienda=(int) c.getAvaluo();
		int edad=c.getEdad();
		int creditosExistentes=c.getCreditosExistentes();
		
		
		/*
		 * A30: no se tomaron créditos A31: todos los créditos en este banco se
		 * pagarondebidamente A32: créditos existentes devueltos debidamente hasta ahora
		 * A33:retraso en pagar en el pasado A34: cuenta crítica / otros créditos
		 * existentes(no en este banco)
		 */
		String A30 = "A30";
		String A31 = "A31";
		String A32 = "A32";
		String A33 = "A33";
		String A34 = "A34";

		String historialCreditos = c.getEstadoCredito();
		String historialCreditosReturn = "";

		if (historialCreditos.equalsIgnoreCase("NoCreditos")) {
			historialCreditosReturn = A30;
		}

		if (historialCreditos.equalsIgnoreCase("Pagados")) {
			historialCreditosReturn = A31;
		}

		if (historialCreditos.equalsIgnoreCase("Devueltos")) {
			historialCreditosReturn = A32;
		}
		if (historialCreditos.equalsIgnoreCase("Retraso")) {
			historialCreditosReturn = A33;
		}

		if (historialCreditos.equalsIgnoreCase("Critica")) {
			historialCreditosReturn = A34;
		}

		/*
		 * A40. inmuebles (casa, finca, etc) A41: automovil A42: muebles / equipamiento
		 * A43: tecnología A44: electrodomésticos A45: reparaciones A46: educación A47:
		 * vacaciones A48: capacitación A49: negocios A410: otros
		 */
		String A40 = "A40";
		String A41 = "A41";
		String A42 = "A42";
		String A43 = "A43";
		String A44 = "A44";
		String A45 = "A45";
		String A46 = "A46";
		String A47 = "A47";
		String A48 = "A48";
		String A49 = "A49";
		String A410 = "A410";

		String propositoCredito = c.getPropositoCredito();
		String propositoCreditoReturn = "";

		if (propositoCredito.equalsIgnoreCase("inmuebles")) {
			propositoCreditoReturn = A40;
		}

		if (propositoCredito.equalsIgnoreCase("automovil")) {
			propositoCreditoReturn = A41;
		}

		if (propositoCredito.equalsIgnoreCase("muebles")) {
			propositoCreditoReturn = A42;
		}
		if (propositoCredito.equalsIgnoreCase("tecnologia")) {
			propositoCreditoReturn = A43;
		}

		if (propositoCredito.equalsIgnoreCase("electrodomesticos")) {
			propositoCreditoReturn = A44;
		}

		if (propositoCredito.equalsIgnoreCase("reparaciones")) {
			propositoCreditoReturn = A45;
		}

		if (propositoCredito.equalsIgnoreCase("eduacion")) {
			propositoCreditoReturn = A46;
		}

		if (propositoCredito.equalsIgnoreCase("vacaciones")) {
			propositoCreditoReturn = A47;
		}
		if (propositoCredito.equalsIgnoreCase("capacitacion")) {
			propositoCreditoReturn = A48;
		}

		if (propositoCredito.equalsIgnoreCase("negocio")) {
			propositoCreditoReturn = A49;
		}

		if (propositoCredito.equalsIgnoreCase("otros")) {
			propositoCreditoReturn = A410;
		}

		/*
		 * A61: 0 <= ... <500 USD A62: 500 <= ... <1000 USD A63: 1000 <= ... <1500 USD
		 * A64: ...> = 1500 USD A65: sin cuenta de ahorros
		 */
		String A61 = "A61";
		String A62 = "A62";
		String A63 = "A63";
		String A64 = "A64";
//		String A65 = "A65";

		double saldoCuentaAhorros = c.getSaldo();
		String saldoCuentaAhorrosReturn = "";

		if ((saldoCuentaAhorros >= 0) && (saldoCuentaAhorros < 500)) {
			saldoCuentaAhorrosReturn = A61;
		}

		if ((saldoCuentaAhorros >= 500) && (saldoCuentaAhorros < 100)) {
			saldoCuentaAhorrosReturn = A62;
		}

		if ((saldoCuentaAhorros >= 1000) && (saldoCuentaAhorros < 1500)) {
			saldoCuentaAhorrosReturn = A63;
		}
		if (saldoCuentaAhorros >= 1500) {
			saldoCuentaAhorrosReturn = A64;
		}

		/*
		 * A71: desempleado A72: ... <1 año A73: 1 <= ... <4 años A74: 4 <= ... <7 años
		 * A75: ..> = 7 años
		 */
		String A71 = "A71";
		String A72 = "A72";
		String A73 = "A73";
		String A74 = "A74";
		String A75 = "A75";

		String tiempoEmpleo = c.getTipoEmpleo();
		String tiempoEmpleoReturn = "";
		
		if (tiempoEmpleo.equalsIgnoreCase("desempleado")) {
			tiempoEmpleoReturn = A71;
		}

		if (tiempoEmpleo.equalsIgnoreCase("Menos de 1 anio")) {
			tiempoEmpleoReturn = A72;
		}

		if (tiempoEmpleo.equalsIgnoreCase("De 1 anio a 4 anio")) {
			tiempoEmpleoReturn = A73;
		}

		if (tiempoEmpleo.equalsIgnoreCase("De 4  a 7 anio")) {
			tiempoEmpleoReturn = A74;
		}
		if (tiempoEmpleo.equalsIgnoreCase("Mas de 7 años")) {
			tiempoEmpleoReturn = A75;
		}

		/*
		 * A91: masculino: divorciado/separado A92: femenino:
		 * dirvorciada/separada/casada A93: masculino: soltero A94: masculino:
		 * casado/viudo A95: femenino: soltera
		 */
		String A91 = "A91";
		String A92 = "A92";
		String A93 = "A93";
		String A94 = "A94";
		String A95 = "A95";

		String estadoCivilSexo = c.getEstadoCivil();
		String estadoCivilSexoReturn = "";

		if (estadoCivilSexo.equalsIgnoreCase("MasculinoDivorciado/Separado")) {
			estadoCivilSexoReturn = A91;
		}

		if (estadoCivilSexo.equalsIgnoreCase("FemeninoDivorciado/Separado")) {
			estadoCivilSexoReturn = A92;
		}

		if (estadoCivilSexo.equalsIgnoreCase("MasculinoSoltero")) {
			estadoCivilSexoReturn = A93;
		}

		if (estadoCivilSexo.equalsIgnoreCase("MasculinoCasado/Viudo")) {
			estadoCivilSexoReturn = A94;
		}

		if (estadoCivilSexo.equalsIgnoreCase("FemeninoSoltero")) {
			estadoCivilSexoReturn = A95;
		}

		/*
		 * A101: ninguno A102: co-aplicante A103: garante
		 */
		String A101 = "A101";
		String A102 = "A102";
		String A103 = "A103";

		String garante = c.getGarante();
		String garanteReturn = "";

		if (garante.equalsIgnoreCase("ninguno")) {
			garanteReturn = A101;
		}

		if (garante.equalsIgnoreCase("co-aplicante")) {
			garanteReturn = A102;
		}

		if (garante.equalsIgnoreCase("garante")) {
			garanteReturn = A103;
		}

		/*
		 * A121: Bienes inmuebles A122: Si no A121: Seguro de vida y plan de
		 * construcción A123: Si no A121/A122: automovil u otro A124: desconocido / sin
		 * propiedad
		 */
		String A121 = "A121";
		String A122 = "A122";
		String A123 = "A123";
		String A124 = "A124";

		String activos = c.getActivos();
		String activosReturn = "";

		if (activos.equalsIgnoreCase("Bienes Inmuebles")) {
			activosReturn = A121;
		}

		if (activos.equalsIgnoreCase("Seguro de vida y plan de construccion")) {
			activosReturn = A122;
		}

		if (activos.equalsIgnoreCase("Automovil u otros")) {
			activosReturn = A123;
		}

		if (activos.equalsIgnoreCase("Desconocidos / sin propiedad")) {
			activosReturn = A123;
		}

		/*
		 * A151: gratis A152: alquiler A153: propio
		 */
		String A151 = "A151";
		String A152 = "A152";
		String A153 = "A153";

		String vivienda = c.getVivienda();
		String viviendaReturn = "";

		if (vivienda.equalsIgnoreCase("Gratis")) {
			viviendaReturn = A151;
		}

		if (vivienda.equalsIgnoreCase("Alquilada")) {
			viviendaReturn = A152;
		}

		if (vivienda.equalsIgnoreCase("Propia")) {
			viviendaReturn = A153;
		}

		/*
		 * A171: desempleado A172: jubilado A173: empleado A174: autónomo
		 */
		String A171 = "A171";
		String A172 = "A172";
		String A173 = "A173";
		String A174 = "A174";

		String empleo = c.getEmpleo();
		String empleoReturn = "";

		if (empleo.equalsIgnoreCase("Desempleado")) {
			empleoReturn = A171;
		}

		if (empleo.equalsIgnoreCase("Empleado")) {
			empleoReturn = A172;
		}

		if (empleo.equalsIgnoreCase("Jubilado")) {
			empleoReturn = A173;
		}

		if (empleo.equalsIgnoreCase("Autonomo")) {
			empleoReturn = A174;
		}

		/*
		 * A201: sí A202: no
		 */
		String A201 = "A201";
		String A202 = "A202";

		String trabajodorExtranjero = c.getTrabajodorExtranjero();
		String trabajodorExtranjeroReturn = "";

		if (trabajodorExtranjero.equalsIgnoreCase("Si")) {
			trabajodorExtranjeroReturn = A201;
		}

		if (trabajodorExtranjero.equalsIgnoreCase("No")) {
			trabajodorExtranjeroReturn = A202;
		}

		generarCSV(DNI, plazoCreditos, historialCreditosReturn, propositoCreditoReturn, montoCredito, saldoCuentaAhorrosReturn,
				tiempoEmpleoReturn, tasaPago, estadoCivilSexoReturn, garanteReturn, avaluoVivienda, activosReturn, edad,
				viviendaReturn, creditosExistentes, empleoReturn, trabajodorExtranjeroReturn, numeroCuenta);
	}

	public void generarCSV(String DNI, int plazoMesesCreditos, String hitorialCredito, String propositoCredito,
			int montoCredito, String saldoCuentaAhorros, String tiempoEmpleo, int tasaPago,
			String estadoCivilSexo, String garante, int avaluoVivienda, String activos, int edad, String vivienda,
			int cantidadCreditosExistentes, String empleo, String trabajadorExtranjero, String numeroCuenta) throws Exception {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("/Users/italomendieta/Desktop/ArchivosDMR/" + numeroCuenta + ".csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		String ColumnNamesList = "DNI;" + "PLAZOMESESCREDITO;" + "HISTORIALCREDITO;" + "PROPOSITOCREDITO;"
				+ "MONTOCREDITO;" + "SALDOCUENTAAHORROS;" + "TIEMPOEMPLEO;" + "TASAPAGO;" + "ESTADOCIVILYSEXO;"
				+ "GARANTE;" + "AVALUOVIVIENDA;" + "ACTIVOS;" + "EDAD;" + "VIVIENDA;" + "CANTIDADCREDITOSEXISTENTES;"
				+ "EMPLEO;" + "TRABAJADOREXTRANJERO;" + "TIPOCLIENTE";

		builder.append(ColumnNamesList + "\n");
		builder.append(DNI + ";" + plazoMesesCreditos + ";" + hitorialCredito + ";" + propositoCredito + ";"
				+ montoCredito + ";" + saldoCuentaAhorros + ";" + tiempoEmpleo + ";" + tasaPago + ";" + estadoCivilSexo
				+ ";" + garante + ";" + avaluoVivienda + ";" + activos + ";" + edad + ";" + vivienda + ";"
				+ cantidadCreditosExistentes + ";" + empleo + ";" + trabajadorExtranjero + ";" + 1 + ";");
		pw.write(builder.toString());
		pw.close();
		System.out.println("done!");
	}

}
