package gui;

import java.awt.*;
import javax.swing.*;

public class VentanaPago extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaPago() {
		
		// Configuración de la ventana.
		setTitle("Menú principal");
		setSize(700, 200);
		setLocation(425, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		// Creación de contenedores de la ventana y su configuración.
		JPanel pNorte = new JPanel();
		pNorte.setLayout(new GridLayout(1,7));
		JPanel pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(1,2));
		JPanel pOeste = new JPanel();
		pOeste.setLayout(new GridLayout(20,1));
		JPanel pSur = new JPanel();
		pSur.setLayout(new FlowLayout());

		// Creación de componentes de la ventana y su configuración.
		JButton btnInicio = new JButton("Inicio");
		JPanel pNombreTienda = new JPanel();
		JPanel pPanelVacio1 = new JPanel();
		JPanel pPanelVacio2 = new JPanel();
		JPanel pPanelVacio3 = new JPanel();
		JButton btnCerrarSesion = new JButton("Cerrar sesion");
		
		JLabel txtDatosCoche = new JLabel("¿El coche que quiere comprar es: ?");
		
		JButton btnConfirmar = new JButton("Confirmar");
		JButton btnCancelar = new JButton("Cancelar");
		
		// Asignación de los componentes a los contenedores.
		pNorte.add(btnInicio);
		pNorte.add(pNombreTienda);
		pNorte.add(pPanelVacio1);
		pNorte.add(pPanelVacio2);
		pNorte.add(pPanelVacio3);
		pNorte.add(btnCerrarSesion);
		
		pCentro.add(txtDatosCoche);
		
		pSur.add(btnConfirmar);
		pSur.add(btnCancelar);
		
		add(pNorte, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pOeste, BorderLayout.WEST);
		add(pSur, BorderLayout.SOUTH);

		// Configuración de la ventana.
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		VentanaPago vp = new VentanaPago();
	}

}
