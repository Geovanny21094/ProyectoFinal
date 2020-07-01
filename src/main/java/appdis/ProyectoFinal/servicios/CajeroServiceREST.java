package appdis.ProyectoFinal.servicios;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Transaccion;
import appdis.ProyectoFinal.modelo.Transferencia;

@Path("/transaccion")
public class CajeroServiceREST {

	@Inject
	DaoProyectoLocal ejb;

//	private String tipo;
//	private double monto;
//	private Cuenta cuenta;
//	private String numeroCuenta;
	private List<Transaccion> listatransacciones;

	@GET
	@Path("/transferencia/{numCuentaOrigen}/{numCuentaDestino}/{monto}")
	@Produces("application/json")
	public void tranferirDineroCuenta(@PathParam("numCuentaOrigen")String numCuentaOrigen, @PathParam("numCuentaDestino")String numCuentaDestino, @PathParam("monto")double monto) throws Exception {

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

	
	@GET
	@Path("/transferencia/{numeroCuenta}")
	@Produces("application/json")
	public Cuenta obtenerDatosCuenta(@PathParam("numeroCuenta")String numeroCuenta) {
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

	@GET
	@Path("/transferencia/{tipo}/{monto}/{numeroCuenta}")
	@Produces("application/json")
	public String Deposito(@PathParam("tipo")String tipo, @PathParam("monto")double monto, @PathParam("numeroCuenta")String numeroCuenta) {

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


	@GET
	@Path("/transferencia/{tipo}/{monto}/{numeroCuenta}")
	@Produces("application/json")
	public String Retiro(@PathParam("tipo")String tipo, @PathParam("monto")double monto, @PathParam("numeroCuenta")String numeroCuenta) throws Exception {
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
