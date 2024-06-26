package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

import javax.jdo.Query;

import domain.jdo.Coche;
import domain.jdo.Color;
import domain.jdo.Marca;
import domain.jdo.Usuario;
import metodosGui.MetodosGUI;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
/**
 * Ventana principal.
 * Ventana que muestra los coches disponibles en la tienda y permite filtrarlos y comprarlos.
 * @return VentanaPrincipal
 */

public class VentanaPrincipal extends JFrame{
	
	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	private JTable tablaCoches;
	private Usuario usuarioLogeado;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaPrincipal() {

		
		usuarioLogeado = null;
		
		// Configuración de la ventana.
		setTitle("Menú principal");
		setSize(1100, 800);
		setLocation(275, 0);
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
		JButton btnRegistrarse = new JButton("Registrarse");
		JButton btnLogin = new JButton("Login");
		
		JLabel lFiltros = new JLabel("Filtros:");
		JButton btnBuscar = new JButton("Buscar");
		JLabel lMarcas = new JLabel("Marcas:");
		JComboBox<String> comboBMarcas = new JComboBox<String>();
		comboBMarcas.addItem("Subaru");
		comboBMarcas.addItem("Mercedes");
		
		JButton btnQuitarFiltros = new JButton("Quitar filtros");
		
		JLabel lColor = new JLabel("Color:");
		JCheckBox cbBlue = new JCheckBox("Azul");
		JCheckBox cbWhite = new JCheckBox("Blanco");
		JCheckBox cbBlack = new JCheckBox("Rojo");
		
		JLabel lPrecio = new JLabel("Precio:");
		JSlider sPrecio = new JSlider(0, 10000, 10000);
		sPrecio.setMajorTickSpacing(2000);
		sPrecio.setMinorTickSpacing(1000);
		sPrecio.setPaintTicks(true);
		sPrecio.setPaintLabels(true);
		
		JLabel lEstado = new JLabel("Estado:");
		JCheckBox cbNuevo = new JCheckBox("Nuevo");
		JCheckBox cbUsado= new JCheckBox("Usado");
		
		JLabel lAnyo = new JLabel("Año:");
		JTextField txtAnyo1 = new JTextField();
		JTextField txtAnyo2 = new JTextField();
		
		JLabel lKilometraje = new JLabel("Kilometros:");
		JTextField txtKm = new JTextField();
		
		JButton btnComprar = new JButton("Comprar");


		ArrayList<Coche> coches = new ArrayList<Coche>();
		
		LogicaCliente logicaCliente = new LogicaCliente("localhost", "8080");
		try {
			ArrayList<Coche> nuevosCoches = logicaCliente.obtenerCoches();
			coches.addAll(nuevosCoches);  // Agrega elementos sin reasignar la referencia
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al obtener los coches: " + e.getMessage());
		}

		CocheTableModel tablamodelo = new CocheTableModel(coches);
		tablaCoches = new JTable(tablamodelo);
		pCentro.add(tablaCoches);
		
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
		
		btnRegistrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaRegistrarse vr = new VentanaRegistrarse();
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
		
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnQuitarFiltros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnComprar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Sin usuarios no se puede realizar la compra.
				
				if (usuarioLogeado == null) {
					JOptionPane.showMessageDialog(null, "No puede comprar un coche. Registrese o cree una cuenta");
				} else {
					// 1. Obtener la fila con el coche seleciconado
					int selectedRow = tablaCoches.getSelectedRow();
					if (selectedRow == -1) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un coche antes de eliminar.");
						return;
					}

					// 2. Obtener el objeto
					Coche coche = tablamodelo.getCocheAt(selectedRow);
					VentanaPago vp = new VentanaPago(usuarioLogeado, coche);
					dispose();
				}
			}
		});
		
		
		// Asignación de los componentes a los contenedores.
		pNorte.add(btnInicio);
		pNorte.add(pNombreTienda);
		pNorte.add(pPanelVacio1);
		pNorte.add(pPanelVacio2);
		pNorte.add(pPanelVacio3);
		pNorte.add(btnRegistrarse);
		pNorte.add(btnLogin);
		
		pOeste.add(lFiltros);
		pOeste.add(btnBuscar);
		pOeste.add(lMarcas);
		pOeste.add(comboBMarcas);
		
		pOeste.add(lKilometraje);
		pOeste.add(txtKm);
		
		pOeste.add(lAnyo);
		pOeste.add(txtAnyo1);
		pOeste.add(txtAnyo2);
		
		pOeste.add(lColor);
		pOeste.add(cbBlue);
		pOeste.add(cbWhite);
		pOeste.add(cbBlack);
		pOeste.add(lPrecio);
		pOeste.add(sPrecio);
		pOeste.add(lEstado);
		pOeste.add(cbNuevo);
		pOeste.add(cbUsado);
		pOeste.add(btnQuitarFiltros);
		
		pSur.add(btnComprar);
		
		add(pNorte, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pOeste, BorderLayout.WEST);
		add(pSur, BorderLayout.SOUTH);

		// Configuración de la ventana.
		setVisible(true);
		
	}
	
	public VentanaPrincipal(Usuario userLog) {
		
		usuarioLogeado = userLog;

		// Configuración de la ventana.
		setTitle("Menú principal");
		setSize(1100, 800);
		setLocation(275, 0);
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
		JButton btnVerPerfil = new JButton("Ver perfil");
		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		
		JLabel lFiltros = new JLabel("Filtros:");
		JButton btnBuscar = new JButton("Buscar");
		JLabel lMarcas = new JLabel("Marcas:");
		JComboBox<String> comboBMarcas = new JComboBox<String>();
		comboBMarcas.addItem("Subaru");
		comboBMarcas.addItem("Mercedes");
		
		JButton btnQuitarFiltros = new JButton("Quitar filtros");
		
		JLabel lColor = new JLabel("Color:");
		JCheckBox cbBlue = new JCheckBox("Azul");
		JCheckBox cbWhite = new JCheckBox("Blanco");
		JCheckBox cbBlack = new JCheckBox("Rojo");
		
		JLabel lPrecio = new JLabel("Precio:");
		JSlider sPrecio = new JSlider(0, 10000, 10000);
		sPrecio.setMajorTickSpacing(2000);
		sPrecio.setMinorTickSpacing(1000);
		sPrecio.setPaintTicks(true);
		sPrecio.setPaintLabels(true);
		
		JLabel lEstado = new JLabel("Estado:");
		JCheckBox cbNuevo = new JCheckBox("Nuevo");
		JCheckBox cbUsado= new JCheckBox("Usado");
		
		JLabel lAnyo = new JLabel("Año:");
		JTextField txtAnyo1 = new JTextField();
		JTextField txtAnyo2 = new JTextField();
		
		JLabel lKilometraje = new JLabel("Kilometros:");
		JTextField txtKm = new JTextField();
		
		JButton btnComprar = new JButton("Comprar");


		ArrayList<Coche> coches = new ArrayList<Coche>();
		
		LogicaCliente logicaCliente = new LogicaCliente("localhost", "8080");
		try {
			ArrayList<Coche> nuevosCoches = logicaCliente.obtenerCoches();
			coches.addAll(nuevosCoches);  // Agrega elementos sin reasignar la referencia
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al obtener los coches: " + e.getMessage());
		}
		
		CocheTableModel tablamodelo = new CocheTableModel(coches);
		tablaCoches = new JTable(tablamodelo);
		pCentro.add(tablaCoches);
		
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
		
		btnVerPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaVerPerfil vrp = new VentanaVerPerfil(usuarioLogeado);
				dispose();
				
			}
		});
		
		btnCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				usuarioLogeado = null;
				MetodosGUI m = new MetodosGUI();
				m.abrirVentanaInicio();
				dispose();
				
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnQuitarFiltros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnComprar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Sin usuarios no se puede realizar la compra.
				
				if (usuarioLogeado == null) {
					JOptionPane.showMessageDialog(null, "No puede comprar un coche. Registrese o cree una cuenta");
				} else {
					// 1. Obtener la fila con el coche seleciconado
					int selectedRow = tablaCoches.getSelectedRow();
					if (selectedRow == -1) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un coche antes de eliminar.");
						return;
					}

					// 2. Obtener el objeto
					Coche coche = tablamodelo.getCocheAt(selectedRow);
					VentanaPago vp = new VentanaPago(usuarioLogeado, coche);
					dispose();
				}
			}
		});
		
		
		// Asignación de los componentes a los contenedores.
		pNorte.add(btnInicio);
		pNorte.add(pNombreTienda);
		pNorte.add(pPanelVacio1);
		pNorte.add(pPanelVacio2);
		pNorte.add(pPanelVacio3);
		pNorte.add(btnVerPerfil);
		pNorte.add(btnCerrarSesion);
		
		pOeste.add(lFiltros);
		pOeste.add(btnBuscar);
		pOeste.add(lMarcas);
		pOeste.add(comboBMarcas);
		
		pOeste.add(lKilometraje);
		pOeste.add(txtKm);
		
		pOeste.add(lAnyo);
		pOeste.add(txtAnyo1);
		pOeste.add(txtAnyo2);
		
		pOeste.add(lColor);
		pOeste.add(cbBlue);
		pOeste.add(cbWhite);
		pOeste.add(cbBlack);
		pOeste.add(lPrecio);
		pOeste.add(sPrecio);
		pOeste.add(lEstado);
		pOeste.add(cbNuevo);
		pOeste.add(cbUsado);
		pOeste.add(btnQuitarFiltros);
		
		pSur.add(btnComprar);
		
		add(pNorte, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pOeste, BorderLayout.WEST);
		add(pSur, BorderLayout.SOUTH);

		// Configuración de la ventana.
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		VentanaPrincipal vp = new VentanaPrincipal();
	}
}
