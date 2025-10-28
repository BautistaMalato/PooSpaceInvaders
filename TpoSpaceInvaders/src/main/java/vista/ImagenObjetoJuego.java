package vista;

import modelo.Observador;

import javax.swing.*;

public class ImagenObjetoJuego extends JLabel implements Observador {
    protected int anchoInicial;
    protected int altoInicial;
    
	public ImagenObjetoJuego(int ancho, int alto) {
		this.anchoInicial = ancho;
		this.altoInicial = alto;
		setSize(ancho, alto);
	}

    public void mover(int x, int y) {
        setBounds(x, y, getAncho(), getAlto());
    }

	public int getAncho() {
		return getWidth();
	}

	public int getAlto() {
		return getHeight();
	}
    
	public void setAncho(int ancho) {
		this.anchoInicial = ancho;
		setSize(ancho, getAlto());
	}
	
	public void setAlto(int alto) {
		this.altoInicial = alto;
		setSize(getAncho(), alto);
	}
    
}
