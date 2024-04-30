package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class VentanaPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaCoches;
	private Usuario usuarioLogeado;

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

        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
		Query q = pm.newQuery("javax.jdo.query.SQL", "SELECT * FROM coche");
		List<Object[]> results = q.executeList();
		for (Object[] row : results) {
			Marca marca = Marca.valueOf((String) row[1]);
			Color color = Color.valueOf((String) row[3]);
			Coche coche = new Coche(
				marca,          	// marca
				(Integer)row[2],    // año
				color,          	// color
				(Integer)row[4],	// km
				(Integer)row[5],	// precio
				(Boolean)row[6]		// estado
			);
			coche.setId((Integer)row[0]);
			coches.add(coche);
		}
		q.closeAll();
		
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

					// 2. Obtener el objeto y eliminar el coche de la base de datos
					// CocheTableModel model = (CocheTableModel) tablamodelo.getModel();
					Coche coche = tablamodelo.getCocheAt(selectedRow);
					// System.out.println("Coche a eliminar: " + coche);

					try {
						System.out.println("Eliminando coche...");
						LogicaCliente logicaCliente = new LogicaCliente("localhost", "8080");
						logicaCliente.eliminarCoche(coche); // eliminarCoche acepta un objeto Coche

					// 3. Actualizar la tabla
					// ... (pendiente de implementar)

						JOptionPane.showMessageDialog(null, "Coche eliminado");

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "No se ha eliminado el coche: " + ex.getMessage());
					}
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
	
	public static void main(String[] args) {
		VentanaPrincipal vp = new VentanaPrincipal();
	}
}
