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

    public void agregarPuntaje(int puntos) { 
    	this.puntaje+=puntos;
    }
    public void perderVida() {
    	if(vidas>0) {
    		vidas--;
    	}
    }
    
    public String getNombre() {
    	return nombre;
    }
    public int getPuntaje() {
    	return puntaje;
    }
    
    public void setPuntaje(int puntaje) {
    	this.puntaje = puntaje;
    }
    public int getVida() {
    	return vidas;
    }
    
     
    
    public void setVida(int vidas) {
    	this.vidas=vidas;
    }
     

    // Getters y setters
}
