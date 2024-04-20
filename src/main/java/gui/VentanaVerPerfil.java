package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.*;

public class VentanaVerPerfil extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaVerPerfil() {
		
		// Configuración de la ventana.
		setTitle("Ventana ver perfil");
		setSize(800, 600);
		setLocation(400, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// Creación de contenedores de la ventana y su configuración.
		JPanel pNorte = new JPanel();
		pNorte.setLayout(new GridLayout(1,7));
		JPanel pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(6,1));
		JPanel pSur = new JPanel();
		pSur.setLayout(new GridLayout(1,3));
		
		// Creación de componentes de la ventana y su configuración.
		JButton btnInicio = new JButton("Inicio");
		JPanel pNombreTienda = new JPanel();
		JPanel pVacio1 = new JPanel();
		JButton btnDesconectarse = new JButton("Desconectarse");
		JLabel lUsuario = new JLabel("Usuario: ");
		JLabel lContrasenya = new JLabel("Contraseña: ");
		JLabel lEmail = new JLabel("Email: ");
		JLabel lComprasRealizadas = new JLabel("Compras realizadas:");
		
		// Configuración de los eventos.
		
		btnInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaPrincipal vp = new VentanaPrincipal();
				dispose();
				
			}
		});
		
		btnDesconectarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// Asignación de los componentes a los contenedores.
		pNorte.add(btnInicio);
		pNorte.add(pNombreTienda);
		pNorte.add(pVacio1);
		pNorte.add(btnDesconectarse);
		pCentro.add(lUsuario);
		pCentro.add(lContrasenya);
		pCentro.add(lEmail);
		pCentro.add(lComprasRealizadas);
		
		add(pNorte, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pSur, BorderLayout.SOUTH);
		
		// Configuración de la ventana.
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaVerPerfil vvp = new VentanaVerPerfil();
	}

}
