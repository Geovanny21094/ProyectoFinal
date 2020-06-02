package appdis.ProyectoFinal.modelo;

import java.sql.Date;

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
public class Notificaciones {
	
	@Id
	@Column (name = "id_not")
	private int id_not;
	
	private String mensaje_notificacion;
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "id_banca")
	private BancaVirtual banca;
	
	
	public int getId_not() {
		return id_not;
	}
	
	public void setId_not(int id_not) {
		this.id_not = id_not;
	}
	
	public String getMensaje_notificacion() {
		return mensaje_notificacion;
	}
	
	public void setMensaje_notificacion(String mensaje_notificacion) {
		this.mensaje_notificacion = mensaje_notificacion;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
	
	

}
