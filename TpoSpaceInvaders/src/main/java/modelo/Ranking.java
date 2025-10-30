package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranking {
    private ArrayList<Jugador> top10 = new ArrayList<>();

    public void agregarJugador(String nombre, int puntaje) {
        top10.add(new Jugador(nombre, puntaje));
        ordenarRanking();  
    }

    public boolean actualizarPuntaje(String nombre, int nuevoPuntaje) {
        for (Jugador j : top10) {
            if (j.getnombre().equalsIgnoreCase(nombre)) {
                j.setpuntaje(nuevoPuntaje);
                ordenarRanking();  
                System.out.println("Puntaje de " + nombre + " actualizado a " + nuevoPuntaje);
                return true;
            }
        }
        System.out.println("No se encontró al jugador " + nombre);
        return false;
    }

    public void obtenerTop10() {
        for (Jugador j : top10) {
            System.out.println(j.getnombre() + " - " + j.getpuntaje());
        }
    }

    private void ordenarRanking() {
        Collections.sort(top10, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador j1, Jugador j2) {
                
                return Integer.compare(j2.getpuntaje(), j1.getpuntaje());
            }
        });
    }
}










