package appdis.ProyectoFinal.modelo;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */
@Entity
public class Cliente {
	
	@Id
	@Column (name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_cliente;
	
	@Column (name = "fecha_registro")
	private Date fecha_registro;
	
	@Column (name = "usuario")
	private String usuario;
	
	@Column (name = "contrasenia")
	private String contrasenia;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Persona persona;
	

	
	@Column (name = "credito")
	@OneToMany(mappedBy = "cliente")
	private List<Credito> credito;
	
	
	public void agregarCliente(Cliente cl, Persona per) {

		if (cl == null)
			cl = new Cliente();
		cl.setPersona(per);
		
	}
	
	
	public int getId_cliente() {
		return id_cliente;
	}
	
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public Date getFecha_registro() {
		return fecha_registro;
	}
	
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Credito> getCredito() {
		return credito;
	}

	public void setCredito(List<Credito> credito) {
		this.credito = credito;
	}

	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}





