package modelo;

import java.util.ArrayList;

public class Espacio {
	private int ancho;
	private int alto;
	private NaveJugador naveJugador;
	private ArrayList<ObjetoJuegoActualizable> listaObjetoJuego = new ArrayList<>();
	
	public Espacio(int ancho, int alto, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador observadorNave) {
		this.ancho = ancho;
		this.alto = alto;
		naveJugador = new NaveJugador(posicionNaveJugadorX, posicionNaveJugadorY, observadorNave, ancho);
	}

	public NaveJugador getNaveJugador() {
		return naveJugador;
	}

	public void agregar(ObjetoJuegoActualizable actualizable) {
		listaObjetoJuego.add(actualizable);
	}

	public void acualizarPosiciones() {
		for (ObjetoJuegoActualizable actualizable: listaObjetoJuego) {
			actualizable.actualizarPosicion();
		}
	}

}
