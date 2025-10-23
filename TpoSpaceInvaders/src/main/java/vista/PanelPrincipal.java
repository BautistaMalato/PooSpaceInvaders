package vista;

import controlador.JuegoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelPrincipal extends JPanel {
	private int ancho;
	private int alto;
	private ImagenNave imagenNave;
	private JuegoController juegoController;
	
	public PanelPrincipal() {
		ancho = 800;
		alto = 600;
		setLayout(null);
		setPreferredSize(new Dimension(ancho, alto));
		
        imagenNave = new ImagenNave();
        add(imagenNave);
        
        juegoController = new JuegoController(ancho, alto, 400, 500, imagenNave);
                
        interceptarTeclado();
        interceptarMouse();
        simularMovimientos();
	}
	
	private void interceptarTeclado() {
		setFocusable(true);
        addKeyListener(new KeyAdapter() {
        	@Override
    	    public void keyPressed(KeyEvent evento) {
    	        int tecla = evento.getKeyCode();
    	        if (tecla == KeyEvent.VK_RIGHT) {
    	            juegoController.moverNaveJugadorDerecha();
    	        } else if (tecla == KeyEvent.VK_SPACE) {
    	        	ImagenRayo imagenRayo = new ImagenRayo();
    	        	add(imagenRayo);
    	        	juegoController.disparar(imagenRayo);
    	        }
    		}	
		});
	}
	
	private void interceptarMouse() {
		addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                juegoController.moverNaveJugador(e.getX());
            }
        });
	}
	
    private void simularMovimientos() {
    	Timer gameLoop = new Timer(20, new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent e) {
    			juegoController.actualizarPosiciones();
            }
        });
        gameLoop.start();
    }
    
}
