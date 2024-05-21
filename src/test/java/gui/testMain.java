package gui;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.Component;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import metodosGui.MetodosGUI;

public class testMain {
	
    private Main mainFrame;
    private MetodosGUI metodosMock;

    @Before
    public void setUp() {
        // Crear la instancia del marco principal
        mainFrame = new Main() {
            @Override
            protected MetodosGUI createMetodosGUI() {
                return metodosMock;
            }
        };
        
        // Mock de la instancia de MetodosGUI
        metodosMock = Mockito.mock(MetodosGUI.class);
    }

    @Test
    public void testBtnAdminAction() {
        // Encontrar el botón "Administrador"
        JButton btnAdmin = findButtonByText(mainFrame, "Administrador");

        // Simular un clic en el botón
        btnAdmin.doClick();

        // Verificar que se llamó a abrirVentanaLoginAdmins
        verify(metodosMock, times(1)).abrirVentanaLoginAdmins();
    }

    @Test
    public void testBtnUsuarioAction() {
        // Encontrar el botón "Usuario"
        JButton btnUsuario = findButtonByText(mainFrame, "Usuario");

        // Simular un clic en el botón
        btnUsuario.doClick();

        // Verificar que se llamó a abrirVentanaInicio
        verify(metodosMock, times(1)).abrirVentanaInicio();
    }

    private JButton findButtonByText(Main mainFrame, String text) {
        for (Component c : mainFrame.getContentPane().getComponents()) {
            if (c instanceof JButton && ((JButton) c).getText().equals(text)) {
                return (JButton) c;
            }
        }
        throw new RuntimeException("No se encontró el botón con el texto: " + text);
    }

}
