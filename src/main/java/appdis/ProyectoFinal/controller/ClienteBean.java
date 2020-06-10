package appdis.ProyectoFinal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Notificaciones;
import appdis.ProyectoFinal.modelo.Transaccion;

@ManagedBean
@SessionScoped
public class ClienteBean {

	@Inject
	DaoProyectoLocal ejb;

	private Cuenta cuenta;
	private Cliente cl;
	private List<Transaccion> listaTransacciones;
	private List<Notificaciones> listaNotificaciones;

	@PostConstruct
	public void init() {
//		cl=new Cliente();
		cuenta = new Cuenta();

	}

	public String cerrarSesion() {

		return "login?faces-redirect=true";
	}

	public String accesoCliente(String numeroCuenta) {
//		cl = ejb.getCorreo(user);
		try {
			cuenta = ejb.buscarCuenta(numeroCuenta);

			listaNotificaciones = ejb.buscarNotificaciones(cuenta.getCliente().getId_cliente());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cuenta=new Cuenta();
	
		return "ClienteAccesos";
		
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

}
