package appdis.ProyectoFinal.vistaSwing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class VentanaDeposito extends JInternalFrame {
	private JTextField txtCuenta;
	private JTextField txtMonto;

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
		setTitle("Depositos");
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
		lblNewLabel_2.setBounds(94, 106, 61, 16);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(115, 199, 117, 29);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(272, 199, 117, 29);
		getContentPane().add(btnCancelar);
		
		txtCuenta = new JTextField();
		txtCuenta.setBounds(167, 50, 178, 26);
		getContentPane().add(txtCuenta);
		txtCuenta.setColumns(10);
		
		txtMonto = new JTextField();
		txtMonto.setBounds(167, 101, 130, 26);
		getContentPane().add(txtMonto);
		txtMonto.setColumns(10);

	}
}
