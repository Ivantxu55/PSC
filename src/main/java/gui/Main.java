package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFrame;

import metodosGui.MetodosGUI;

public class Main extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main() {
		
		// Configuración de la ventana.
		setTitle("Main");
		setSize(800, 600);
		setLocation(400, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// Creación de contenedores de la ventana y su configuración.
		JPanel pCentro = new JPanel();
		pCentro.setLayout(new FlowLayout());
		
		// Creación de componentes de la ventana y su configuración.
		JButton btnAdmin = new JButton("Administrador");
		JButton btnUsuario = new JButton("Usuario");
		
		//TODO
		// Configuración de los eventos.
		
		btnAdmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MetodosGUI m = new MetodosGUI();
				m.abrirVentanaLoginAdmins();	
				dispose();
				
			}
		});
		
		btnUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MetodosGUI m = new MetodosGUI();
				m.abrirVentanaInicio();	
				dispose();
				
			}
		});
		
		// Asignación de los componentes a los contenedores.
		
		pCentro.add(btnAdmin);
		pCentro.add(btnUsuario);
		
		add(pCentro, BorderLayout.CENTER);
		
		// Configuración de la ventana.
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
	}

}
