package appdis.ProyectoFinal.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_not;
	
	@Column (name = "mensaje_notificacion")
	private String mensaje_notificacion;
	@Column (name = "fecha")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

	
	
	
	
	
	
	

}
