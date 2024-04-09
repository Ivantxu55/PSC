package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class VentanaPrincipalAdmins extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		
		JLabel lFiltros = new JLabel("Filtros:");
		JButton btnBuscar = new JButton("Buscar");
		JLabel lMarcas = new JLabel("Marcas:");
		JCheckBox cbSubaru = new JCheckBox("Subaru");
		JCheckBox cbMercedes = new JCheckBox("Mercedes");
		
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
		
		//TODO
		// Configuración de los eventos.
		
		
		// Asignación de los componentes a los contenedores.
		pNorte.add(btnInicio);
		pNorte.add(pNombreTienda);
		pNorte.add(pPanelVacio1);
		pNorte.add(pPanelVacio2);
		pNorte.add(pPanelVacio3);
		
		pOeste.add(lFiltros);
		pOeste.add(btnBuscar);
		pOeste.add(lMarcas);
		pOeste.add(cbSubaru);
		pOeste.add(cbMercedes);
		
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
		
		pSur.add(btnEditar);
		
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
