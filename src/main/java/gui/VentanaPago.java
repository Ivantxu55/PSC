package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import domain.jdo.Coche;
import domain.jdo.Usuario;

public class VentanaPago extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaPago(Usuario usuarioLogeado, Coche cocheComprar) {
		
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
		
		// Configuración de los eventos.
		
		btnConfirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// Eliminar el coche de la base de datos

				try {
					System.out.println("Eliminando coche...");
					LogicaCliente logicaCliente = new LogicaCliente("localhost", "8080");
					logicaCliente.eliminarCoche(cocheComprar); // eliminarCoche acepta un objeto Coche

				// 3. Actualizar la tabla
				// ... (pendiente de implementar)

					JOptionPane.showMessageDialog(null, "Coche eliminado");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "No se ha eliminado el coche: " + ex.getMessage());
				}
				
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
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

}
