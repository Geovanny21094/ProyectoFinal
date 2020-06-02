package appdis.ProyectoFinal.modelo;

import java.sql.Date;

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
public class BancaVirtual {
	
	@Id
	@Column (name = "id_banca")
	private int id_banca;
	
	
	private Date fecha_ingreso;
	
	public int getId_banca() {
		return id_banca;
	}
	
	public void setId_banca(int id_banca) {
		this.id_banca = id_banca;
	}
	
	
	
	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}
	
	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
