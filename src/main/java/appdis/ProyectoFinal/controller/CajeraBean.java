package appdis.ProyectoFinal.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Persona;
import appdis.ProyectoFinal.modelo.Transaccion;

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
	private Transaccion transaccion;

	
	private List<Transaccion> listatransacciones; 
	
	@PostConstruct
	public void init() {
		cuenta = new Cuenta();
		cliente = new Cliente();
		transaccion = new Transaccion();
       
	}

	
	
	public void actTabla () {
		
		 try {
			ejb.buscarTransaccion(transaccion.getCuenta().getId_cuenta());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Transaccion newTransaccion = new Transaccion();
		Cuenta newCuenta = new Cuenta();

		if (tipo.equalsIgnoreCase("Deposito")) {
			double saldo = monto + cuenta.getSaldo();
			newTransaccion.setCuenta(cuenta);
			newTransaccion.setFecha(new Date());
			newTransaccion.setTipo(tipo);
            newTransaccion.setMonto(monto);
            cuenta.setSaldo(saldo);
			try {
//				cuenta.agregarCliente(cliente, newCuenta);
				cuenta.agregarTransaccion(newTransaccion);
				ejb.guardarTransaccion(newTransaccion);
				ejb.guardarCuenta(cuenta);
				listatransacciones = ejb.buscarTransaccion(newTransaccion.getCuenta().getId_cuenta());
				//actTabla();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (tipo.equalsIgnoreCase("Retiro")) {
			double saldoAnterior = cuenta.getSaldo();
			if (monto <= saldoAnterior) {
				double saldoTotal = saldoAnterior - monto;

				newTransaccion.setFecha(new Date());
				newTransaccion.setTipo(tipo);
				 newTransaccion.setMonto(monto);
				newTransaccion.setCuenta(cuenta);
				
				cuenta.setSaldo(saldoTotal);
				try {
//					cuenta.agregarCliente(cliente, newCuenta);
					cuenta.agregarTransaccion(newTransaccion);
					ejb.guardarTransaccion(newTransaccion);
					ejb.guardarCuenta(cuenta);
					actTabla();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Saldo Insuficinete");
			}

		}

		monto = 0;
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



	public Transaccion getTransaccion() {
		return transaccion;
	}



	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}



	public List<Transaccion> getListatransacciones() {
		return listatransacciones;
	}



	public void setListatransacciones(List<Transaccion> listatransacciones) {
		this.listatransacciones = listatransacciones;
	}
	
	
	
	
	
	
	
	
	
	
	

}
