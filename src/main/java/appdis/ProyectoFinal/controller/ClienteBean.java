package appdis.ProyectoFinal.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Cuenta;

@ManagedBean
@ViewScoped
public class ClienteBean {

	@Inject
	DaoProyectoLocal ejb;

	private List<Cuenta> listaCuenta;
	
	@PostConstruct
	public void init() {
		
//		listaCuenta=ejb.buscarCuenta(numeroCuenta);
//		this.listaCuenta=listaCuenta;

	}
	
	
	
	
}
