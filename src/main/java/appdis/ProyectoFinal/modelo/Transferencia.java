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
public class Transferencia {
	
	
	@Id
	@Column (name = "id_transferencia")
	private int id_transferencia;
	
	@Column (name = "cuenta_destino")
	private String cuenta_destino;
	
	
	@ManyToOne
	@JoinColumn(name = "id_cuenta")
	private Cuenta cuenta;
	
//	@ManyToOne
//	@JoinColumn(name = "id_inst")
//	private InstitucionFinanciera institucion;
	
	
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

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

//	public InstitucionFinanciera getInstitucion() {
//		return institucion;
//	}
//
//	public void setInstitucion(InstitucionFinanciera institucion) {
//		this.institucion = institucion;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
