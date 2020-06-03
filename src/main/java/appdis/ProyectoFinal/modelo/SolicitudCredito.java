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
public class SolicitudCredito {

	@Id
	@Column (name = "id_sol")
	private int id_sol;
	
	@Column (name = "notificacion")
	private String notificacion;
	@Column (name = "estado")
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "id_banca")
	private BancaVirtual bancav;
	
	
	
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

	public BancaVirtual getBancav() {
		return bancav;
	}

	public void setBancav(BancaVirtual bancav) {
		this.bancav = bancav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}













