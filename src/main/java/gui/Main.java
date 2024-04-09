package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Main extends JFrame{
	
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
