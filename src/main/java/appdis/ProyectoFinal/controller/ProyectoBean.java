package appdis.ProyectoFinal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

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




/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
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
	
	private List<Persona> listaPersonas = new ArrayList<>() ;
	private List<Telefonos> listaTelefonos = new ArrayList<>() ;
	private List<BancaVirtual> listaBanca = new ArrayList<>() ;
	private List<Cliente> listaClientes = new ArrayList<>() ;
	private List<Credito> listaCredito = new ArrayList<>() ;
	private List<Cuenta> listaCuenta = new ArrayList<>() ;
	private List<InstitucionFinanciera> listaInstitucion = new ArrayList<>() ;
	private List<Notificaciones> listaNotificaciones = new ArrayList<>() ;
	private List<SolicitudCredito> listaSolicitud = new ArrayList<>() ;
	private List<Transferencia> listaTransferencia = new ArrayList<>() ;
	
	@PostConstruct
	public void init () {
		persona = new Persona ();
		telefonos = new Telefonos();
		banca = new BancaVirtual();
		cliente = new Cliente();
		credito = new Credito();
		cuenta = new Cuenta();
		institucion = new InstitucionFinanciera();
		notificaciones = new Notificaciones();
		solicitud = new SolicitudCredito();
		transferencia = new Transferencia();
		
		
		
		
		
		persona.agregarTelefono(new Telefonos());
		
		datosPersona();
		this.listaPersonas = listaPersonas;
		this.listaTelefonos = listaTelefonos;
		this.listaBanca = listaBanca;
		this.listaClientes = listaClientes;
		this.listaCredito = listaCredito;
		this.listaCuenta = listaCuenta;
		this.listaInstitucion = listaInstitucion;
		this.listaNotificaciones = listaNotificaciones;
		this.listaSolicitud = listaSolicitud;
		this.listaTransferencia = listaTransferencia;	
				
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


	public String guardarDatos () {
		System.out.println(this.toString());
		
		
		try {
		//	telefono.setPersona(persona);
			dalp.guardarPersona(persona);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	private void datosPersona() {
		try {
			//ltTelf = dal.buscarTelefono();
			//listas=dal.buscarCedula();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public String agregarTelefono() {
		persona.agregarTelefono(new Telefonos());
		return null;
		
	}
	
	
	
	
}
