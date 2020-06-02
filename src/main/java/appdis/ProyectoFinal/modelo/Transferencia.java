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
public class Transferencia {
	
	
	@Id
	@Column (name = "id_transferencia")
	private int id_transferencia;
	
	private String cuenta_destino;
	
	
	public int getId_transferencia() {
		return id_transferencia;
	}
	
	public void setId_transferencia(int id_transferencia) {
		this.id_transferencia = id_transferencia;
	}

	public String getCuenta_destino() {
		return cuenta_destino;
	}

	public void setCuenta_destino(String cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
