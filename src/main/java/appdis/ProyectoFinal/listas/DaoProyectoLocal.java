package appdis.ProyectoFinal.listas;

import java.util.List;

import javax.ejb.Local;

import appdis.ProyectoFinal.modelo.BancaVirtual;
import appdis.ProyectoFinal.modelo.Cliente;
import appdis.ProyectoFinal.modelo.Credito;
<<<<<<< HEAD






=======
>>>>>>> c2eb90a9f3378a303e99ee94088a515e342517b8

@Local
public interface DaoProyectoLocal {

	public void guardarBanca(BancaVirtual bv) throws Exception;
	public void actualizarBanca(BancaVirtual bv) throws Exception;
	
<<<<<<< HEAD
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
=======
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
	
	
>>>>>>> c2eb90a9f3378a303e99ee94088a515e342517b8
	
}
