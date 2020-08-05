package appdis.ProyectoFinal.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CuentasDestino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String tipoCuenta;
	private String institucionFinanciera;
	private String numeroCuentaBeneficiario;
	private String cedulaBeneficiario;
	private String nombreBeneficiario;
	private String correoBeneficiario;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cuenta")
	private Cuenta cuenta;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTipoCuenta() {
		return tipoCuenta;
	}


	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}


	public String getInstitucionFinanciera() {
		return institucionFinanciera;
	}


	public void setInstitucionFinanciera(String institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}


	public String getNumeroCuentaBeneficiario() {
		return numeroCuentaBeneficiario;
	}


	public void setNumeroCuentaBeneficiario(String numeroCuentaBeneficiario) {
		this.numeroCuentaBeneficiario = numeroCuentaBeneficiario;
	}


	public String getCedulaBeneficiario() {
		return cedulaBeneficiario;
	}


	public void setCedulaBeneficiario(String cedulaBeneficiario) {
		this.cedulaBeneficiario = cedulaBeneficiario;
	}


	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}


	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}


	public String getCorreoBeneficiario() {
		return correoBeneficiario;
	}


	public void setCorreoBeneficiario(String correoBeneficiario) {
		this.correoBeneficiario = correoBeneficiario;
	}


	public Cuenta getCuenta() {
		return cuenta;
	}


	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	

	
}
