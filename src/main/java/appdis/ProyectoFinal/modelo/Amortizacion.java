package appdis.ProyectoFinal.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Amortizacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_Amoritizacion;
	private int numeroCuota;
	private Date fechaPago;
	private double valor;
	private String estado;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_credito")
	 Credito credito;


	public int getId_Amoritizacion() {
		return id_Amoritizacion;
	}


	public void setId_Amoritizacion(int id_Amoritizacion) {
		this.id_Amoritizacion = id_Amoritizacion;
	}


	public int getNumeroCuota() {
		return numeroCuota;
	}


	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}


	public Date getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	public Credito getCredito() {
		return credito;
	}


	public void setCredito(Credito credito) {
		this.credito = credito;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	

}
