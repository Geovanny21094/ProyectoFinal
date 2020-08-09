package appdis.ProyectoFinal.servicios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Amortizacion;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Credito;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.CuentasDestino;
import appdis.ProyectoFinal.modelo.Notificaciones;
import appdis.ProyectoFinal.modelo.Transaccion;
import appdis.ProyectoFinal.modelo.Transferencia;

@Path("/cliente")

public class ClienteREST {

	@Inject
	DaoProyectoLocal ejb;

	@GET
	@Path("/cliente/{user}/{clave}")
	@Produces("application/json")
	public void isValidCliente(@PathParam("user") String user, @PathParam("clave") String clave) {
		List<Transaccion> listaTransacciones;
		Notificaciones notificaciones = new Notificaciones();
		Cuenta cuenta = new Cuenta();
		Cliente cl = new Cliente();
		int numeroCuenta1;
		try {

			cl = ejb.getCorreo(user);
			if (cl.getUsuario().equals(user) && (cl.getContrasenia().equals(clave))) {

				System.out.println("true");
				ejb.enviarCorreo("INGRESO A CUENTA", "Se ingreso a la BANCA VIRTUAL", cl.getPersona().getCorreo());

				notificaciones.setMensaje_notificacion("Ingreso Satisfactorio");
				notificaciones.setFecha(new java.util.Date());
				notificaciones.setCliente(cl);
				// cl.guardarNotificacion(notificaciones);

				ejb.guardarNotificaciones(notificaciones);

				cuenta = ejb.buscarCuenta(cl.getId_cliente());
				numeroCuenta1 = cuenta.getId_cuenta();
				System.out.println("cedula-> " + numeroCuenta1);
//				pag = "ClienteHome?faces-redirect=true&numeroCuenta=" + cuentanumeroCuenta1;

				listaTransacciones = ejb.buscarTransaccion(cuenta.getId_cuenta());

			} else {
				System.out.println("false");
				ejb.enviarCorreo("INTENTO DE INGRESO A LA BANCA VIRTUAL",
						"Se intento ingresar a la BANCA VIRTUAL \n" + "Estado FALLIDO", cl.getPersona().getCorreo());

				notificaciones.setMensaje_notificacion("Ingreso Erroneo");
				notificaciones.setFecha(new java.util.Date());
				notificaciones.setCliente(cl);
				// cl.guardarNotificacion(notificaciones);

				ejb.guardarNotificaciones(notificaciones);
				numeroCuenta1 = cuenta.getId_cuenta();

				listaTransacciones = ejb.buscarTransaccion(numeroCuenta1);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cl = new Cliente();
		notificaciones = new Notificaciones();
	}

	@GET
	@Path("/credito/{numeroCuenta}")
	@Produces("application/json")
	public Cuenta saldoCuenta(@PathParam("numeroCuenta") String numeroCuenta) throws Exception {
		Cuenta cuenta = new Cuenta();
		cuenta = ejb.buscarCuenta(numeroCuenta);
		return cuenta;
	}

	@GET
	@Path("/amortizacion/{idCuenta}")
	@Produces("application/json")
	public List<Amortizacion> amortizaciones(@PathParam("idCuenta") int idCuenta) throws Exception {
		List<Amortizacion> listaAmortizacions = new ArrayList<Amortizacion>();
		Credito credito = new Credito();

		credito = ejb.buscarCreditos(idCuenta);
		listaAmortizacions = ejb.buscarAmortizaciones(credito.getId_credito());

		return listaAmortizacions;
	}

	@POST
	@Path("/contrasenia/{passwordOld}/{passwordNew}/{numeroCuenta}")
	@Produces("application/json")
	public void cambiarContrasenia(@PathParam("passwordOld") String passwordOld,
			@PathParam("passwordNew") String passwordNew, @PathParam("numeroCuenta") String numeroCuenta)
			throws Exception {

		Cuenta cuenta = new Cuenta();
		Cliente cl = new Cliente();

		cuenta = ejb.buscarCuenta(numeroCuenta);

		cl = ejb.buscarCliente(cuenta.getCliente().getPersona().getCedula());

		String passOld = cuenta.getCliente().getContrasenia();

		System.out.println(passOld + " -old");
		if (passOld.equals(passwordOld)) {
			cl.setContrasenia(passwordNew);
			ejb.actualizarCliente(cl);
			ejb.enviarCorreo("CAMBIO DE  A CONTRASEÑA",
					"Se cambiado la contraseña de  la BANCA VIRTUAL \n" + "Su nueva Contraseña es: " + passwordNew + "",
					cl.getPersona().getCorreo());

		} else {
			System.out.println("Contrasena no es correcta");
		}
	}

	@POST
	@Path("/tranferenciaExterna/{numeroCuentaOrigen}")
	@Produces("application/json")
	@Consumes("application/json")
	public void tranferirDineroCuenta(@PathParam("numeroCuentaOrigen") String numeroCuentaOrigen,
			Transferencia transferencia) throws Exception {

		Cuenta cuenta = new Cuenta();
		Transaccion newTransaccion = new Transaccion();
		CuentasDestino cueDes = new CuentasDestino();

		double monto = transferencia.getMonto();

		cuenta = ejb.buscarCuenta(numeroCuentaOrigen);

		double saldoAnterior = cuenta.getSaldo();
		if (monto <= saldoAnterior) {
			newTransaccion.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
			newTransaccion.setTipo("Retiro");
			newTransaccion.setMonto(monto);
			try {
				System.out.println(cuenta.getNumeroCuenta());
				newTransaccion.setCuenta(cuenta);
				cuenta.setSaldo(saldoAnterior - monto);
				ejb.actualizarCuenta(cuenta);
				ejb.guardarTransaccion(newTransaccion);

				transferencia.setCuenta(cuenta);
				transferencia.setCuenta_destino(cueDes.getNumeroCuentaBeneficiario());
				transferencia.setFechaTranferencia(new java.util.Date());
				transferencia.setMonto(monto);

				ejb.guardarTransferencia(transferencia);

			} catch (

			Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Saldo Insuficinete");
		}
	}

}
