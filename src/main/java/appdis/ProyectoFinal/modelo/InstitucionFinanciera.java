package appdis.ProyectoFinal.modelo;

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
public class InstitucionFinanciera {

	@Id
	@Column (name = "id_inst")
	private int id_inst;
	private String nombre;
	private String direccion;
	private String correo;
	
	
	
	@OneToMany(mappedBy = "institucion")
	private List<Transferencia> transferencia;
	
	
	
	public int getId_inst() {
		return id_inst;
	}
	
	public void setId_inst(int id_inst) {
		this.id_inst = id_inst;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
	
	
	
	
	
	
}
