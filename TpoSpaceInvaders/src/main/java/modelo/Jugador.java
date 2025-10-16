package modelo;

public class Jugador {
    private String nombre;
    private int puntaje;
    private int vidas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
        this.vidas = 3;
    }

    public void agregarPuntaje(int puntos) { }
    public void perderVida() { }

    // Getters y setters
}
