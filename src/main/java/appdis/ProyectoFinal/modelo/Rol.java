package appdis.ProyectoFinal.modelo;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */
@Entity
public class Rol {

	@Id
	@Column(name = "id_rol")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rol_id;

	private String cargo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cedula_persona")
	private Persona persona;

	public int getRol_id() {
		return rol_id;
	}

	public void setRol_id(int rol_id) {
		this.rol_id = rol_id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void agregarRol(Rol rol, Persona per) {

		if (rol == null)
			rol = new Rol();
		rol.setPersona(per);
		
	}

}
