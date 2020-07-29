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

	@WebMethod
	public void tranferirDineroCuenta(String numCuentaOrigen, String numCuentaDestino, double monto) throws Exception {

		Transferencia tranferencia = new Transferencia();
		Transaccion newTransaccion = new Transaccion();
		Cuenta cuentaOrigen = ejb.buscarCuenta(numCuentaOrigen);
		Cuenta cuentaDestino = ejb.buscarCuenta(numCuentaDestino);

		double saldoAnterior = cuentaOrigen.getSaldo();
		if (monto <= saldoAnterior) {
			newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
			newTransaccion.setTipo("Retiro");
			newTransaccion.setMonto(monto);
			try {
				System.out.println(cuentaOrigen.getNumeroCuenta());
				newTransaccion.setCuenta(cuentaOrigen);
				cuentaOrigen.setSaldo(saldoAnterior - monto);
				ejb.actualizarCuenta(cuentaOrigen);
				ejb.guardarTransaccion(newTransaccion);

				
				double saldo = monto + cuentaDestino.getSaldo();

				newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
				newTransaccion.setTipo("Deposito");
				newTransaccion.setMonto(monto);

				cuentaDestino = ejb.buscarCuenta(numCuentaDestino);
				newTransaccion.setCuenta(cuentaDestino);
				cuentaDestino.setSaldo(saldo);
				ejb.actualizarCuenta(cuentaDestino);
				ejb.guardarTransaccion(newTransaccion);
				
			} catch (

			Exception e) {
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
	public String Deposito(String tipo, double monto, String numeroCuenta) {

		Transaccion newTransaccion = new Transaccion();
		Cuenta cuenta = new Cuenta();

		if (tipo.equalsIgnoreCase("Deposito")) {
			double saldo = monto + cuenta.getSaldo();

			newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
			newTransaccion.setTipo(tipo);
			newTransaccion.setMonto(monto);
			try {
				cuenta = ejb.buscarCuenta(numeroCuenta);
				System.out.println(cuenta.getNumeroCuenta());
				newTransaccion.setCuenta(cuenta);
				System.out.println("Si entra para enviar 1 ");
//				cuenta.agregarCliente(cliente, newCuenta);
				// cuenta.agregarTransaccion(newTransaccion);
				cuenta.setSaldo(cuenta.getSaldo() + saldo);
				ejb.actualizarCuenta(cuenta);
				System.out.println("Si entra para enviar 2");

				ejb.guardarTransaccion(newTransaccion);
				System.out.println("Paso el guardar Transaccion");

				System.out.println("Paso el guardar Cuenta");
				// listatransacciones =
				// ejb.buscarTransaccion(newTransaccion.getCuenta().getId_cuenta());
				// return "true";
				// actTabla();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// monto = 0;
		return null;
	}

	@WebMethod
	public String Retiro(String tipo, double monto, String numeroCuenta) throws Exception {
		Transaccion newTransaccion = new Transaccion();
		Cuenta cuenta = ejb.buscarCuenta(numeroCuenta);

		if (tipo.equalsIgnoreCase("Retiro")) {
			double saldoAnterior = cuenta.getSaldo();
			if (monto <= saldoAnterior) {

				newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
				newTransaccion.setTipo(tipo);
				newTransaccion.setMonto(monto);
				try {
					System.out.println(cuenta.getNumeroCuenta());
					newTransaccion.setCuenta(cuenta);
					System.out.println("Si entra para enviar 1 ");
//					cuenta.agregarCliente(cliente, newCuenta);
					// cuenta.agregarTransaccion(newTransaccion);
					cuenta.setSaldo(saldoAnterior - monto);
					ejb.actualizarCuenta(cuenta);
					System.out.println("Si entra para enviar 2");

					ejb.guardarTransaccion(newTransaccion);
					System.out.println("Paso el guardar Transaccion");

					System.out.println("Paso el guardar Cuenta");
					// listatransacciones =
					// ejb.buscarTransaccion(newTransaccion.getCuenta().getId_cuenta());
					// return "true";
					// actTabla();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.println("Saldo Insuficinete");

				System.out.println("");
			}
		}
		return null;

	}

}
