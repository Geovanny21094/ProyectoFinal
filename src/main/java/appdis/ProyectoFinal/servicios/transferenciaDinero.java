package appdis.ProyectoFinal.servicios;

import javax.jws.WebMethod;
import javax.jws.WebService;

import appdis.ProyectoFinal.modelo.Cuenta;

@WebService
public class transferenciaDinero {

	
	@WebMethod
	public void transferirDinero(Cuenta cuentaDestino, Cuenta cuentaOrigen, Double monto) {
		
	}
}
