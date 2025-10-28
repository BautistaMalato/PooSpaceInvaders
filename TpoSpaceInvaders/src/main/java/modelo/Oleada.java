package modelo;

import java.util.ArrayList;
import java.util.List;

public class Oleada {
	private List<NaveInvasora> naves;
	private int anchoEspacio;
	private int altoEspacio;
	private int velocidadHorizontal;
	
	public Oleada(int anchoEspacio, int altoEspacio, Observador observadorNave, int velocidadHorizontal) {
		this.anchoEspacio = anchoEspacio;
		this.altoEspacio = altoEspacio;
		this.velocidadHorizontal = velocidadHorizontal;
		this.naves = new ArrayList<>();
		
		// Crear 15 naves en 3 filas de 5
		int anchoNave = 40;
		int altoNave = 30;
		int separacionX = 60;
		int separacionY = 50;
		
		for (int fila = 0; fila < 3; fila++) {
			for (int columna = 0; columna < 5; columna++) {
				int x = columna * separacionX + 50;
				int y = fila * separacionY + 50;
				NaveInvasora nave = new NaveInvasora(x, y, 0, observadorNave, anchoEspacio, velocidadHorizontal);
				naves.add(nave);
			}
		}
	}
	
	public List<NaveInvasora> getNaves() {
		return naves;
	}
	
	public void moverTodas() {
		for (NaveInvasora nave : naves) {
			nave.moverHorizontal();
		}
	}
	
	public boolean llegoAlExtremo() {
		return naves.stream().anyMatch(n -> 
			n.getX() >= anchoEspacio - 100 || n.getX() <= 0
		);
	}
	
	public void descenderTodas() {
		for (NaveInvasora nave : naves) {
			nave.descender();
		}
	}
	
	public boolean todasEliminadas() {
		return naves.isEmpty();
	}
}
