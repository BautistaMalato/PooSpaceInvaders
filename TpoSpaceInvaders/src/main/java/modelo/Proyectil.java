package modelo;

public class Proyectil extends ObjetoJuegoActualizable {
	
	public Proyectil(int x, int y, Observador observador, int anchoEspacio) {
		super(x, y, 5, observador, anchoEspacio); // velocidad 5 (mitad de los rayos)
	}

	@Override
	public void actualizarPosicion() {
		mover(getX(), getY() + getVelocidad());
	}
}
