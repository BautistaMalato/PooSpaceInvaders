package modelo;
import java.util.ArrayList;

public class Ranking {
	private ArrayList<Jugador> top10 = new ArrayList< >();
	
	public void agregarJugador(String nombre, int puntaje) {
		top10.add(new Jugador(nombre,puntaje));
		
		
	}
	public boolean actualizarPuntaje(String nombre, int nuevopuntaje) {
		for(Jugador j: top10) {
			if (j.getNombre().equalsIgnoreCase(nombre)) {
				j.setpuntaje(nuevopuntaje);
				System.out.println("Puntaje de " + nombre + " actualizado a " + nuevopuntaje);
                return true;
			}
		}
		System.out.println("No se encontró al jugador " + nombre);
        return false;
		
		
	}
	public void obtenerTop10() {
		for(Jugador j: top10) {
			System.out.println(j.getnombre()+"-"+j.getpuntaje());
			
		}
	}
}

