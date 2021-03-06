package appdis.ProyectoFinal.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import appdis.ProyectoFinal.dao.EnviarCorreo;
import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Persona;
import appdis.ProyectoFinal.modelo.Rol;

@ManagedBean
@ViewScoped
public class AdministradorBean {

	@Inject
	private DaoProyectoLocal ejb;

	private EnviarCorreo envCorreo;
	private Persona persona;
	private Cliente cliente;
	private Cuenta cuenta;
	private Rol rol;
	private String numero;
	private String cedula;

	private List<Rol> listaRol;
	private List<Cliente> listaCliente;
	private List<Cuenta> listaCuenta;

	@PostConstruct
	public void init() {
		rol = new Rol();
		persona = new Persona();
		cliente = new Cliente();
		cuenta = new Cuenta();
		envCorreo = new EnviarCorreo();

		try {
			numero = ejb.numeroCuenta();
			listaRol = ejb.listarRol();
			listaCliente = ejb.listarClinetes();
			listaCliente = ejb.listarClinetes();
			listaCuenta = ejb.listarCuentas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.listaCuenta = listaCuenta;
		this.listaRol = listaRol;
		this.listaCliente = listaCliente;
	}

	public void agregarPersona(Persona per) {
		if (persona == null)
			persona = new Persona();
		persona.setCedula(per.getCedula());
	}

	public String obtenerCliente(String cedula) {
		System.out.println(cedula);
		try {

//			persona=ejb.buscarPersonaa(cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AdministracionClientesModificar?faces-redirect=true&cedula=" + cedula;
	}

	public String obtenerPersonaRol(String cedula) {
		System.out.println(cedula);
		try {

//			persona=ejb.buscarPersonaa(cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AdministracionRolesModificar?faces-redirect=true&cedula=" + cedula;
	}

	public String modificarCliente() {
		// persona=ejb.buscarCliente(cedula)
		return null;
	}

	public void eliminarRol(String cedula) {
		System.out.println(cedula);
		try {
//			persona = ejb.buscarPersonaa(cedula);
			ejb.eliminarRol(cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Metodo que Guarda el Rol de una Persona Creada
	 */
	public void agregarRol() {
		try {

			agregarPersona(persona);
			rol.agregarRol(rol, persona);
			String user = ejb.getUserRol(rol);
			String pass = ejb.getPasswordRol(rol);

			ejb.guardarRol(rol);

			ejb.enviarCorreo("CREACION DE USUARIO ", " Bienvenido a la Cooperativa DMR,"
					+ " le propocionarmos un Usuario y Contraseña con el cual puede ingresar a la BANCA VIRTUAL \n"
					+ "Su usuario es: " + user + "\n Su contraseña es: " + pass, persona.getCorreo());
			rol = new Rol();
			persona = new Persona();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Metodo que Guarda un Cliente a partir de una Persona
	 */
	public String agregarCliente() {
		try {

			cliente.agregarCliente(cliente, persona);
			String user = ejb.getUser(cliente);
			String pass = ejb.getPassword(cliente);

			ejb.guardarCliente(cliente);

			cuenta.setNumeroCuenta(numero);
			double saldo = 0;
			cuenta.setSaldo(saldo);
			cuenta.setTipoOperacion("Apertura de Cuenta");

			cuenta.agregarCliente(cliente, cuenta);

			ejb.guardarCuenta(cuenta);

			ejb.enviarCorreo("CREACION DE USUARIO ", " Bienvenido a la Cooperativa DMR,"
					+ " le propocionarmos un Usuario y Contraseña con el cual puede ingresar a la BANCA VIRTUAL \n"
					+ "Su usuario es: " + user + "\n Su contraseña es: " + pass, persona.getCorreo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AdministracionClientedLista";
	}

	public String ModificarCliente() {
		try {

			ejb.guardarPersona(persona);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AdministracionClientedLista";
	}

	public String ModificarRol() {
		try {

			ejb.guardarPersona(persona);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "AdministracionRolesLista";
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		System.out.println("Parametro" + cedula);
		this.cedula = cedula;
		if (cedula != null) {
			try {
				persona = ejb.buscarPersonaa(cedula);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String socios() {
		return "login?faces-redirect=true";
	}
	
	public String roles() {
		return "loginRol?faces-redirect=true";
	}

	public List<Cuenta> getListaCuenta() {
		return listaCuenta;
	}

	public void setListaCuenta(List<Cuenta> listaCuenta) {
		this.listaCuenta = listaCuenta;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public List<Rol> getListaRol() {
		return listaRol;
	}

	public void setListaRol(List<Rol> listaRol) {
		this.listaRol = listaRol;
	}

	public EnviarCorreo getEnvCorreo() {
		return envCorreo;
	}

	public void setEnvCorreo(EnviarCorreo envCorreo) {
		this.envCorreo = envCorreo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
