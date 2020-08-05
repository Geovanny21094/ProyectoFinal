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


/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */



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
	@Path("/transferencia/{montoTra}/{numCuentaOrigen}/{numCuentaDestino}")
	@Produces("application/json")
	public void tranferirDineroCuenta( @PathParam("montoTra")double montoTra, @PathParam("numCuentaOrigen")String numCuentaOrigen, @PathParam("numCuentaDestino")String numCuentaDestino) throws Exception {

		Transferencia tranferencia = new Transferencia();
		Transaccion newTransaccion = new Transaccion();
		Cuenta cuentaOrigen = ejb.buscarCuenta(numCuentaOrigen);
		Cuenta cuentaDestino = ejb.buscarCuenta(numCuentaDestino);

		double saldoAnterior = cuentaOrigen.getSaldo();
		if (montoTra <= saldoAnterior) {
			newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
			newTransaccion.setTipo("Retiro");
			newTransaccion.setMonto(montoTra);
			try {
				System.out.println(cuentaOrigen.getNumeroCuenta());
				newTransaccion.setCuenta(cuentaOrigen);
				cuentaOrigen.setSaldo(saldoAnterior - montoTra);
				ejb.actualizarCuenta(cuentaOrigen);
				ejb.guardarTransaccion(newTransaccion);

				
				double saldo = montoTra + cuentaDestino.getSaldo();

				newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
				newTransaccion.setTipo("Deposito");
				newTransaccion.setMonto(montoTra);

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
	@Path("/transferencia/{tipo}/{numeroCuenta}/{monto}")
	@Produces("application/json")
	public String Deposito(@PathParam("tipo")String tipo,  @PathParam("numeroCuenta")String numeroCuenta,@PathParam("monto")double monto) {

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


//	@GET
//	@Path("/transferencia/{tipo}/{monto}/{numeroCuenta}/{num}")
//	@Produces("application/json")
//	public String Retiro(@PathParam("tipo")String tipo, @PathParam("monto")double monto, @PathParam("numeroCuenta")String numeroCuenta,@PathParam("num") String num) throws Exception {
	@GET
	@Path("/transferencia/{tipo}/{numeroCuenta}/{monto}/{num}")
	@Produces("application/json")
	public String Retiro(@PathParam("tipo")String tipo, @PathParam("numeroCuenta")String numeroCuenta, @PathParam("monto")double monto,@PathParam("num") String num) throws Exception {
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