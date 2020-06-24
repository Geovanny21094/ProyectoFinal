package appdis.ProyectoFinal.modelo;

import java.sql.Date;

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
public class Transaccion {

	@Id
	@Column (name = "id_transaccion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_transaccion;
	
	private String tipo;
	
	private Date Fecha;
	
	private double monto;
	

	
	
	public int getId_transaccion() {
		return id_transaccion;
	}


	public void setId_transaccion(int id_transaccion) {
		this.id_transaccion = id_transaccion;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Date getFecha() {
		return Fecha;
	}


	public void setFecha(Date fecha) {
		Fecha = fecha;
	}


	


	public double getMonto() {
		return monto;
	}


	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	
	
	
	
	
	
	
	
	
}
