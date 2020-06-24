package appdis.ProyectoFinal.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Credito {

	@Id
	@Column (name = "id_credito")
	private int id_credito;
	@Column (name = "monto")
	private double monto;
	@Column (name = "saldo")
	private double saldo;
	@Column (name = "cuotas")
	private double cuotas;
	
	
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
