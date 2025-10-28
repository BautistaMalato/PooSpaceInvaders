package vista;

import javax.swing.*;

public class Ventana extends JFrame {
	private JPanel panelActual;
	
	public Ventana() {
		PanelInicial panelInicial = new PanelInicial(this);
		cambiarPanel(panelInicial);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void cambiarPanel(JPanel nuevoPanel) {
		if (panelActual != null) {
			remove(panelActual);
		}
		panelActual = nuevoPanel;
		setContentPane(nuevoPanel);
		revalidate();
		repaint();
		if (panelActual.isPreferredSizeSet()) {
			pack();
			setLocationRelativeTo(null);
		}
	}
}
