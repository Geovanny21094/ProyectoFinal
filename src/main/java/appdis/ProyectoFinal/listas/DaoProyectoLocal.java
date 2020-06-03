package appdis.ProyectoFinal.listas;

import java.util.List;

import javax.ejb.Local;

import appdis.ProyectoFinal.modelo.BancaVirtual;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Credito;







@Local
public interface DaoProyectoLocal {

	
	public void guardarBanca(BancaVirtual bv) throws Exception;
	public void actualizarBanca(BancaVirtual bv) throws Exception;
	public  List<BancaVirtual> buscarBanca(int id) throws Exception;
	public void eliminarBanca(int id) throws Exception;
	public void guardarCliente(Cliente cl) throws Exception;
	public void actualizarCliente(Cliente cl) throws Exception; 
	public  List<Cliente> buscarCliente(int id) throws Exception;
	public void eliminarCliente(int id) throws Exception;
	public void guardarCredito(Credito cr) throws Exception; 
	public void actualizarCredito(Credito cr) throws Exception;
	public  List<Credito> buscarCredito(int id) throws Exception;
	public void eliminarCredito(int id) throws Exception;
	
}
