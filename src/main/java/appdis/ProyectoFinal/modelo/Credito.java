package appdis.ProyectoFinal.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Credito {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_credito")
	private int id_credito;
	
	@Column (name = "cuotas")
	private int cuotas;
	
	private String historialCreditos;
	
	private String propositoCredito;
		
	@Column (name = "monto")
	private double monto;
	
	@Column (name = "saldo")
	private double saldo;
	
	private String tipoEmpleo;
	
	private double tasaPostpago;
	
	private String estadoCivil;
	
	private String garante;
	
	private double avaluo;
	
	private String activos;
	
	private int edad;
	
	private String vivienda;
	
	private int creditosExistentes;
	
	private String empleo;
	
	private String trabajodorExtranjero;
	
	private String tipoCliente;
	
	private String estadoCredito;
	
	private Date fechaCredito;
	
	
	
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cuenta")
	private  Cuenta cuenta;
	
	
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
	
	public int getCuotas() {
		return cuotas;
	}
	
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaCredito() {
		return fechaCredito;
	}

	public void setFechaCredito(Date fechaCredito) {
		this.fechaCredito = fechaCredito;
	}

	public String getHistorialCreditos() {
		return historialCreditos;
	}

	public void setHistorialCreditos(String historialCreditos) {
		this.historialCreditos = historialCreditos;
	}

	public String getPropositoCredito() {
		return propositoCredito;
	}

	public void setPropositoCredito(String propositoCredito) {
		this.propositoCredito = propositoCredito;
	}

	public String getTipoEmpleo() {
		return tipoEmpleo;
	}

	public void setTipoEmpleo(String tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}

	public double getTasaPostpago() {
		return tasaPostpago;
	}

	public void setTasaPostpago(double tasaPostpago) {
		this.tasaPostpago = tasaPostpago;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getGarante() {
		return garante;
	}

	public void setGarante(String garante) {
		this.garante = garante;
	}

	public double getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(double avaluo) {
		this.avaluo = avaluo;
	}

	public String getActivos() {
		return activos;
	}

	public void setActivos(String activos) {
		this.activos = activos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getVivienda() {
		return vivienda;
	}

	public void setVivienda(String vivienda) {
		this.vivienda = vivienda;
	}

	public int getCreditosExistentes() {
		return creditosExistentes;
	}

	public void setCreditosExistentes(int creditosExistentes) {
		this.creditosExistentes = creditosExistentes;
	}

	public String getEmpleo() {
		return empleo;
	}

	public void setEmpleo(String empleo) {
		this.empleo = empleo;
	}

	public String getTrabajodorExtranjero() {
		return trabajodorExtranjero;
	}

	public void setTrabajodorExtranjero(String trabajodorExtranjero) {
		this.trabajodorExtranjero = trabajodorExtranjero;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getEstadoCredito() {
		return estadoCredito;
	}

	public void setEstadoCredito(String estadoCredito) {
		this.estadoCredito = estadoCredito;
	}

	
	
	
	
}
