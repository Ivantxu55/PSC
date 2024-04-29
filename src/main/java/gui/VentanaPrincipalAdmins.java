package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import domain.jdo.Coche;
import domain.jdo.Color;
import metodosGui.MetodosGUI;

import domain.jdo.Marca;

public class VentanaPrincipalAdmins extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaCoches;

	public VentanaPrincipalAdmins() {
		
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
		JButton btnCerrarSesion = new JButton("Cerrar sesion");
		
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
		
		JButton btnEditar = new JButton("Editar");
		JButton btnCrearCoche = new JButton("Nuevo coche");
		JButton btnEliminar = new JButton("Eliminar");
		
		Coche c = new Coche();
		ArrayList<Coche> coches = new ArrayList<Coche>();
		coches.add(c);
		CocheTableModel tablamodelo = new CocheTableModel(coches);
		tablaCoches = new JTable(tablamodelo);
		pCentro.add(tablaCoches);
		
		//TODO
		// Configuración de los eventos.
		
		btnInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MetodosGUI m = new MetodosGUI();
				m.abrirVentanaInicioAdmins();
				dispose();
				
			}
		});
		
		btnCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
		
		btnCrearCoche.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// abrir una cmd donde se incluyan los siguientes datos: marca, anyo, color, kilometraje, precio, estado
				// marca = JOptionPane.showInputDialog("Introduce la marca del coche");
				// anyo = JOptionPane.showInputDialog("Introduce el año del coche");
				// color = JOptionPane.showInputDialog("Introduce el color del coche");
				// kilometraje = JOptionPane.showInputDialog("Introduce el kilometraje del coche");	
				// precio = JOptionPane.showInputDialog("Introduce el precio del coche");
				// estado = JOptionPane.showInputDialog("Introduce el estado del coche");

				Marca marca = Marca.Ford;
				int anyo = 2020;
				Color color = Color.Black;
				int kilometraje = 5000;
				int precio = 20000;
				boolean estado = true;

				// llamar al método correspondiente que conecte con la API y cree el coche
				System.out.println("Creando coche...");
				Coche coche = new Coche(2, marca, anyo, color, kilometraje, precio, estado);
				LogicaCliente logicaCliente = new LogicaCliente("localhost", "8080");
				logicaCliente.agregarCoche(coche);

				// Coche coche = new Coche(1, Marca.Ford, 2020, Color.Black, 5000, 20000, true);
				// JOptionPane.showMessageDialog(null, "Coche creado exitosamente");
				
				
			}
		});
		
		btnEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			
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
		
		pSur.add(btnCrearCoche);
		pSur.add(btnEditar);
		pSur.add(btnEliminar);
		
		add(pNorte, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pOeste, BorderLayout.WEST);
		add(pSur, BorderLayout.SOUTH);

		// Configuración de la ventana.
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		VentanaPrincipalAdmins vpa = new VentanaPrincipalAdmins();
	}
}
