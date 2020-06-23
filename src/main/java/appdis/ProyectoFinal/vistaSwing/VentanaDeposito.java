package appdis.ProyectoFinal.vistaSwing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class VentanaDeposito extends JInternalFrame {
	private JTextField txtCuenta;
	private JTextField txtMonto;
	private JTextField txtNombres;
	private JTextField txtApellidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDeposito frame = new VentanaDeposito();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaDeposito() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 563, 353);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DEPOSITOS CUENTAS PERSONALES");
		lblNewLabel.setBounds(31, 6, 225, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CUENTA");
		lblNewLabel_1.setBounds(94, 55, 61, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MONTO");
		lblNewLabel_2.setBounds(94, 220, 61, 16);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(115, 272, 117, 29);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(272, 272, 117, 29);
		getContentPane().add(btnCancelar);
		
		txtCuenta = new JTextField();
		txtCuenta.setBounds(167, 50, 178, 26);
		getContentPane().add(txtCuenta);
		txtCuenta.setColumns(10);
		
		txtMonto = new JTextField();
		txtMonto.setBounds(167, 215, 130, 26);
		getContentPane().add(txtMonto);
		txtMonto.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Apellidos");
		lblNewLabel_3.setBounds(94, 158, 61, 16);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nombres");
		lblNewLabel_3_1.setBounds(94, 111, 61, 16);
		getContentPane().add(lblNewLabel_3_1);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(167, 106, 178, 26);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(167, 153, 178, 26);
		getContentPane().add(txtApellidos);
		
		JButton btnNewButton = new JButton("BUSCAR");
		btnNewButton.setBounds(357, 50, 117, 29);
		getContentPane().add(btnNewButton);

	}
}
