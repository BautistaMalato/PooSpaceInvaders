package modelo;

public class NaveInvasora extends ObjetoJuego {
	private int velocidadHorizontal;
	private boolean moviendoseDerecha;
	
	public NaveInvasora(int x, int y, int velocidad, Observador observador, int anchoEspacio, int velocidadHorizontal) {
		super(x, y, velocidad, observador, anchoEspacio);
		this.velocidadHorizontal = velocidadHorizontal;
		this.moviendoseDerecha = true;
	}
	
	public void moverHorizontal() {
		if (moviendoseDerecha) {
			moverDerecha();
			if (getX() >= getAnchoEspacio() - 100) {
				moviendoseDerecha = false;
			}
		} else {
			moverIzquierda();
			if (getX() <= 0) {
				moviendoseDerecha = true;
			}
		}
	}
	
	public void moverIzquierda() {
		mover(getX() - velocidadHorizontal, getY());
	}
	
	public void descender() {
		mover(getX(), getY() + getObservador().getAlto() + 5);
	}
	
	public void setMoviendoseDerecha(boolean moviendoseDerecha) {
		this.moviendoseDerecha = moviendoseDerecha;
	}
	
	public boolean isMoviendoseDerecha() {
		return moviendoseDerecha;
	}
}
