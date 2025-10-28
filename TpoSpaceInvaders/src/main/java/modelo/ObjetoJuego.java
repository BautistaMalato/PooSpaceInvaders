package modelo;

public abstract class ObjetoJuego {
	private int x;
	private int y;
	private int velocidad;
	private Observador observador;
	private int xMax;
	
	public ObjetoJuego(int x, int y, int velocidad, Observador observador, int anchoEspacio) {
		super();
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.observador = observador;
		observador.mover(x, y);
		this.xMax = anchoEspacio - observador.getAncho();
	}

	public void moverDerecha() {
		mover(x + velocidad, y);
	}
	
	public void moverArriba() {
		mover(x, y - velocidad);
	}

	public void mover(int x) {
		mover(x, y);
	}
	
	public void mover(int x, int y) {
		this.x = x;
		this.y = y;
		observador.mover(x, y);
	}
	
	public int getPosicionMediaX() {
		return x + observador.getAncho()/2;
	}

	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	public int getAnchoEspacio() {
		return xMax + observador.getAncho();
	}

	public Observador getObservador() {
		return observador;
	}
}
