package appdis.ProyectoFinal.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */
@Entity
public class Telefonos {

	@Id
	@Column (name = "id_telf")
	private int id_telf;
	
	@Column (name = "numero")
	private String numero;
	@Column (name = "tipo")
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name = "id_cedula")
	private Persona persona;
	
	
	
	
	public int getId_telf() {
		return id_telf;
	}
	public void setId_telf(int id_telf) {
		this.id_telf = id_telf;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	
	
	
	
	
	
}
