package appdis.ProyectoFinal.vistaSwing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class VentanaDeposito extends JInternalFrame {

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

	}

}
