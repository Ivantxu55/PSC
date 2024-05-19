package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;

import domain.jdo.Coche;
import domain.jdo.Usuario;
import metodosGui.MetodosGUI;

import javax.swing.*;

public class VentanaRegistrarse extends JFrame{
	
	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaRegistrarse() {
		
		//listaUsuarios.add(new Usuario("", null, "", "", new ArrayList<Coche>()));
		
		// Configuración de la ventana.
		setTitle("Ventana registrarse");
		setSize(750, 300);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// Creación de contenedores de la ventana y su configuración.
		JPanel pNorte = new JPanel();
		pNorte.setLayout(new GridLayout(1,7));
		JPanel pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(4,3));
		JPanel pSur = new JPanel();
		pSur.setLayout(new FlowLayout());

		// Creación de componentes de la ventana y su configuración.
		JButton btnInicio = new JButton("Inicio");
		JPanel pNombreTienda = new JPanel();
		JPanel pPanelVacio1 = new JPanel();
		JPanel pPanelVacio2 = new JPanel();
		JPanel pPanelVacio3 = new JPanel();
		JPanel pPanelVacio4 = new JPanel();
		JButton btnLogin = new JButton("Login");
		
		JLabel lUsuario = new JLabel("Usuario:");
		JTextField txtUsuario = new JTextField();
		JLabel lErrorUsuario = new JLabel("El nombre está escogido o está vacío");
		lErrorUsuario.setForeground(Color.RED);
		lErrorUsuario.setVisible(false);
		JLabel lEmail = new JLabel("Email:");
		JTextField txtEmail = new JTextField();
		JLabel lErrorEmail = new JLabel("El email está en uso o está vacío");
		lErrorEmail.setForeground(Color.RED);
		lErrorEmail.setVisible(false);
		JLabel lContrasenya = new JLabel("Contraseña:");
		JPasswordField txtContrasenya = new JPasswordField();
		JLabel lErrorContrasenya = new JLabel("La contraseña no coincide o está vacía");
		lErrorContrasenya.setForeground(Color.RED);
		lErrorContrasenya.setVisible(false);
		JLabel lRepetirContrasenya = new JLabel("Repetir contraseña:");
		JPasswordField txtRepetirContrasenya = new JPasswordField();
		JLabel lErrorContrasenya2 = new JLabel("La contraseña no coincide o está vacía");
		lErrorContrasenya2.setForeground(Color.RED);
		lErrorContrasenya2.setVisible(false);
		JButton btnAceptar = new JButton("Aceptar");
		
		//TODO
		// Configuración de los eventos.
		
		btnInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MetodosGUI m = new MetodosGUI();
				m.abrirVentanaInicio();
				dispose();
				
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaLogin vl = new VentanaLogin();
				dispose();
				
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Se lee Usuarios.txt y cada usuario se mete en listaUsuarios.
				leerBinarioUsuarios();
					
				// Se comprueba que las contraseñas coincidan.
					if ( !txtContrasenya.getText().equals(txtRepetirContrasenya.getText()) ) {
						lErrorContrasenya.setVisible(true);
						lErrorContrasenya2.setVisible(true);
					} else {
						lErrorContrasenya.setVisible(false);
						lErrorContrasenya2.setVisible(false);
					}
					
					// Se recorre listaUsuarios y se comprueba que el campo no este vacío, ni sea espacio, ni null y que además sea correcto, es decir, 
					// que no se duplique el nombre y el mail.
					for (Usuario u : listaUsuarios) {
						
						if ( txtUsuario.getText().equals("") || txtUsuario.getText().equals(" ") || txtUsuario.getText().equals(null) || 
								txtUsuario.getText().equals(u.getNombre()) ) {
							lErrorUsuario.setVisible(true);
								
								if (txtEmail.getText().equals("") || txtEmail.getText().equals(" ") || txtEmail.getText().equals(null) || 
										txtEmail.getText().equals(u.getCorreo()) ) {
									lErrorEmail.setVisible(true);
									
										if (txtContrasenya.getText().equals("") || txtContrasenya.getText().equals(" ") || 
												txtContrasenya.getText().equals(null) || 
												!txtContrasenya.getText().equals(txtRepetirContrasenya.getText()) ) {
											lErrorContrasenya.setVisible(true);
											
											if (txtRepetirContrasenya.getText().equals("") || txtRepetirContrasenya.getText().equals(" ") || 
													txtRepetirContrasenya.getText().equals(null) || 
													!txtContrasenya.getText().equals(txtRepetirContrasenya.getText())) {
												lErrorContrasenya2.setVisible(true);
												break;
												
											} else {
												lErrorContrasenya2.setVisible(false);
												break;
											}
											
										} else {
											lErrorContrasenya.setVisible(false);
											
											if (txtRepetirContrasenya.getText().equals("") || txtRepetirContrasenya.getText().equals(" ") || 
													txtRepetirContrasenya.getText().equals(null) || 
													!txtContrasenya.getText().equals(txtRepetirContrasenya.getText())) {
												lErrorContrasenya2.setVisible(true);
												break;
												
											} else {
												lErrorContrasenya2.setVisible(false);
												break;
											}
										}
										
									} else {
										lErrorEmail.setVisible(false);
										
										if (txtContrasenya.getText().equals("") || txtContrasenya.getText().equals(" ") || 
												txtContrasenya.getText().equals(null) || 
												!txtContrasenya.getText().equals(txtRepetirContrasenya.getText()) ) {
											lErrorContrasenya.setVisible(true);
											
											if (txtRepetirContrasenya.getText().equals("") || txtRepetirContrasenya.getText().equals(" ") || 
													txtRepetirContrasenya.getText().equals(null) || 
													!txtContrasenya.getText().equals(txtRepetirContrasenya.getText())) {
												lErrorContrasenya2.setVisible(true);
												break;
												
											} else {
												lErrorContrasenya2.setVisible(false);
												break;
											}
											
										} else {
											lErrorContrasenya.setVisible(false);
											
											if (txtRepetirContrasenya.getText().equals("") || txtRepetirContrasenya.getText().equals(" ") || 
													txtRepetirContrasenya.getText().equals(null) || 
													!txtContrasenya.getText().equals(txtRepetirContrasenya.getText())) {
												lErrorContrasenya2.setVisible(true);
												break;
												
											} else {
												lErrorContrasenya2.setVisible(false);
												break;
											}
										}
									}	
						} else {
							lErrorUsuario.setVisible(false);
							
							if (txtEmail.getText().equals("") || txtEmail.getText().equals(" ") || txtEmail.getText().equals(null) || 
									txtEmail.getText().equals(u.getCorreo()) ) {
								lErrorEmail.setVisible(true);
								
									if (txtContrasenya.getText().equals("") || txtContrasenya.getText().equals(" ") || 
											txtContrasenya.getText().equals(null) || 
											!txtContrasenya.getText().equals(txtRepetirContrasenya.getText()) ) {
										lErrorContrasenya.setVisible(true);
										
										if (txtRepetirContrasenya.getText().equals("") || txtRepetirContrasenya.getText().equals(" ") || 
												txtRepetirContrasenya.getText().equals(null) || 
												!txtContrasenya.getText().equals(txtRepetirContrasenya.getText())) {
											lErrorContrasenya2.setVisible(true);
											break;
											
										} else {
											lErrorContrasenya2.setVisible(false);
											break;
										}
										
									} else {
										lErrorContrasenya.setVisible(false);
										
										if (txtRepetirContrasenya.getText().equals("") || txtRepetirContrasenya.getText().equals(" ") || 
												txtRepetirContrasenya.getText().equals(null) || 
												!txtContrasenya.getText().equals(txtRepetirContrasenya.getText())) {
											lErrorContrasenya2.setVisible(true);
											break;
											
										} else {
											lErrorContrasenya2.setVisible(false);
											break;
										}
									}
									
								} else {
									lErrorEmail.setVisible(false);
									
									if (txtContrasenya.getText().equals("") || txtContrasenya.getText().equals(" ") || 
											txtContrasenya.getText().equals(null) || 
											!txtContrasenya.getText().equals(txtRepetirContrasenya.getText()) ) {
										lErrorContrasenya.setVisible(true);
										
										if (txtRepetirContrasenya.getText().equals("") || txtRepetirContrasenya.getText().equals(" ") || 
												txtRepetirContrasenya.getText().equals(null) || 
												!txtContrasenya.getText().equals(txtRepetirContrasenya.getText())) {
											lErrorContrasenya2.setVisible(true);
											break;
											
										} else {
											lErrorContrasenya2.setVisible(false);
											break;
										}
										
									} else {
										lErrorContrasenya.setVisible(false);
										
										if (txtRepetirContrasenya.getText().equals("") || txtRepetirContrasenya.getText().equals(" ") || 
												txtRepetirContrasenya.getText().equals(null) || 
												!txtContrasenya.getText().equals(txtRepetirContrasenya.getText())) {
											lErrorContrasenya2.setVisible(true);
											break;
											
										} else {
											lErrorContrasenya2.setVisible(false);
										}
									}
								}
						}
					}
					
					// Si no ha habido errores se agrga el nuevo usuario.
					if (lErrorUsuario.isVisible() == false && lErrorEmail.isVisible() == false && lErrorContrasenya.isVisible() == false && 
							lErrorContrasenya2.isVisible() == false) {
						
						Usuario nuevoUsuario = new Usuario(txtUsuario.getText(), new Date(), txtEmail.getText(), txtContrasenya.getText(), new ArrayList<Coche>());
						
						anyadirBinarioUsuarios(nuevoUsuario);
						
						dispose();
						VentanaPrincipal vp = new VentanaPrincipal(nuevoUsuario);
						JOptionPane.showMessageDialog(null, "Registro realizado con éxito");
					}
			}
		});
		
		// Asignación de los componentes a los contenedores.
		pNorte.add(btnInicio);
		pNorte.add(pNombreTienda);
		pNorte.add(pPanelVacio1);
		pNorte.add(pPanelVacio2);
		pNorte.add(pPanelVacio3);
		pNorte.add(pPanelVacio4);
		pNorte.add(btnLogin);

		pCentro.add(lUsuario);
		pCentro.add(txtUsuario);
		pCentro.add(lErrorUsuario);
		pCentro.add(lEmail);
		pCentro.add(txtEmail);
		pCentro.add(lErrorEmail);
		pCentro.add(lContrasenya);
		pCentro.add(txtContrasenya);
		pCentro.add(lErrorContrasenya);
		pCentro.add(lRepetirContrasenya);
		pCentro.add(txtRepetirContrasenya);
		pCentro.add(lErrorContrasenya2);
		pSur.add(btnAceptar);
		
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
	
	public void anyadirBinarioUsuarios(Usuario nuevoUsuario) {
        try (FileOutputStream fos = new FileOutputStream("src\\main\\resources\\usuarios.bin");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        	
        	  for (int i = 0; i < listaUsuarios.size(); i++) {
        		  oos.writeObject(listaUsuarios.get(i));
        	  }

               oos.writeObject(nuevoUsuario);
               
           } catch (Exception e) {
               e.printStackTrace();
           }
	}
	
	public static void main(String[] args) {
		VentanaRegistrarse vp = new VentanaRegistrarse();

	}

}
