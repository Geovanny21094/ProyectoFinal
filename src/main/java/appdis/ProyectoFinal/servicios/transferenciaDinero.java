package appdis.ProyectoFinal.servicios;

import java.sql.Date;
import java.util.Calendar;

import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Transaccion;

public class transferenciaDinero {

	private String tipo;
	private double monto;

	public void tranferirDineroCuenta(String cuOrigen, String cuDestino, Double monto) {

		Cuenta cuentaOrigen = new Cuenta();

		Cuenta cuentaDestino = new Cuenta();

		Transaccion newTransaccion = new Transaccion();

		if (tipo.equalsIgnoreCase("Trasnferencia")) {
			double saldo = monto + cuentaOrigen.getSaldo();
			newTransaccion.setCuenta(cuentaDestino);
			newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
			newTransaccion.setTipo(tipo);
			newTransaccion.setMonto(monto);
			cuentaDestino.setSaldo(saldo);

		}
	}

}
