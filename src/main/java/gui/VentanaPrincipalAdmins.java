package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import java.util.List;


import domain.jdo.Coche;
import domain.jdo.Color;
import metodosGui.MetodosGUI;

import domain.jdo.Marca;

import java.util.UUID;

import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import java.util.Map;
import java.util.HashMap;

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
		JComboBox<String> comboMarcas = new JComboBox<String>();
		for(Marca m : Marca.values()) {
			comboMarcas.addItem(m.toString());
		}
		
		JButton btnQuitarFiltros = new JButton("Quitar filtros");
		
		JLabel lColor = new JLabel("Color:");
		JComboBox<String> comboColor = new JComboBox<String>();
		for(Color c : Color.values()) {
			comboColor.addItem(c.toString());
		}
		
		JLabel lPrecio = new JLabel("Precio:");
		JSlider sPrecio = new JSlider(0, 10000, 10000);
		sPrecio.setMajorTickSpacing(2000);
		sPrecio.setMinorTickSpacing(1000);
		sPrecio.setPaintTicks(true);
		sPrecio.setPaintLabels(true);
		
		JLabel lEstado = new JLabel("Estado:");
		JRadioButton cbNuevo = new JRadioButton("Nuevo");
		JRadioButton cbUsado= new JRadioButton("Usado");
		
		JLabel lAnyo = new JLabel("Año:");
		JTextField txtAnyo1 = new JTextField();
		JTextField txtAnyo2 = new JTextField();
		
		JLabel lKilometraje = new JLabel("Kilometros:");
		JTextField txtKm = new JTextField();
		
		JButton btnEditar = new JButton("Editar");
		JButton btnCrearCoche = new JButton("Nuevo coche");
		JButton btnEliminar = new JButton("Eliminar");


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
				JDialog d = new JDialog();
				d.setLayout(new FlowLayout());
				d.setSize(300, 100);
				d.setLocation(500, 300);
				JLabel l = new JLabel("¿Está seguro de que desea cerrar la sesión?");
				JButton bSi = new JButton("Sí");
				d.add(l);
				d.add(bSi);
				d.setVisible(true);

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

				try {
					// Captura de datos desde diálogos
					Marca[] choices_marcas = Marca.values(); // Obtener todos los valores del enum Marca
					Marca marca = (Marca) JOptionPane.showInputDialog(
						null, // componente padre
						"Elige la marca del coche:", // mensaje
						"Selección de Marca", // título del diálogo
						JOptionPane.QUESTION_MESSAGE, // tipo de mensaje
						null, // icono (no especificado aquí, por lo tanto, nulo)
						choices_marcas, // arreglo de objetos para seleccionar
						choices_marcas[0] // objeto inicial seleccionado
					);
					// Marca marca = Marca.valueOf(marcaStr);  // Asumiendo que Marca es un enum y el usuario introduce un valor válido
					int anyo = Integer.parseInt(JOptionPane.showInputDialog("Introduce el año del coche"));
					// Crear un JComboBox para los colores disponibles
					Color[] choices_colores = Color.values();
					Color color = (Color) JOptionPane.showInputDialog(null, "Elige el color del coche:",
										"Selección de Color", JOptionPane.QUESTION_MESSAGE, null,
										choices_colores, choices_colores[0]);
					int kilometraje = Integer.parseInt(JOptionPane.showInputDialog("Introduce el kilometraje del coche"));
					int precio = Integer.parseInt(JOptionPane.showInputDialog("Introduce el precio del coche"));
					boolean estado = Boolean.parseBoolean(JOptionPane.showInputDialog("Introduce el estado del coche (true para nuevo, false para usado)"));

					// Creación del objeto Coche
					// String uniqueID = UUID.randomUUID().toString();
					Coche coche = new Coche(marca, anyo, color, kilometraje, precio, estado);
					
					// Llamada a la lógica cliente que conecta con la API
					System.out.println("Creando coche...");
					LogicaCliente logicaCliente = new LogicaCliente("localhost", "8080");
					logicaCliente.agregarCoche(coche);

					JOptionPane.showMessageDialog(null, "Coche creado exitosamente");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "No se ha creado el coche: " + ex.getMessage());
				}
			}
		});
		
		btnEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// Selección del coche en la tabla
				int selectedRow = tablaCoches.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un coche antes de modificar.");
					return;
				}
				Coche cocheActual = tablamodelo.getCocheAt(selectedRow); // Asumimos que este método está implementado en el modelo de tabla

				try {
					Map<String, Object> cambios = new HashMap<>();

					// Diálogo para modificar la marca
					Marca[] choices_marcas = Marca.values();
					Marca marca = (Marca) JOptionPane.showInputDialog(
						null,
						"Elige la nueva marca del coche:",
						"Modificar Marca",
						JOptionPane.QUESTION_MESSAGE,
						null,
						choices_marcas,
						cocheActual.getMarca()
					);
					if (marca != null && marca != cocheActual.getMarca()) cambios.put("marca", marca);

					// Diálogo para modificar el año
					String anyoStr = JOptionPane.showInputDialog("Introduce el nuevo año del coche:", cocheActual.getAnyo());
					if (anyoStr != null && !anyoStr.isEmpty()) {
						int anyo = Integer.parseInt(anyoStr);
						if (anyo != cocheActual.getAnyo()) cambios.put("anyo", anyo);
					}

					// Diálogo para modificar el color
					Color[] choices_colores = Color.values();
					Color color = (Color) JOptionPane.showInputDialog(null, "Elige el nuevo color del coche:",
									"Modificar Color", JOptionPane.QUESTION_MESSAGE, null,
									choices_colores, cocheActual.getColor());
					if (color != null && color != cocheActual.getColor()) cambios.put("color", color);

					// Diálogo para modificar el kilometraje
					String kilometrajeStr = JOptionPane.showInputDialog("Introduce el nuevo kilometraje del coche:", cocheActual.getKilometraje());
					if (kilometrajeStr != null && !kilometrajeStr.isEmpty()) {
						int kilometraje = Integer.parseInt(kilometrajeStr);
						if (kilometraje != cocheActual.getKilometraje()) cambios.put("kilometraje", kilometraje);
					}

					// Diálogo para modificar el precio
					String precioStr = JOptionPane.showInputDialog("Introduce el nuevo precio del coche:", cocheActual.getPrecio());
					if (precioStr != null && !precioStr.isEmpty()) {
						int precio = Integer.parseInt(precioStr);
						if (precio != cocheActual.getPrecio()) cambios.put("precio", precio);
					}

					// Diálogo para modificar el estado (nuevo o usado)
					String estadoStr = JOptionPane.showInputDialog("Introduce el nuevo estado del coche (true para nuevo, false para usado):", cocheActual.isNuevo());
					if (estadoStr != null && !estadoStr.isEmpty()) {
						boolean estado = Boolean.parseBoolean(estadoStr);
						if (estado != cocheActual.isNuevo()) cambios.put("estado", estado);
					}

					if (!cambios.isEmpty()) {
						// Llamada a la lógica cliente que conecta con la API para modificar
						System.out.println("Modificando coche...");
						LogicaCliente logicaCliente = new LogicaCliente("localhost", "8080");
						logicaCliente.modificarCoche(cocheActual, cambios);
						JOptionPane.showMessageDialog(null, "Coche modificado exitosamente");
					} else {
						JOptionPane.showMessageDialog(null, "No se realizó ninguna modificación.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error en la entrada de datos: " + ex.getMessage());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al modificar el coche: " + ex.getMessage());
				}

			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
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
		});


		comboColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Coche> filtrados = new ArrayList<Coche>();
				for(Coche c : coches) {
					if(c.getColor().toString().equals(comboColor.getSelectedItem().toString())) {
						filtrados.add(c);
					}
				}
				tablamodelo.setCoches(filtrados);


			}
		});
		comboMarcas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Coche> filtrados = new ArrayList<Coche>();
				for(Coche c : coches) {
					if(c.getMarca().toString().equals(comboMarcas.getSelectedItem().toString())) {
						filtrados.add(c);
					}
				}
				tablamodelo.setCoches(filtrados);

			}
		});


		cbNuevo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cbUsado.setSelected(false);
				ArrayList<Coche> filtrados = new ArrayList<Coche>();
				for(Coche c : coches) {
					if(c.isNuevo()) {
						filtrados.add(c);
					}
				}
				tablamodelo.setCoches(filtrados);
			}
		});

		cbUsado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cbNuevo.setSelected(false);
				ArrayList<Coche> filtrados = new ArrayList<Coche>();
				for(Coche c : coches) {
					if(!c.isNuevo()) {
						filtrados.add(c);
					}
				}
				tablamodelo.setCoches(filtrados);
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
		pOeste.add(comboMarcas);
		
		pOeste.add(lKilometraje);
		pOeste.add(txtKm);
		
		pOeste.add(lAnyo);
		pOeste.add(txtAnyo1);
		pOeste.add(txtAnyo2);
		
		pOeste.add(lColor);
		pOeste.add(comboColor);
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
