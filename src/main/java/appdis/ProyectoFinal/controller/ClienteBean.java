package appdis.ProyectoFinal.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.Part;

import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Amortizacion;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Credito;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.CuentasDestino;
import appdis.ProyectoFinal.modelo.Notificaciones;
import appdis.ProyectoFinal.modelo.Transaccion;
import appdis.ProyectoFinal.modelo.Transferencia;

@ManagedBean
@SessionScoped
public class ClienteBean {

	@Inject
	DaoProyectoLocal ejb;

	private Cliente cl;
	private Cuenta cuenta;
	private Credito credito;
	private CuentasDestino cueDes;
	Transferencia tranferencia;
	private Notificaciones notificaciones;

	private List<CuentasDestino> listCuenDestino = new ArrayList<>();
	private List<Notificaciones> listaNotificaciones;
	private List<Amortizacion> listaAmortizacions;
	private List<Amortizacion> listaAmortizacionsPagadas;
	private List<Transaccion> listaTransacciones;
	private List<SelectItem> nombreCuentas;
	private List<Transaccion> listaTrans;

	private String numeroCuenta;
	private String cadenaCuenta;
	private double saldoCuenta;
	private double parteMonto;

	private int numeroCuenta1;
	private int edad;
	private int creditosExistentes;

	private Date fechaIni;
	private Date fechaFin;

	private String user;
	private String clave;
	private String passwordOld;
	private String passwordNew;

	private Part file;
	private String folder = "/Users/italomendieta/Desktop/ArchivosDMR/";

	@PostConstruct
	public void init() throws Exception {
		cl = new Cliente();
		cuenta = new Cuenta();
		credito = new Credito();
		cueDes = new CuentasDestino();
		tranferencia = new Transferencia();
		notificaciones = new Notificaciones();

		nombresCuentas();

		this.nombreCuentas = nombreCuentas;
		this.edad = edad;
		this.creditosExistentes = creditosExistentes;

	}

	public String isValidCliente() {
		String pag = "";
		Cliente cl;

		try {

			cl = ejb.getCorreo(user);
			if (cl.getUsuario().equals(user) && (cl.getContrasenia().equals(clave))) {

				System.out.println("true");
				ejb.enviarCorreo("INGRESO A CUENTA", "Se ingreso a la BANCA VIRTUAL", cl.getPersona().getCorreo());

				notificaciones.setMensaje_notificacion("Ingreso Satisfactorio");
				notificaciones.setFecha(new java.util.Date());
				notificaciones.setCliente(cl);
				// cl.guardarNotificacion(notificaciones);

				ejb.guardarNotificaciones(notificaciones);

				cuenta = ejb.buscarCuenta(cl.getId_cliente());
				numeroCuenta1 = cuenta.getId_cuenta();
				System.out.println("cedula-> " + cuenta.getId_cuenta());
				pag = "ClienteHome?faces-redirect=true&numeroCuenta=" + numeroCuenta1;

				listaTransacciones = ejb.buscarTransaccion(numeroCuenta1);

			} else {
				System.out.println("false");
				ejb.enviarCorreo("INTENTO DE INGRESO A LA BANCA VIRTUAL",
						"Se intento ingresar a la BANCA VIRTUAL \n" + "Estado FALLIDO", cl.getPersona().getCorreo());

				notificaciones.setMensaje_notificacion("Ingreso Erroneo");
				notificaciones.setFecha(new java.util.Date());
				notificaciones.setCliente(cl);
				// cl.guardarNotificacion(notificaciones);

				ejb.guardarNotificaciones(notificaciones);

				listaTransacciones = ejb.buscarTransaccion(numeroCuenta1);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cl = new Cliente();
		notificaciones = new Notificaciones();
		user = "";
		clave = "";
		return pag;
	}

	/*
	 * Realizar transferencia a Cuenta Externa
	 */
	public void tranferirDineroCuenta() throws Exception {

		Transaccion newTransaccion = new Transaccion();

		double monto = tranferencia.getMonto();
		double saldoAnterior = cuenta.getSaldo();
		if (monto <= saldoAnterior) {
			newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
			newTransaccion.setTipo("Retiro");
			newTransaccion.setMonto(monto);
			try {
				System.out.println(cuenta.getNumeroCuenta());
				newTransaccion.setCuenta(cuenta);
				cuenta.setSaldo(saldoAnterior - monto);
				ejb.actualizarCuenta(cuenta);
				ejb.guardarTransaccion(newTransaccion);

				tranferencia.setCuenta(cuenta);
				tranferencia.setCuenta_destino(cueDes.getNumeroCuentaBeneficiario());
				tranferencia.setFechaTranferencia(new java.util.Date());
				tranferencia.setMonto(monto);

				ejb.guardarTransferencia(tranferencia);

			} catch (

			Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Saldo Insuficinete");
		}
	}

	/*
	 * Metodo para Actualizar las ultimas Transacciones
	 */
	public void actualizarTransacciones() throws Exception {
		numeroCuenta1 = cuenta.getId_cuenta();
		listaTransacciones = ejb.buscarTransaccion(numeroCuenta1);

	}

	public String cerrarSesion() {
		return "login?faces-redirect=true";
	}

	/*
	 * Metodo Lista las Accesos a la Cuenta del Cliente
	 */
	public String accesoCliente(int numeroCuenta) {
		try {
			cuenta = ejb.buscarCuenta(numeroCuenta);

			listaNotificaciones = ejb.buscarNotificaciones(cuenta.getCliente().getId_cliente());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cuenta = new Cuenta();

		return "ClienteAccesos?faces-redirect=true";

	}

	/*
	 * Metodo visualizar la informacion del Cuenta
	 */
	public String datosCliente(int numeroCuenta) {
		try {
			cl = new Cliente();
			System.out.println("Cuneta num-> " + numeroCuenta);
			cuenta = ejb.buscarCuenta(numeroCuenta);

			cl = ejb.buscarCliente(cuenta.getCliente().getPersona().getCedula());

			System.out.println("Cedula-> " + cl.getPersona().getCedula());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ClientePerfil";
	}

	/*
	 * Metodo Lista las transaciones de los ultimos dias
	 */
	public String trasacionesFecha(String numeroCuenta) {

		try {
			System.out.println(numeroCuenta);
			listaTrans = ejb.buscarTransaccionDias(numeroCuenta);

			for (Transaccion tra : listaTrans) {
				System.out.println(tra.getFecha());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "ClienteFiltro?faces-redirect=true";

	}

	/*
	 * Metodo Lista las transaciones dentro de un rango de fechas
	 */
	public String trasacionesFecha2(String numeroCuenta) {

		try {

			System.out.println(numeroCuenta);

			listaTrans = new ArrayList<Transaccion>();
			listaTrans = ejb.buscarTransaccionDias2(numeroCuenta, fechaIni, fechaFin);

			for (Transaccion tra : listaTrans) {
				System.out.println(tra.getFecha());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "ClienteFiltro?faces-redirect=true";

	}

	/*
	 * Redirige hacia pagina Registrar cuenta con el numero de cuenta
	 * Correspondiente
	 */
	public String registrarCuenta() {
		numeroCuenta1 = cuenta.getId_cuenta();
		return "RegistrarCuenta?faces-redirect=true&numeroCuenta=" + numeroCuenta1;
	}

	/*
	 * Redirigue hacia la pagina Tranferencia con el numero de cuenta
	 * correspondiente
	 */
	public String tranferencia() {
		nombresCuentas();
		numeroCuenta1 = cuenta.getId_cuenta();
		return "Transferencia?faces-redirect=true&numeroCuenta=" + numeroCuenta1;
	}

	/*
	 * Redirigue hacia la pagina Tranferencia con el numero de cuenta
	 * correspondiente
	 */
	public String creditoMethod() throws Exception {
//		nombresCuentas();

		credito = new Credito();
		numeroCuenta1 = cuenta.getId_cuenta();

		cuenta = ejb.buscarCuenta(numeroCuenta1);

		double saldoCuenta = cuenta.getSaldo();

		List<Credito> listCredito = ejb.buscarCreditoCuenta(cuenta.getId_cuenta());

		creditosExistentes = listCredito.size();

		String fechaNac = cuenta.getCliente().getPersona().getFecha_nacimiento().toString();
		String fechaAct = new java.util.Date().toLocaleString();
		String anioActual = fechaAct.substring(6, 10);

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
		java.util.Date date = sdf1.parse(fechaNac);
		String anioNacimineto = sdf2.format(date);

		edad = Integer.parseInt(anioActual) - Integer.parseInt(anioNacimineto);

		return "SolicitudCredito?faces-redirect=true&numeroCuenta=" + numeroCuenta1;
	}

	/*
	 * Metodo permite registar cuentas de 3ros para realizar transferencias
	 */
	public String agregarCuentaDestino() throws Exception {

		cuenta = ejb.buscarCuenta(cuenta.getNumeroCuenta());

		cueDes.setCuenta(cuenta);
		ejb.guardarCuentaDestino(cueDes);

		return "";

	}

	/*
	 * Carga en un ComboBox el listado de Cuentas de 3ros agregagos por el cliente,
	 * para realizar tranferencias
	 */
	public void nombresCuentas() {
		try {
			numeroCuenta = cuenta.getNumeroCuenta();
			listCuenDestino = ejb.buscarCuentasDestino(numeroCuenta);
			nombreCuentas = new ArrayList<SelectItem>();

			for (CuentasDestino cuentasDes : listCuenDestino) {
				nombreCuentas.add(new SelectItem(cuentasDes.getNombreBeneficiario() + " - "
						+ cuentasDes.getNumeroCuentaBeneficiario() + " - " + cuentasDes.getInstitucionFinanciera()));
				// nombresAutor.add(new SelectItem(autor.getNombre()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Carga los datos de una Cuenta 3ra Seleccionada en el ComboBox
	 */
	public void cargarDatosCuenta() throws Exception {

		String[] parts = cadenaCuenta.split(" - ");
		String numeroCuentaDestino = parts[1];

		cueDes = ejb.buscarCuentaDestino(numeroCuentaDestino);

	}

	/*
	 * 
	 */
	public void solicitudCredito() throws Exception {

		credito.setCreditosExistentes(creditosExistentes);

		credito.setSaldo(saldoCuenta);

		credito.setEdad(edad);

		credito.setSaldo(cuenta.getSaldo());

		double saldoAnterior = cuenta.getSaldo();

		credito.setFechaCredito(new java.util.Date());

		credito.setCuenta(cuenta);

		credito.setEstadoCredito("Pendiente");

//		upload();

		ejb.guardarCredito(credito);

		ejb.categorizacion(credito);
	}

	public void upload() throws Exception {
		try (InputStream input = file.getInputStream()) {
			cuenta = ejb.buscarCuenta(cuenta.getNumeroCuenta());
			String fileName = file.getName();
			System.out.println(fileName);
			Files.copy(input, new File(folder + cuenta.getNumeroCuenta() + ".pdf").toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String contrasenia() {
		numeroCuenta1 = cuenta.getId_cuenta();
		return "CambiarContrasenia?faces-redirect=true&numeroCuenta=" + numeroCuenta1;
	}

	public void cambiarContrasenia() throws Exception {

		cuenta = ejb.buscarCuenta(cuenta.getNumeroCuenta());

		cl = ejb.buscarCliente(cuenta.getCliente().getPersona().getCedula());

		String passOld = cuenta.getCliente().getContrasenia();

		System.out.println(passOld + " -old");
		if (passOld.equals(passwordOld)) {
			cl.setContrasenia(passwordNew);
			ejb.actualizarCliente(cl);
			ejb.enviarCorreo("CAMBIO DE  A CONTRASEÑA",
					"Se cambiado la contraseña de  la BANCA VIRTUAL \n" + "Su nueva Contraseña es: " + passwordNew + "",
					cl.getPersona().getCorreo());

		} else {
			System.out.println("Contrasena no es correcta");
		}

	}

	public String Amortizacion() throws Exception {
		
		String pag = "";
		numeroCuenta1 = cuenta.getId_cuenta();
//		cuenta = ejb.buscarCuenta(numeroCuenta);
		credito = ejb.buscarCreditos(cuenta.getId_cuenta());

		if ((listaAmortizacions = ejb.buscarAmortizaciones(credito.getId_credito())) != null) {
			pag = "Amortizacion?faces-redirect=true&idCredito=" + credito.getId_credito();
		} else {
			pag = "NoExiste?faces-redirect=true";
		}
		return pag;

	}
	
	public String AmortizacionPagadas() throws Exception {

		String pag = "";
		numeroCuenta1 = cuenta.getId_cuenta();
//		cuenta = ejb.buscarCuenta(numeroCuenta);
		credito = ejb.buscarCreditos(cuenta.getId_cuenta());

		if ((listaAmortizacionsPagadas = ejb.buscarAmortizacionesPagadas(credito.getId_credito())) != null) {
			pag = "AmortizacionPagadas?faces-redirect=true&idCredito=" + credito.getId_credito();
		} else {
			pag = "NoExiste?faces-redirect=true";
		}

		return pag;

	}

	public void pagarAmortizacion(int idAmortizacion) throws Exception {

		Amortizacion amo = ejb.buscarAmortizacion(idAmortizacion);
		double saldo = cuenta.getSaldo();
		double saldoPagar = amo.getValor();
		double saldoTotal;

		if (saldoPagar <= saldo) {

			saldoTotal = saldo - saldoPagar;
			cuenta.setSaldo(saldoTotal);

			amo.setEstado("Pagada");

			ejb.actualizarAmortizacion(amo);
			ejb.actualizarCuenta(cuenta);

			Amortizacion();
		} else {
			System.out.println("No tiene SALDO suficiente");
		}

	}

	public void pagarParteAmortizacion(int idAmortizacion) throws Exception {

		Amortizacion amo = ejb.buscarAmortizacion(idAmortizacion);
		double saldoCue = cuenta.getSaldo();
		double saldoAmor = amo.getValor();
		double saldoTotalCuenta;
		double saldoTotalAmortizacion;

		if (parteMonto <= saldoCue) {

			saldoTotalCuenta = saldoCue - parteMonto;
			saldoTotalAmortizacion=saldoAmor-parteMonto;
			
			cuenta.setSaldo(saldoTotalCuenta);
			
			amo.setValor(saldoTotalAmortizacion);

			ejb.actualizarAmortizacion(amo);
			ejb.actualizarCuenta(cuenta);

			Amortizacion();
		} else {
			System.out.println("No tiene SALDO suficiente");
		}

	}
	
	public void comprobarPagos() throws Exception {
		java.util.Date fechaActual = new java.util.Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		sdformat.format(fechaActual);

		listaAmortizacions = ejb.buscarAmortizaciones(credito.getId_credito());

		for (Amortizacion amo : listaAmortizacions) {

			java.util.Date fechaPago = amo.getFechaPago();
			sdformat.format(fechaPago);

			if (fechaPago.before(fechaActual)) {
				amo.setEstado("Vencida");
				ejb.actualizarAmortizacion(amo);
				
				System.out.println("Cuota num-> " + amo.getNumeroCuota());
			} else {
				System.out.println("no hay cuotas vencidas");
			}
		}
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Cliente getCl() {
		return cl;
	}

	public void setCl(Cliente cl) {
		this.cl = cl;
	}

	public List<Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}

	public void setListaTransacciones(List<Transaccion> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}

	public List<Notificaciones> getListaNotificaciones() {
		return listaNotificaciones;
	}

	public void setListaNotificaciones(List<Notificaciones> listaNotificaciones) {
		this.listaNotificaciones = listaNotificaciones;
	}

	public List<Transaccion> getListaTrans() {
		return listaTrans;
	}

	public void setListaTrans(List<Transaccion> listaTrans) {
		this.listaTrans = listaTrans;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public CuentasDestino getCueDes() {
		return cueDes;
	}

	public void setCueDes(CuentasDestino cueDes) {
		this.cueDes = cueDes;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public List<CuentasDestino> getListCuenDestino() {
		return listCuenDestino;
	}

	public void setListCuenDestino(List<CuentasDestino> listCuenDestino) {
		this.listCuenDestino = listCuenDestino;
	}

	public List<SelectItem> getNombreCuentas() {
		return nombreCuentas;
	}

	public void setNombreCuentas(List<SelectItem> nombreCuentas) {
		this.nombreCuentas = nombreCuentas;
	}

	public String getCadenaCuenta() {
		return cadenaCuenta;
	}

	public void setCadenaCuenta(String cadenaCuenta) {
		this.cadenaCuenta = cadenaCuenta;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Notificaciones getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(Notificaciones notificaciones) {
		this.notificaciones = notificaciones;
	}

	public int getNumeroCuenta1() {
		return numeroCuenta1;
	}

	public void setNumeroCuenta1(int numeroCuenta1) {
		this.numeroCuenta1 = numeroCuenta1;
	}

	public Transferencia getTranferencia() {
		return tranferencia;
	}

	public void setTranferencia(Transferencia tranferencia) {
		this.tranferencia = tranferencia;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getCreditosExistentes() {
		return creditosExistentes;
	}

	public void setCreditosExistentes(int creditosExistentes) {
		this.creditosExistentes = creditosExistentes;
	}

	public double getSaldoCuenta() {
		return saldoCuenta;
	}

	public void setSaldoCuenta(double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	public List<Amortizacion> getListaAmortizacions() {
		return listaAmortizacions;
	}

	public void setListaAmortizacions(List<Amortizacion> listaAmortizacions) {
		this.listaAmortizacions = listaAmortizacions;
	}

	public List<Amortizacion> getListaAmortizacionsPagadas() {
		return listaAmortizacionsPagadas;
	}

	public void setListaAmortizacionsPagadas(List<Amortizacion> listaAmortizacionsPagadas) {
		this.listaAmortizacionsPagadas = listaAmortizacionsPagadas;
	}

	public double getParteMonto() {
		return parteMonto;
	}

	public void setParteMonto(double parteMonto) {
		this.parteMonto = parteMonto;
	}
	
	
	

}
