package appdis.ProyectoFinal.servicios;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Transaccion;
import appdis.ProyectoFinal.modelo.Transferencia;

@WebService
public class CajeroServiceSOAP {

	@Inject
	DaoProyectoLocal ejb;

//	private String tipo;
//	private double monto;
//	private Cuenta cuenta;
//	private String numeroCuenta;
	private List<Transaccion> listatransacciones;
	
	

	public void tranferirDineroCuenta(Cuenta cuOrigen, Cuenta cuDestino, String tipo, double monto) {

			Transferencia tra=new Transferencia();

			Transaccion newTransaccion = new Transaccion();
			double saldoAnterior = cuOrigen.getSaldo();
			if (monto <= saldoAnterior) {
				double saldoTotal = saldoAnterior - monto;

				newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
				newTransaccion.setTipo(tipo);
				newTransaccion.setMonto(monto);
				newTransaccion.setCuenta(cuOrigen);

				cuOrigen.setSaldo(saldoTotal);
				try {
					cuOrigen.agregarTransaccion(newTransaccion);
					ejb.guardarTransaccion(newTransaccion);
					ejb.guardarCuenta(cuOrigen);
					
					
					double saldo = monto + cuDestino.getSaldo();
					newTransaccion.setCuenta(cuDestino);
					newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
					newTransaccion.setTipo(tipo);
					newTransaccion.setMonto(monto);
					cuDestino.setSaldo(saldo);
					
//						cuenta.agregarCliente(cliente, newCuenta);
						cuDestino.agregarTransaccion(newTransaccion);
						ejb.guardarTransaccion(newTransaccion);
						ejb.guardarCuenta(cuDestino);
						
						tra.setCuenta(cuOrigen);
						tra.setCuenta_destino(cuDestino.getNumeroCuenta());
						ejb.guardarTransferencia(tra);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Saldo Insuficinete");
			}
		}
	

	@WebMethod
	public Cuenta obtenerDatosCuenta(String numeroCuenta) {
		try {
//			this.numeroCuenta = numeroCuenta;
			Cuenta cuenta = new Cuenta();
			cuenta = ejb.buscarCuenta(numeroCuenta);
			System.out.println("entra: " + cuenta);
			return cuenta;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@WebMethod
	public String Deposito(String tipo, double monto, Cuenta cuenta) {
		Transaccion newTransaccion = new Transaccion();
		Cuenta newCuenta = new Cuenta();

		if (tipo.equalsIgnoreCase("Deposito")) {
			double saldo = monto + cuenta.getSaldo();
			newTransaccion.setCuenta(cuenta);
			newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
			newTransaccion.setTipo(tipo);
			newTransaccion.setMonto(monto);
			cuenta.setSaldo(saldo);
			try {
//				cuenta.agregarCliente(cliente, newCuenta);
				cuenta.agregarTransaccion(newTransaccion);
				ejb.guardarTransaccion(newTransaccion);
				ejb.guardarCuenta(cuenta);
				listatransacciones = ejb.buscarTransaccion(newTransaccion.getCuenta().getId_cuenta());
				// actTabla();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		monto = 0;
		return "false";
	}
	
	@WebMethod
	public String Retiro(String tipo, double monto, Cuenta cuenta) {
		Transaccion newTransaccion = new Transaccion();
		Cuenta newCuenta = new Cuenta();

		 if (tipo.equalsIgnoreCase("Retiro")) {
			double saldoAnterior = cuenta.getSaldo();
			if (monto <= saldoAnterior) {
				double saldoTotal = saldoAnterior - monto;

				newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
				newTransaccion.setTipo(tipo);
				newTransaccion.setMonto(monto);
				newTransaccion.setCuenta(cuenta);

				cuenta.setSaldo(saldoTotal);
				try {
//					cuenta.agregarCliente(cliente, newCuenta);
					cuenta.agregarTransaccion(newTransaccion);
					ejb.guardarTransaccion(newTransaccion);
					ejb.guardarCuenta(cuenta);
//					actTabla();
					listatransacciones = ejb.buscarTransaccion(newTransaccion.getCuenta().getId_cuenta());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Saldo Insuficinete");
				
				System.out.println("");
			}
		}

		monto = 0;
		return null;
			
	}
	
	
	

}
