package appdis.ProyectoFinal.servicios;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Transaccion;

@WebService
public class CajeroServiceSOAP {
	
	@Inject 
	DaoProyectoLocal ejb;
	
	private String tipo;
	private double monto;
	private Cuenta cuenta;
	private List<Transaccion> listatransacciones; 

	
	@WebMethod
	public String guardarTrasaccion() {
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
				//actTabla();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (tipo.equalsIgnoreCase("Retiro")) {
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
			}

		}

		monto = 0;
		return null;
	}
	

}
