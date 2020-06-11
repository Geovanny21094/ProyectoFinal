package appdis.ProyectoFinal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
	private List<Transaccion> listaTrans;
	private Date fechaIni;
	private Date fechaFin;
	

	@PostConstruct
	public void init() {
//		cl=new Cliente();
		cuenta = new Cuenta();

	}

	public String cerrarSesion() {

		return "login?faces-redirect=true";
	}

	public String accesoCliente(int numeroCuenta) {
//		cl = ejb.getCorreo(user);
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
	
	public String trasacionesFecha2(String numeroCuenta) {

		try {
			
			System.out.println(numeroCuenta);

			listaTrans=new ArrayList<Transaccion>();
			listaTrans = ejb.buscarTransaccionDias2(numeroCuenta,fechaIni,fechaFin);

			for (Transaccion tra : listaTrans) {
				System.out.println(tra.getFecha());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "ClienteFiltro?faces-redirect=true";

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


	
}
