package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Espacio {
	private int ancho;
	private int alto;
	private NaveJugador naveJugador;
	private ArrayList<ObjetoJuegoActualizable> listaObjetoJuego = new ArrayList<>();
	private ArrayList<Proyectil> listaProyectilesEnemigos = new ArrayList<>();
	private Oleada oleada;
	private MuroEnergia muro;
	
	public Espacio(int ancho, int alto, int posicionNaveJugadorX, int posicionNaveJugadorY, 
	               Observador observadorNave, Observador observadorNaveEnemiga, Observador observadorMuro) {
		this.ancho = ancho;
		this.alto = alto;
		naveJugador = new NaveJugador(posicionNaveJugadorX, posicionNaveJugadorY, observadorNave, ancho);
		
		// Crear oleada
		oleada = new Oleada(ancho, alto, observadorNaveEnemiga, 2);
		
		// Crear muro de energía (arriba de la nave)
		muro = new MuroEnergia(350, 300, 100, 50, observadorMuro);
	}

	public NaveJugador getNaveJugador() {
		return naveJugador;
	}
	
	public Oleada getOleada() {
		return oleada;
	}
	
	public MuroEnergia getMuro() {
		return muro;
	}

	public void agregar(ObjetoJuegoActualizable actualizable) {
		listaObjetoJuego.add(actualizable);
	}
	
	public void agregarProyectilEnemigo(Proyectil proyectil) {
		listaProyectilesEnemigos.add(proyectil);
	}

	public void acualizarPosiciones() {
		// Actualizar proyectiles del jugador
		Iterator<ObjetoJuegoActualizable> iterJugador = listaObjetoJuego.iterator();
		while (iterJugador.hasNext()) {
			ObjetoJuegoActualizable obj = iterJugador.next();
			obj.actualizarPosicion();
			
			// Eliminar si sale de pantalla
			if (obj.getY() < -100 || obj.getY() > alto + 100) {
				iterJugador.remove();
			}
		}
		
		// Actualizar proyectiles enemigos
		Iterator<Proyectil> iterEnemigo = listaProyectilesEnemigos.iterator();
		while (iterEnemigo.hasNext()) {
			Proyectil proy = iterEnemigo.next();
			proy.actualizarPosicion();
			
			if (proy.getY() > alto + 100) {
				iterEnemigo.remove();
			}
		}
	}
	
	public ArrayList<ObjetoJuegoActualizable> getProyectilesJugador() {
		return listaObjetoJuego;
	}
	
	public ArrayList<Proyectil> getProyectilesEnemigos() {
		return listaProyectilesEnemigos;
	}
	
	public void removerProyectilJugador(ObjetoJuegoActualizable obj) {
		listaObjetoJuego.remove(obj);
	}
	
	public void removerProyectilEnemigo(Proyectil proy) {
		listaProyectilesEnemigos.remove(proy);
	}
}
