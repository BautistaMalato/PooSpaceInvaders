package controlador;

import modelo.Espacio;
import modelo.NaveJugador;
import modelo.Observador;
import modelo.Rayo;

public class JuegoController {
	private Espacio espacio;
	
	public JuegoController(int anchoEspacio, int altoEspacio, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador observadorNave) {
		espacio = new Espacio(anchoEspacio, altoEspacio, posicionNaveJugadorX, posicionNaveJugadorY, observadorNave);
	}

	public void moverNaveJugadorDerecha() {
		NaveJugador naveJugador = espacio.getNaveJugador();
        naveJugador.moverDerecha();
	}

	public void moverNaveJugador(int x) {
		NaveJugador naveJugador = espacio.getNaveJugador();
        naveJugador.mover(x);
	}

	public void disparar(Observador observador) {
		NaveJugador naveJugador = espacio.getNaveJugador();
        Rayo disparo = naveJugador.disparar(observador);
        espacio.agregar(disparo);
	}

	public void actualizarPosiciones() {
		espacio.acualizarPosiciones();
	}

}
