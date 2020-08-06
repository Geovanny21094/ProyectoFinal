package appdis.ProyectoFinal.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;

import appdis.ProyectoFinal.listas.DaoProyectoLocal;
import appdis.ProyectoFinal.modelo.Amortizacion;
import appdis.ProyectoFinal.modelo.Credito;
import appdis.ProyectoFinal.modelo.Cuenta;
import appdis.ProyectoFinal.modelo.Transaccion;

@ManagedBean
@ViewScoped
public class JefeCredito {

	@Inject
	DaoProyectoLocal ejb;

	private Cuenta cuenta;
	private Credito credito;

	private List<Credito> listCreditos;

	private Part file;
	private String folder = "/Users/italomendieta/Desktop/ArchivosDMR/";

	@PostConstruct
	public void init() throws Exception {
		cuenta = new Cuenta();
		credito = new Credito();

		listCreditos = ejb.buscarCreditos();

	}

	public void cargarDatosCuenta() throws Exception {
		cuenta = ejb.buscarCuenta(cuenta.getNumeroCuenta());
	}

	/*
	 * Metodo que genera Credtio
	 */
	public void generarCredito() throws Exception {

		Transaccion newTransaccion = new Transaccion();
		cuenta = ejb.buscarCuenta(cuenta.getNumeroCuenta());

		credito.setCuenta(cuenta);
		credito.setSaldo(credito.getMonto());
		credito.setFechaCredito(new java.util.Date());
		double saldoAnterior = cuenta.getSaldo();

		ejb.guardarCredito(credito);

		int meses;
		int numCuotas = credito.getCuotas();
		double valorCuotas = credito.getMonto() / numCuotas;
		double saldoNuevo = saldoAnterior + credito.getMonto();

		java.util.Date fechaPago = credito.getFechaCredito();

		String filename = "Amortizacion.xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("FirstSheet");

		HSSFRow rowhead = sheet.createRow((short) 0);
		rowhead.createCell(0).setCellValue("Numero Cuota");
		rowhead.createCell(1).setCellValue("Fecha de Pago");
		rowhead.createCell(2).setCellValue("Monto");

		FileOutputStream fileOut = null;

		for (int i = 1; i <= numCuotas; i++) {
			Amortizacion amortizacion = new Amortizacion();

			meses = fechaPago.getMonth();
			fechaPago.setMonth(meses);

			amortizacion.setFechaPago(fechaPago);
			amortizacion.setNumeroCuota(i);
			amortizacion.setValor(valorCuotas);

			amortizacion.setCredito(credito);

			ejb.guardarAmortizacion(amortizacion);

			HSSFRow row = sheet.createRow((short) i);

			row.createCell(0).setCellValue(i);

			row.createCell(1).setCellValue(fechaPago);
			row.createCell(2).setCellValue(valorCuotas);

			fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);

			fechaPago.setMonth(meses + 1);
		}

		// Se genera el documento

		newTransaccion.setFecha(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		newTransaccion.setTipo("Credito");
		newTransaccion.setMonto(credito.getMonto());

		cuenta.setSaldo(saldoNuevo);

		ejb.actualizarCuenta(cuenta);
		ejb.guardarTransaccion(newTransaccion);

		System.out.println("Your excel file has been generated!");

		ejb.enviarCorreo1("CREDITO", "Se le adjunta la Tabla de amortizaciones de su Credito",
				cuenta.getCliente().getPersona().getCorreo(),
				"/Users/italomendieta/bin/wildfly18/bin/Amortizacion.xls");

		fileOut.close();
	}

	public void upload() {
		try (InputStream input = file.getInputStream()) {
			String fileName = file.getName();
			System.out.println(fileName);
			Files.copy(input, new File(folder + cuenta.getNumeroCuenta() + ".pdf").toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public List<Credito> getListCreditos() {
		return listCreditos;
	}

	public void setListCreditos(List<Credito> listCreditos) {
		this.listCreditos = listCreditos;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}
