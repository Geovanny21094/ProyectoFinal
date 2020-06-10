package appdis.ProyectoFinal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Transaccion;

@ManagedBean
@ViewScoped
public class ClienteBean {

	@Inject
	DaoProyectoLocal ejb;

	private Cuenta cuenta;
	private List<Transaccion> listatransacciones;

	@PostConstruct
	public void init() {

		cuenta = new Cuenta();
//		cuenta = ejb.buscarCuenta(numeroCuenta);
//		listatransacciones=ejb.buscarTransaccion(id);

//		listaCuenta=ejb.buscarCuenta(numeroCuenta);
//		this.listaCuenta=listaCuenta;

	}

	public String cerrarSesion() {
		
		return "login?faces-redirect=true";
	}



}
