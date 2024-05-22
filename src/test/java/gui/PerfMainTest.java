package gui;

import static org.junit.Assert.assertNotNull;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assume;

import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

import java.awt.GraphicsEnvironment;


public class PerfMainTest {

    private Main mainWindow;

    @Before
    public void setUp() {
        Assume.assumeFalse("The test is running in a headless environment", GraphicsEnvironment.isHeadless());

        mainWindow = new Main();
    }

    @After
    public void tearDown() {
        if (mainWindow != null) {
            mainWindow.dispose();
        }
    }

    @Test
    @JUnitPerfTest(threads = 10, durationMs = 1000)
    @JUnitPerfTestRequirement(executionsPerSec = 200, allowedErrorPercentage = (float) 0.1)
    public void testMainWindowCreationPerformance() {
        assertNotNull(mainWindow);
    }

    @Test
    @JUnitPerfTest(threads = 10, durationMs = 1000)
    @JUnitPerfTestRequirement(executionsPerSec = 200, allowedErrorPercentage = (float) 0.1)
    public void testAdminButtonPerformance() {
        JButton btnAdmin = getButton(mainWindow, "Administrador");
        assertNotNull(btnAdmin);
        
        for (int i = 0; i < 10; i++) {
            btnAdmin.doClick();
        }
    }

    @Test
    @JUnitPerfTest(threads = 10, durationMs = 1000)
    @JUnitPerfTestRequirement(executionsPerSec = 200, allowedErrorPercentage = (float) 0.1)
    public void testUsuarioButtonPerformance() {
        JButton btnUsuario = getButton(mainWindow, "Usuario");
        assertNotNull(btnUsuario);

        for (int i = 0; i < 10; i++) {
            btnUsuario.doClick();
        }
    }

    private JButton getButton(Main mainWindow, String text) {
        for (Component component : mainWindow.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component panelComponent : panel.getComponents()) {
                    if (panelComponent instanceof JButton) {
                        JButton button = (JButton) panelComponent;
                        if (button.getText().equals(text)) {
                            return button;
                        }
                    }
                }
            }
        }
        return null;
    }
}
