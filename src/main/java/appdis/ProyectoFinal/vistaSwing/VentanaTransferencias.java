package appdis.ProyectoFinal.vistaSwing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class VentanaTransferencias extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTransferencias frame = new VentanaTransferencias();
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
	public VentanaTransferencias() {
		setTitle("Transferencias");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);

	}

}
