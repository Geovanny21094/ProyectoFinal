package appdis.ProyectoFinal.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import appdis.ProyectoFinal.dao.TransaccionDao;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_cuenta;
	
	@Column (name = "numeroCuenta")
	private String numeroCuenta;
	@Column (name = "tipoOperacion")
	private String tipoOperacion;
	@Column (name = "saldo")
	private double saldo;
	
	private Date fecha;
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "numeroCuenta")
//	private List<Transferencia> transferencia;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
		
	public void agregarCliente(Cliente cl, Cuenta cu) {
		if (cu == null)
			cu = new Cuenta();
		cu.setCliente(cl);
	}

	/*
	public void agregarTransaccion(Transaccion tra) {
		if (transaccion == null)
			transaccion = new ArrayList<Transaccion>();	
		transaccion.add(tra);
	}
	*/
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

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

//	public List<Transferencia> getTransferencia() {
//		return transferencia;
//	}
//
//	public void setTransferencia(List<Transferencia> transferencia) {
//		this.transferencia = transferencia;
//	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

/*
	public List<Transaccion> getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion tra) {
		this.transaccion = (List<Transaccion>) tra;
	}
*/
	@Override
	public String toString() {
		return "Cuenta [id_cuenta=" + id_cuenta + ", numeroCuenta=" + numeroCuenta + ", tipoOperacion=" + tipoOperacion
				+ ", saldo=" + saldo + ", cliente=" + cliente + "]";
	}

	
	

	
	
	
	
	

	
	
	
	
}
