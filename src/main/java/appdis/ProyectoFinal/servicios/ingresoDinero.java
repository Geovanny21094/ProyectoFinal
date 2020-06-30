package appdis.ProyectoFinal.servicios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Transaccion;

@WebService
public class ingresoDinero {

	@Inject
	DaoProyectoLocal ejb;
	
	private List<Transaccion> listatransacciones; 
	
	
	
	@WebMethod
	public void obtenerDatosCuenta(String numeroCuenta) {
		try {
//			this.numeroCuenta = numeroCuenta;
			Cuenta cuenta = new Cuenta();
			cuenta = ejb.buscarCuenta(numeroCuenta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@WebMethod
	public void deposito(double monto, Cuenta cuenta) {
		Transaccion newTransaccion = new Transaccion();
		Cuenta newCuenta = new Cuenta();
		String tipo = "Depósito";
		cuenta = new Cuenta();
		

			double saldo = monto + cuenta.getSaldo();
			newTransaccion.setCuenta(cuenta);
			newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
			newTransaccion.setTipo(tipo);
            newTransaccion.setMonto(monto);
            cuenta.setSaldo(saldo);
			try {
//				cuenta.agregarCliente(cliente, newCuenta);
				//cuenta.agregarTransaccion(newTransaccion);
				ejb.guardarTransaccion(newTransaccion);
				ejb.guardarCuenta(cuenta);
				listatransacciones = ejb.buscarTransaccion(newTransaccion.getCuenta().getId_cuenta());
				//actTabla();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
	
	
	@WebMethod
	public List<Transaccion> listaTransacciones(int id){
		List<Transaccion> listadoTransacciones = new ArrayList<Transaccion>();
		
		try {
			listadoTransacciones = ejb.buscarTransaccion(id);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listadoTransacciones;		
	}
	
	
	
	
	
}
