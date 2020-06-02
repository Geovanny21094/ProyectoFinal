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
public class Credito {

	@Id
	@Column (name = "id_credito")
	private int id_credito;
	private double monto;
	private double saldo;
	private double cuotas;
	
	
	public int getId_credito() {
		return id_credito;
	}
	
	public void setId_credito(int id_credito) {
		this.id_credito = id_credito;
	}
	
	public double getMonto() {
		return monto;
	}
	
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public double getCuotas() {
		return cuotas;
	}
	
	public void setCuotas(double cuotas) {
		this.cuotas = cuotas;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
