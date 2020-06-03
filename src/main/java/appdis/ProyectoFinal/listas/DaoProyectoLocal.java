package appdis.ProyectoFinal.listas;

import java.util.List;

import javax.ejb.Local;

import appdis.ProyectoFinal.modelo.BancaVirtual;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Credito;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.InstitucionFinanciera;
import appdis.ProyectoFinal.modelo.Notificaciones;
import appdis.ProyectoFinal.modelo.Persona;
import appdis.ProyectoFinal.modelo.Rol;
import appdis.ProyectoFinal.modelo.SolicitudCredito;
import appdis.ProyectoFinal.modelo.Telefonos;
import appdis.ProyectoFinal.modelo.Transferencia;



/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */

@Local
public interface DaoProyectoLocal {


	/*Banca Virtual*/
	public void guardarBanca(BancaVirtual bv) throws Exception;
	public void actualizarBanca(BancaVirtual bv) throws Exception; 
	public  List<BancaVirtual> buscarBanca(int id) throws Exception; 
	public void eliminarBanca(int id) throws Exception;
	
	
	
	
	/*Cliente*/
	public void guardarCliente(Cliente cl) throws Exception;
	public void actualizarCliente(Cliente cl) throws Exception;
	public  List<Cliente> buscarCliente(int id) throws Exception; 
	public void eliminarCliente(int id) throws Exception;
	
	
	/*Credito*/
	public void guardarCredito(Credito cr) throws Exception;
	public void actualizarCredito(Credito cr) throws Exception;
	public  List<Credito> buscarCredito(int id) throws Exception;
	public void eliminarCredito(int id) throws Exception;
	
	
	
	
	/*Cuenta*/
	public void guardarCuenta(Cuenta cu) throws Exception;
	public void actualizarCuenta(Cuenta cu) throws Exception;
	public  List<Cuenta> buscarCuenta(int id) throws Exception;
	public void eliminarCuenta(int id) throws Exception;
	
	
	
	/*Intitucion Financiera*/
	public void guardarInstitucion(InstitucionFinanciera inf) throws Exception;
	public void actualizarInstitucion(InstitucionFinanciera inf) throws Exception;
	public  List<InstitucionFinanciera> buscarInstitucion(int id) throws Exception;
	public void eliminarInstitucion(int id) throws Exception;
	
	
	
	/*Notificaciones*/
	public void guardarNotificaciones(Notificaciones not) throws Exception;
	public void actualizarNotificaciones(Notificaciones not) throws Exception;
	public  List<Notificaciones> buscarNotificaciones(int id) throws Exception;
	public void eliminarNotificaciones(int id) throws Exception;
	public void enviarCorreo(String asunto, String mensaje, String correoDestino) throws Exception;
	
	
	
	
	/*Persona*/
	public void guardarPersona(Persona per) throws Exception;
	public void actualizarPersona(Persona per) throws Exception;
	public  List<Persona> buscarPersona(String cedula) throws Exception;
	public void eliminarPersona(String cedula) throws Exception;
	
	public boolean validarCedula(String cedula) throws Exception;
	
	
	
	
	
	/*SolicitudCredito*/
	public void guardarSolicitud(SolicitudCredito sol) throws Exception ;
	public void actualizarSolicitud(SolicitudCredito sol) throws Exception;
	public  List<SolicitudCredito> buscarSolicitud(int id) throws Exception ;
	public void eliminarSolicitud(int id) throws Exception;
	
	
	
	
	
	/*Telefonos*/
	public void guardarTelefonos(Telefonos telf) throws Exception;
	public void actualizarTelefono(Telefonos telf) throws Exception;
	public  List<Telefonos> buscarTelefono(int id) throws Exception;
	public void eliminarTelefono(int id) throws Exception;
	
	
	
	
	
	/*Transferencia*/
	public void guardarTransferencia(Transferencia tr) throws Exception;
	public void actualizarTransferencia(Transferencia tr) throws Exception;
	public  List<Transferencia> buscarTrasnferencia(int id) throws Exception;
	public void eliminarTransferencia(int id) throws Exception;
	
	
	
	
	
	/*Rol*/
	public void guardarRol(Rol r) throws Exception;
	public void actualizarRol(Rol r) throws Exception;
	public  List<Rol> buscarRol(int id) throws Exception;
	public void eliminarRol(int id) throws Exception;
	
	
	
	
	
}
