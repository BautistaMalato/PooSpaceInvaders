package controlador;

import modelo.*;

public class JuegoController {
	private Espacio espacio;
	private Oleada oleada;
	private MuroEnergia muro;
	
	public JuegoController(int anchoEspacio, int altoEspacio, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador observadorNave) {
		Observador obsNaveEnemiga = new Observador() {
			@Override public void mover(int x, int y) {}
			@Override public int getAncho() { return 40; }
			@Override public int getAlto() { return 30; }
		};
		Observador obsMuro = new Observador() {
			@Override public void mover(int x, int y) {}
			@Override public int getAncho() { return 100; }
			@Override public int getAlto() { return 50; }
		};
		
		espacio = new Espacio(anchoEspacio, altoEspacio, posicionNaveJugadorX, posicionNaveJugadorY, observadorNave, obsNaveEnemiga, obsMuro);
		oleada = espacio.getOleada();
		muro = espacio.getMuro();
	}
	
	public void setOleada(Oleada oleada) {
		this.oleada = oleada;
	}
	
	public void setMuro(MuroEnergia muro) {
		this.muro = muro;
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
	
	public Espacio getEspacio() {
		return espacio;
	}
	
	public Oleada getOleada() {
		return oleada;
	}
	
	public MuroEnergia getMuro() {
		return muro;
	}
}
