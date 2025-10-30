package modelo;

public class Juego {
    private int puntajeTotal;
    private int creditos;
    private int nivelActual;

    public Juego() {
        this.puntajeTotal = 0;
        this.creditos = 3;
        this.nivelActual = 1;
    }

    public void iniciarJuego() {
        this.puntajeTotal = 0;
        this.creditos = 3;
        this.nivelActual = 1;
        System.out.println("El juego está iniciando");
    }

    public void cargarCreditos(int c) {
        if (c > 0) {
            this.creditos += c;
            System.out.println("Créditos cargados: " + c);
        } else {
            System.out.println("La cantidad de créditos debe ser positiva.");
        }
    }

    public void actualizarPuntaje(int p) {
        if (p > 0) {
            this.puntajeTotal += p;
            System.out.println("Puntaje actualizado: +" + p);
        }
    }

    public void avanzarNivel() {
        this.nivelActual++;
        System.out.println("Has avanzado al nivel " + nivelActual);
    }

    public void finalizarPartida() {
        System.out.println("Partida finalizada. Puntaje total: " + puntajeTotal);
    }

    // Getters
    public int getPuntaje() {
        return puntajeTotal;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getNivelActual() {
        return nivelActual;
    }
}
