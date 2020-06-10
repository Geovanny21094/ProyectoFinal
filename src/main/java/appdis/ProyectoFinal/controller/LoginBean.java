package appdis.ProyectoFinal.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import appdis.ProyectoFinal.dao.EnviarCorreo;
import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Persona;
import appdis.ProyectoFinal.modelo.Rol;
import appdis.ProyectoFinal.modelo.Transaccion;

@ManagedBean
@SessionScoped
public class LoginBean {

	@Inject
	private DaoProyectoLocal ejb;

	private EnviarCorreo envCorreo;
	private Persona persona;
	private Cuenta cuenta;
	private Cliente cliente;
	private int numeroCuenta;
	private Transaccion newTransaccion;
	private List<Transaccion> listatransacciones;
	

	private Rol rol;

	@PostConstruct
	public void init() {

		persona = new Persona();
		rol = new Rol();
		cliente = new Cliente();
		cuenta=new Cuenta();
		envCorreo = new EnviarCorreo();
		newTransaccion=new Transaccion();
		


	}

	public String isValidCliente() {
		String pag = "";
		try {
//			String user=cliente.getUsuario();
//			String pass=cliente.getContrasenia();
			Cliente cl = new Cliente();
			if (ejb.isValidUserPassC(cliente.getUsuario(), cliente.getContrasenia()) == true) {

				cl = ejb.getCorreo(cliente.getUsuario());
				System.out.println("true");
				ejb.enviarCorreo("INGRESO A CUENTA", "Se ingreso a la BANCA VIRTUAL", cl.getPersona().getCorreo());
				
				
				cuenta=ejb.buscarCuenta(cl.getId_cliente());
				numeroCuenta=cuenta.getId_cuenta();
				System.out.println("cedula-> "+cuenta.getId_cuenta());
				pag = "ClienteHome?faces-redirect=true&numeroCuenta=" + numeroCuenta;
				
				try {
					listatransacciones = ejb.buscarTransaccion(numeroCuenta);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			} else if (ejb.isValidUserPassC(cliente.getUsuario(), cliente.getContrasenia()) == false) {
				System.out.println("false");
				ejb.enviarCorreo("INTENTO DE INGRESO A LA BANCA VIRTUAL",
						"Se intento ingresar a la BANCA VIRTUAL \n" + "Estado FALLIDO", cl.getPersona().getCorreo());
				pag = "login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cliente=new Cliente();
		return pag;
	}

	public String isValidRol() {
		String pag = "";
		try {
//			String user=cliente.getUsuario();
//			String pass=cliente.getContrasenia();
			if (ejb.isValidUserPassR(rol.getUsuario(), rol.getContrasenia()) == true) {
				// cliente=dalp.getCorreo(cliente.getUsuario());
				System.out.println("true");
				// dalp.enviarCorreo("INGRESO A CUENTA","Se ingreso a la BANCA VIRTUAL",
				// cliente.getPersona().getCorreo());
				pag = "Accesos";
			} else {
				System.out.println("false");
				// dalp.enviarCorreo("INTENTO DE INGRESO A LA BANCA VIRTUAL", "Se intento
				// ingresar a la BANCA VIRTUAL \n"
				// + "Estado FALLIDO", cliente.getPersona().getCorreo());
				pag = "login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pag;
	}
	
	public void cargarCuenta(){
		
		
		
	}
	public EnviarCorreo getEnvCorreo() {
		return envCorreo;
	}

	public void setEnvCorreo(EnviarCorreo envCorreo) {
		this.envCorreo = envCorreo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Transaccion getNewTransaccion() {
		return newTransaccion;
	}

	public void setNewTransaccion(Transaccion newTransaccion) {
		this.newTransaccion = newTransaccion;
	}

	public List<Transaccion> getListatransacciones() {
		return listatransacciones;
	}

	public void setListatransacciones(List<Transaccion> listatransacciones) {
		this.listatransacciones = listatransacciones;
	}

		

	
	

}
