package appdis.ProyectoFinal.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import appdis.ProyectoFinal.dao.EnviarCorreo;
import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Persona;
import appdis.ProyectoFinal.modelo.Rol;

@ManagedBean
@ViewScoped
public class LoginBean {

	@Inject
	private DaoProyectoLocal ejb;

	private EnviarCorreo envCorreo;
	private Persona persona;
	private Cliente cliente;
	
	private Rol rol;

	@PostConstruct
	public void init() {

		persona = new Persona();
		rol = new Rol();
		cliente = new Cliente();
		envCorreo = new EnviarCorreo();

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
				pag = "ClienteHome";
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

}
