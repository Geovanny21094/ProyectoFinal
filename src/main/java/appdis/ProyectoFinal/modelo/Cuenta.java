package appdis.ProyectoFinal.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */
@Entity
public class Cuenta {
	@Id
	@Column (name = "id_cuenta")
	private int id_cuenta;
	
	@Column (name = "numeroCuenta")
	private String numeroCuenta;
	@Column (name = "tipoCuenta")
	private String tipoCuenta;
	@Column (name = "tipoOperacion")
	private String tipoOperacion;
	@Column (name = "saldo")
	private double saldo;
	
	
	@OneToMany(mappedBy = "cuenta")
	private List<Transferencia> transferencia;
	
	
	
	@OneToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	
	
	public int getId_cuenta() {
		return id_cuenta;
	}
	
	public void setId_cuenta(int id_cuenta) {
		this.id_cuenta = id_cuenta;
	}
	
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public List<Transferencia> getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(List<Transferencia> transferencia) {
		this.transferencia = transferencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	

	
	
	
	
}
