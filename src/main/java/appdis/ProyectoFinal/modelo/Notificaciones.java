package appdis.ProyectoFinal.modelo;

import java.sql.Date;

public class Notificaciones {
	
	
	private int id_not;
	private String mensaje_notificacion;
	private Date fecha;
	
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
