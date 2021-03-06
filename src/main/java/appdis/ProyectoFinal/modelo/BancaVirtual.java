package appdis.ProyectoFinal.modelo;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */
@Entity
public class BancaVirtual {

	@Id
	@Column(name = "id_banca")
	private int id_banca;

	@Column(name = "fecha_ingreso")
	private Date fecha_ingreso;


	@Column(name = "solcitud")
	@OneToMany(mappedBy = "bancav")
	private List<SolicitudCredito> solicitud;

	@OneToOne
	private Cliente cliente;

	public int getId_banca() {
		return id_banca;
	}

	public void setId_banca(int id_banca) {
		this.id_banca = id_banca;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}



	public List<SolicitudCredito> getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(List<SolicitudCredito> solicitud) {
		this.solicitud = solicitud;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
