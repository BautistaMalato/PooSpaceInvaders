package modelo;

public class MuroEnergia {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private double vida; // 0 a 100
	private Observador observador;
	
	public MuroEnergia(int x, int y, int ancho, int alto, Observador observador) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.observador = observador;
		this.vida = 100;
		actualizarVisualizacion();
	}
	
	public void recibirDanioEnemigo() {
		// Enemigo: debilita 5%
		vida = Math.max(0, vida - 5);
		actualizarVisualizacion();
	}
	
	public void recibirDanioJugador() {
		// Jugador: debilita 10%
		vida = Math.max(0, vida - 10);
		actualizarVisualizacion();
	}
	
	private void actualizarVisualizacion() {
		int alturaActual = (int)(alto * (vida / 100.0));
		observador.mover(x, y + (alto - alturaActual));
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getAncho() { return ancho; }
	public int getAlto() { return alto; }
	public double getVida() { return vida; }
	public boolean estaDestruido() { return vida <= 0; }
}
