package appdis.ProyectoFinal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.sound.midi.Soundbank;

import appdis.ProyectoFinal.dao.EnviarCorreo;
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
 *
 */

@ManagedBean
@ViewScoped
public class ProyectoBean {

	@Inject
	private DaoProyectoLocal dalp;

	private BancaVirtual banca;
	private Cliente cliente;
	private Credito credito;
	private Cuenta cuenta;
	private InstitucionFinanciera institucion;
	private Notificaciones notificaciones;
	private Persona persona;
	private Telefonos telefonos;
	private SolicitudCredito solicitud;
	private Transferencia transferencia;
	private Rol rol;
	private EnviarCorreo envCorreo;

	private List<Persona> listaPersonas = new ArrayList<>();
	private List<Telefonos> listaTelefonos = new ArrayList<>();
	private List<BancaVirtual> listaBanca = new ArrayList<>();
	private List<Cliente> listaClientes = new ArrayList<>();
	private List<Credito> listaCredito = new ArrayList<>();
	private List<Cuenta> listaCuenta = new ArrayList<>();
	private List<InstitucionFinanciera> listaInstitucion = new ArrayList<>();
	private List<Notificaciones> listaNotificaciones = new ArrayList<>();
	private List<SolicitudCredito> listaSolicitud = new ArrayList<>();
	private List<Transferencia> listaTransferencia = new ArrayList<>();
	private List<Rol> listaRol = new ArrayList<>();

	@PostConstruct
	public void init() {
		persona = new Persona();
		telefonos = new Telefonos();
		banca = new BancaVirtual();
		cliente = new Cliente();
		credito = new Credito();
		cuenta = new Cuenta();
		institucion = new InstitucionFinanciera();
		notificaciones = new Notificaciones();
		solicitud = new SolicitudCredito();
		transferencia = new Transferencia();
		rol = new Rol();
		envCorreo = new EnviarCorreo();

		guardarDatosPersona();
		// persona.agregarTelefono(new Telefonos());

		agregarPersona(persona);
		rol.agregarRol(rol, persona);
		cliente.agregarCliente(cliente, persona);

		guardarDatosPersona();
		agregarRol();
		agregarCliente();
		isValid();

		this.listaPersonas = listaPersonas;
		this.listaTransferencia = listaTransferencia;
		this.listaRol = listaRol;

	}

//	 public void agregarPersona(Persona per) {
//	    	if (persona == null)
//	    		persona = new Persona();
//	    	persona.setCedula(per.getCedula());
//
//	    }

	public String isValid() {
		String pag = "";
		try {
//			String user=cliente.getUsuario();
//			String pass=cliente.getContrasenia();
			if (dalp.isValidUserPassC(cliente.getUsuario(), cliente.getContrasenia()) == true) {
				//cliente=dalp.getCorreo(cliente.getUsuario());
				System.out.println("true");
				//dalp.enviarCorreo("INGRESO A CUENTA","Se ingreso a la BANCA VIRTUAL", cliente.getPersona().getCorreo());
				pag = "Accesos";
			} else {
				System.out.println("false");
				//dalp.enviarCorreo("INTENTO DE INGRESO A LA BANCA VIRTUAL", "Se intento ingresar a la BANCA VIRTUAL \n"
				//		+ "Estado FALLIDO", cliente.getPersona().getCorreo());
				pag = "login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pag;
	}

	public void agregarCliente() {
		try {
			String user = dalp.getUser(cliente);
			String pass = dalp.getPassword(cliente);
			dalp.guardarCliente(cliente);
			dalp.enviarCorreo("CREACION DE USUARIO ", " Bienvenido a la Cooperativa DMR,"
					+ " le propocionarmos un Usuario y Contraseña con el cual puede ingresar a la BANCA VIRTUAL \n"
					+ "Su usuario es: " + user + "\n Su contraseña es: " + pass, persona.getCorreo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void agregarRol() {
		try {
			dalp.guardarRol(rol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void agregarPersona(Persona per) {
		if (persona == null)
			persona = new Persona();
		persona.setCedula(per.getCedula());
	}

	public String guardarDatosPersona() {
		System.out.println(this.toString());

		try {
			dalp.guardarPersona(persona);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void enviarCorreo(String asunto, String mensaje, String destinatario) {
		try {
			envCorreo.enviarMail(asunto, mensaje, destinatario);
		} catch (Exception e) {
			System.out.println("Existe un error en el envio del correo");
		}
	}

	public String guardarDatosCliente() {
		System.out.println(this.toString());

		try {

			dalp.guardarCliente(cliente);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void datosPersona() {
		try {
			// ltTelf = dal.buscarTelefono();
			// listas=dal.buscarCedula();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public BancaVirtual getBanca() {
		return banca;
	}

	public void setBanca(BancaVirtual banca) {
		this.banca = banca;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public InstitucionFinanciera getInstitucion() {
		return institucion;
	}

	public void setInstitucion(InstitucionFinanciera institucion) {
		this.institucion = institucion;
	}

	public Notificaciones getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(Notificaciones notificaciones) {
		this.notificaciones = notificaciones;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Telefonos getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(Telefonos telefonos) {
		this.telefonos = telefonos;
	}

	public SolicitudCredito getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudCredito solicitud) {
		this.solicitud = solicitud;
	}

	public Transferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public List<Telefonos> getListaTelefonos() {
		return listaTelefonos;
	}

	public void setListaTelefonos(List<Telefonos> listaTelefonos) {
		this.listaTelefonos = listaTelefonos;
	}

	public List<BancaVirtual> getListaBanca() {
		return listaBanca;
	}

	public void setListaBanca(List<BancaVirtual> listaBanca) {
		this.listaBanca = listaBanca;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Credito> getListaCredito() {
		return listaCredito;
	}

	public void setListaCredito(List<Credito> listaCredito) {
		this.listaCredito = listaCredito;
	}

	public List<Cuenta> getListaCuenta() {
		return listaCuenta;
	}

	public void setListaCuenta(List<Cuenta> listaCuenta) {
		this.listaCuenta = listaCuenta;
	}

	public List<InstitucionFinanciera> getListaInstitucion() {
		return listaInstitucion;
	}

	public void setListaInstitucion(List<InstitucionFinanciera> listaInstitucion) {
		this.listaInstitucion = listaInstitucion;
	}

	public List<Notificaciones> getListaNotificaciones() {
		return listaNotificaciones;
	}

	public void setListaNotificaciones(List<Notificaciones> listaNotificaciones) {
		this.listaNotificaciones = listaNotificaciones;
	}

	public List<SolicitudCredito> getListaSolicitud() {
		return listaSolicitud;
	}

	public void setListaSolicitud(List<SolicitudCredito> listaSolicitud) {
		this.listaSolicitud = listaSolicitud;
	}

	public List<Transferencia> getListaTransferencia() {
		return listaTransferencia;
	}

	public void setListaTransferencia(List<Transferencia> listaTransferencia) {
		this.listaTransferencia = listaTransferencia;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Rol> getListaRol() {
		return listaRol;
	}

	public void setListaRol(List<Rol> listaRol) {
		this.listaRol = listaRol;
	}

	public EnviarCorreo getEnviarCorreo() {
		return envCorreo;
	}

	public void setEnviarCorreo(EnviarCorreo enviarCorreo) {
		this.envCorreo = enviarCorreo;
	}

}
