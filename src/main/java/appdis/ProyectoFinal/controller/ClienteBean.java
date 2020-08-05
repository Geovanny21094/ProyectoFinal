package appdis.ProyectoFinal.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
import appdis.ProyectoFinal.listas.DaoProyectoLocal;
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
	private List<Transaccion> listaTransacciones;
	private List<SelectItem> nombreCuentas;
	private List<Transaccion> listaTrans;

	private String numeroCuenta;
	private String cadenaCuenta;

	private int numeroCuenta1;
	private int edad;

	private Date fechaIni;
	private Date fechaFin;

	private String user;
	private String clave;

	@PostConstruct
	public void init() throws Exception {
		cuenta = new Cuenta();
		credito = new Credito();
		cueDes = new CuentasDestino();
		tranferencia = new Transferencia();
		notificaciones = new Notificaciones();

		nombresCuentas();

		this.nombreCuentas = nombreCuentas;
		this.edad = edad;
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
	public String credito() {
//		nombresCuentas();
		numeroCuenta1 = cuenta.getId_cuenta();

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
		numeroCuenta1 = cuenta.getId_cuenta();

		cuenta = ejb.buscarCuenta(numeroCuenta1);

		credito.setCuenta(cuenta);

		credito.setSaldo(credito.getMonto());

		double saldoAnterior = cuenta.getSaldo();

		credito.setFechaCredito(new java.util.Date());

		ejb.guardarCredito(credito);

		
		////probar
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("NewData.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		String ColumnNamesList = "Id,Name";
		
		builder.append(ColumnNamesList + "\n");
		builder.append("1" + ",");
		builder.append("Chola");
		builder.append('\n');
		pw.write(builder.toString());
		pw.close();
		System.out.println("done!");
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

}
