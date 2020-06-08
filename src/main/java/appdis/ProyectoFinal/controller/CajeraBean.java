package appdis.ProyectoFinal.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Persona;

@ManagedBean
@ViewScoped
public class CajeraBean {

	@Inject
	DaoProyectoLocal ejb;

	private Cuenta cuenta;
	private Cliente cliente;
	private String numeroCuenta;
	private String tipo;
	private double monto;

	@PostConstruct
	public void init() {
		cuenta = new Cuenta();
		cliente = new Cliente();

	}

	public void obtenerDatosCuenta() {
		try {
			this.numeroCuenta = numeroCuenta;
			cuenta = ejb.buscarCuenta(numeroCuenta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void agregarCuenta(Cuenta c) {

		if (cuenta == null)
			cuenta = new Cuenta();
		cuenta.setNumeroCuenta(c.getNumeroCuenta());
		System.out.println();
	}

	public String guardarTrasaccion() {
		Cuenta newCuenta = new Cuenta();
		if (tipo.equalsIgnoreCase("Deposito")) {
			double saldo = monto + cuenta.getSaldo();
			cuenta.setSaldo(saldo);
			cuenta.setTipoOperacion(tipo);
			cuenta.setFecha(new Date());
			try {
//				cuenta.agregarCliente(cliente, newCuenta);
				ejb.guardarCuenta(cuenta);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (tipo.equalsIgnoreCase("Retiro")) {
			double saldoAnterior = cuenta.getSaldo();
			if (monto <= saldoAnterior) {
				double saldoTotal = saldoAnterior - monto;
				cuenta.setSaldo(saldoTotal);
				cuenta.setTipoOperacion(tipo);
				cuenta.setFecha(new Date());
				newCuenta = cuenta;
				
				try {
//					cuenta.agregarCliente(cliente, newCuenta);
					ejb.guardarCuenta(newCuenta);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Saldo Insuficinete");
			}

		}

		monto=0;
		return null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

}
