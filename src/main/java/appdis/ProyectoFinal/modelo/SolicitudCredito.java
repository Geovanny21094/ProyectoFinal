package appdis.ProyectoFinal.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */
@Entity
public class SolicitudCredito {

	@Id
	@Column (name = "id_sol")
	private int id_sol;
	
	private String notificacion;
	private String estado;
	
	public int getId_sol() {
		return id_sol;
	}
	
	public void setId_sol(int id_sol) {
		this.id_sol = id_sol;
	}
	
	public String getNotificacion() {
		return notificacion;
	}
	
	public void setNotificacion(String notificacion) {
		this.notificacion = notificacion;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}













