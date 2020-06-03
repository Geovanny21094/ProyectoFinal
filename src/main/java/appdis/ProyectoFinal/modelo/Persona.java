package appdis.ProyectoFinal.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;





/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */
@Entity
public class Persona {

	@Id
	@Column (name = "cedula_persona")
	private String cedula;
	
	@Column (name = "nombres")
	private String nombres;
	@Column (name = "apellidos")
	private String apellidos;
	@Column (name = "genero")
	private String genero;
	@Column (name = "correo")
	private String correo;
	@Column (name = "fecha_nacimiento")
	private Date fecha_nacimiento;
	@Column (name = "roll")
	private String roll;
	
	
	@OneToMany(mappedBy = "persona")
	private List<Telefonos> telefonos;
	
	
	
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public List<Telefonos> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefonos> telefonos) {
		this.telefonos = telefonos;
	}
	
	
	
	
	
	 public void agregarTelefono(Telefonos telf) {
	    	if (telefonos == null)
	    		telefonos = new ArrayList<Telefonos>();
	    	telefonos.add(telf);
	    }
	
	
	
	
	
}
