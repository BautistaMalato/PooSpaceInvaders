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
     // Getters y setters
    
    public string getnombre() {
    	return nombre;
    }
    public int getpuntaje() {
    	return puntaje;
    }
    
    public void setpuntaje(int puntaje) {
    	this.puntaje = puntaje;
    }
    public int getvida() {
    	return vidas;
    }
    
     
    
    public void setvida(int vidas) {
    	this.vidas=vidas;
    }
     

    
}


