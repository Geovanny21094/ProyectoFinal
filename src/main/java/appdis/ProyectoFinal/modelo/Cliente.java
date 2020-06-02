package appdis.ProyectoFinal.modelo;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private int id_cliente;
	
	
	private Date fecha_registro;
	
	
	private String usuario;
	
	
	private String contrasenia;
	
	
	@OneToOne
	@JoinColumn(name="id_persona")
	private Persona persona;
	

	
	
	@OneToMany(mappedBy = "cliente")
	private List<Credito> credito;
	
	
	
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





