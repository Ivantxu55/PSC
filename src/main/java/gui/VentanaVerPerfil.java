package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import domain.jdo.Usuario;

import javax.swing.*;

public class VentanaVerPerfil extends JFrame{
	
	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	private Usuario usuarioLogeado;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaVerPerfil(Usuario userLog) {
		
		usuarioLogeado = userLog;
		
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
		JButton btnEliminarCuenta = new JButton("Eliminar cuenta");
		JPanel pNombreTienda = new JPanel();
		JPanel pVacio1 = new JPanel();
		JButton btnDesconectarse = new JButton("Desconectarse");
		JLabel lUsuario = new JLabel("Usuario: ");
		JLabel lContrasenya = new JLabel("Contraseña: ");
		JLabel lEmail = new JLabel("Email: ");
		JLabel lComprasRealizadas = new JLabel("Compras realizadas:");
		
		// Configuración de los eventos.
		
		btnEliminarCuenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				leerBinarioUsuarios();
				
				for (int i = 0; i < listaUsuarios.size(); i++) {
					if (listaUsuarios.get(i).getNombre().equals(usuarioLogeado.getNombre()) && 
							listaUsuarios.get(i).getCorreo().equals(usuarioLogeado.getCorreo())) {
						listaUsuarios.remove(i);
						break;
					}
				}
				
				escribirBinarioUsuarios();
				
			}
		});
		
		btnInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaPrincipal vp = new VentanaPrincipal(usuarioLogeado);
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
		pNorte.add(btnEliminarCuenta);
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
	
	public void leerBinarioUsuarios() {
        try (FileInputStream fis = new FileInputStream("src\\main\\resources\\usuarios.bin");
                ObjectInputStream ois = new ObjectInputStream(fis)) {

               while (fis.available() > 0) {
                   Usuario usuario = (Usuario) ois.readObject();
                   System.out.println("Usuario leído: " + usuario);
                   listaUsuarios.add(usuario);
               }

           } catch (Exception e) {
               e.printStackTrace();
           }
	}
	
	public void escribirBinarioUsuarios() {
        try (FileOutputStream fos = new FileOutputStream("src\\main\\resources\\usuarios.bin");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        	
        	  for (int i = 0; i < listaUsuarios.size(); i++) {
        		  oos.writeObject(listaUsuarios.get(i));
        	  }
               
           } catch (Exception e) {
               e.printStackTrace();
           }
	}

}
