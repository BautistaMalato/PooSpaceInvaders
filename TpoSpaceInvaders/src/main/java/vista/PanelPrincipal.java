package vista;

import controlador.JuegoController;
import modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class PanelPrincipal extends JPanel {
	private int ancho;
	private int alto;
	private ImagenNave imagenNave;
	private JuegoController juegoController;
	private CreditoManager creditoManager;
	private PanelInicial panelInicial;
	
	// Naves enemigas
	private ArrayList<ImagenNaveInvasora> imagenesNaves;
	private ImagenNaveInvasora imagenNaveEnemiga;
	
	// Muro
	private ImagenMuroEnergia imagenMuro;
	private MuroEnergia muro;
	
	// Sistema de juego
	private int vidas = 5;
	private int puntaje = 0;
	private JLabel etiquetaCreditos;
	private JLabel etiquetaPuntaje;
	private JLabel etiquetaVidas;
	private JButton botonMenu;
	
	// Disparos enemigos
	private Timer timerDisparosEnemigos;
	private boolean moviendoDerecha = true;
	
	public PanelPrincipal(CreditoManager creditoManager, PanelInicial panelInicial) {
		this.creditoManager = creditoManager;
		this.panelInicial = panelInicial;
		ancho = 800;
		alto = 600;
		setLayout(null);
		setPreferredSize(new Dimension(ancho, alto));
		setBackground(Color.BLACK);
		
		imagenNave = new ImagenNave();
		imagenNaveEnemiga = new ImagenNaveInvasora();
		imagenMuro = new ImagenMuroEnergia(100, 50);
		
		// Inicializar listas
		imagenesNaves = new ArrayList<>();
		
		// Crear naves enemigas
		inicializarNavesEnemigas();
		
		// Crear muro
		add(imagenMuro);
		muro = new MuroEnergia(350, 300, 100, 50, imagenMuro);
		
		add(imagenNave);
		juegoController = new JuegoController(ancho, alto, 400, 500, imagenNave);
		juegoController.setOleada(inicializarOleada());
		juegoController.setMuro(muro);
		
		crearEtiquetas();
		interceptarTeclado();
		interceptarMouse();
		simularMovimientos();
		iniciarDisparosEnemigos();
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		SwingUtilities.invokeLater(() -> requestFocusInWindow());
	}
	
	private void inicializarNavesEnemigas() {
		// 15 naves en 3 filas de 5
		for (int fila = 0; fila < 3; fila++) {
			for (int columna = 0; columna < 5; columna++) {
				int x = columna * 80 + 100;
				int y = fila * 60 + 50;
				ImagenNaveInvasora img = new ImagenNaveInvasora();
				img.setBounds(x, y, 40, 30);
				add(img);
				imagenesNaves.add(img);
			}
		}
	}
	
	private Oleada inicializarOleada() {
		return new Oleada(ancho, alto, imagenNaveEnemiga, 2);
	}
	
	private void crearEtiquetas() {
		// Créditos
		etiquetaCreditos = new JLabel("Créditos: " + creditoManager.getCreditos());
		etiquetaCreditos.setFont(new Font("Arial", Font.BOLD, 14));
		etiquetaCreditos.setForeground(Color.WHITE);
		etiquetaCreditos.setBounds(10, 10, 200, 30);
		add(etiquetaCreditos);
		
		// Puntaje
		etiquetaPuntaje = new JLabel("Puntaje: 0");
		etiquetaPuntaje.setFont(new Font("Arial", Font.BOLD, 16));
		etiquetaPuntaje.setForeground(Color.WHITE);
		etiquetaPuntaje.setBounds(600, 10, 200, 30);
		add(etiquetaPuntaje);
		
		// Vidas
		etiquetaVidas = new JLabel("Vidas: 5");
		etiquetaVidas.setFont(new Font("Arial", Font.BOLD, 16));
		etiquetaVidas.setForeground(Color.GREEN);
		etiquetaVidas.setBounds(600, 40, 200, 30);
		add(etiquetaVidas);
		
		// Botón Menú
		botonMenu = new JButton("Menú");
		botonMenu.setFont(new Font("Arial", Font.PLAIN, 12));
		botonMenu.setBounds(10, 50, 100, 30);
		botonMenu.addActionListener(e -> panelInicial.regresar());
		add(botonMenu);
	}
	
	private void interceptarTeclado() {
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evento) {
				if (evento.getKeyCode() == KeyEvent.VK_RIGHT) {
					juegoController.moverNaveJugadorDerecha();
				} else if (evento.getKeyCode() == KeyEvent.VK_SPACE) {
					ImagenRayo imagenRayo = new ImagenRayo();
					add(imagenRayo);
					juegoController.disparar(imagenRayo);
				}
			}
		});
	}
	
	private void interceptarMouse() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				juegoController.moverNaveJugador(e.getX());
			}
		});
	}
	
	private void simularMovimientos() {
		Timer gameLoop = new Timer(20, e -> {
			juegoController.actualizarPosiciones();
			moverNavesEnemigas();
			actualizarProyectilesEnemigos();
			verificarColisiones();
			verificarDisparosEnemigos();
			
			// Verificar vidas
			if (vidas <= 0) {
				mostrarGameOver();
			}
			
			// Verificar nivel completado
			if (imagenesNaves.isEmpty()) {
				completarNivel();
			}
		});
		gameLoop.start();
	}
	
	private void actualizarProyectilesEnemigos() {
		// Los proyectiles enemigos ya se actualizan en espacio.acualizarPosiciones()
		// Solo necesitamos visualizarlos
		try {
			for (Proyectil proy : juegoController.getEspacio().getProyectilesEnemigos()) {
				if (proy == null) continue;
				ImagenProyectilEnemigo img = (ImagenProyectilEnemigo) proy.getObservador();
				if (img == null) continue;
				img.setLocation(proy.getX(), proy.getY());
			}
		} catch (Exception e) {
			// Ignorar errores
		}
	}
	
	private void iniciarDisparosEnemigos() {
		timerDisparosEnemigos = new Timer(3000, e -> {
			if (!imagenesNaves.isEmpty()) {
				Random rand = new Random();
				ImagenNaveInvasora naveAleatoria = imagenesNaves.get(rand.nextInt(imagenesNaves.size()));
				dispararProyectilEnemigo(naveAleatoria);
			}
		});
		timerDisparosEnemigos.start();
	}
	
	private void dispararProyectilEnemigo(ImagenNaveInvasora nave) {
		if (nave == null) return;
		if (!imagenesNaves.contains(nave)) return;
		
		try {
			ImagenProyectilEnemigo imgProyectil = new ImagenProyectilEnemigo();
			imgProyectil.setLocation(nave.getX(), nave.getY());
			add(imgProyectil);
			
			Proyectil proyectil = new Proyectil(nave.getX(), nave.getY(), imgProyectil, ancho);
			juegoController.getEspacio().agregarProyectilEnemigo(proyectil);
		} catch (Exception e) {
			// Ignorar errores
		}
	}
	
	private void moverNavesEnemigas() {
		if (imagenesNaves.isEmpty()) return;
		
		boolean debeCambiarDireccion = false;
		
		// Verificar si alguna nave llegó al extremo
		for (ImagenNaveInvasora img : imagenesNaves) {
			if (img == null) continue;
			try {
				if (moviendoDerecha && img.getX() >= ancho - 60) {
					debeCambiarDireccion = true;
					break;
				} else if (!moviendoDerecha && img.getX() <= 0) {
					debeCambiarDireccion = true;
					break;
				}
			} catch (Exception e) {
				continue;
			}
		}
		
		// Si debe cambiar dirección, descender todas
		if (debeCambiarDireccion) {
			moviendoDerecha = !moviendoDerecha;
			for (ImagenNaveInvasora img : imagenesNaves) {
				if (img == null) continue;
				try {
					img.setLocation(img.getX(), img.getY() + 40);
				} catch (Exception e) {
					continue;
				}
			}
		} else {
			// Mover todas en la dirección actual
			int desplazamiento = moviendoDerecha ? 2 : -2;
			for (ImagenNaveInvasora img : imagenesNaves) {
				if (img == null) continue;
				try {
					img.setLocation(img.getX() + desplazamiento, img.getY());
				} catch (Exception e) {
					continue;
				}
			}
		}
	}
	
	private void verificarColisiones() {
		// Colisiones: Rayos del jugador con naves enemigas
		ArrayList<ObjetoJuegoActualizable> proyectiles = new ArrayList<>(juegoController.getEspacio().getProyectilesJugador());
		
		for (ObjetoJuegoActualizable rayo : proyectiles) {
			if (rayo == null) continue;
			
			ImagenRayo imgRayo = (ImagenRayo) rayo.getObservador();
			if (imgRayo == null) continue;
			
			ArrayList<ImagenNaveInvasora> copiaNaves = new ArrayList<>(imagenesNaves);
			for (ImagenNaveInvasora imgNave : copiaNaves) {
				if (imgNave == null) continue;
				
				// Verificar colisión por dimensiones
				if (hayColision(imgRayo, imgNave)) {
					// Destruir nave y rayo
					try {
						remove(imgNave);
						imagenesNaves.remove(imgNave);
						
						remove(imgRayo);
						juegoController.getEspacio().removerProyectilJugador(rayo);
						
						// Agregar puntos
						puntaje += 10;
						etiquetaPuntaje.setText("Puntaje: " + puntaje);
						
						// Verificar vida extra cada 500 puntos
						if (puntaje >= 500 && (puntaje - 10) < 500) {
							vidas++;
							etiquetaVidas.setText("Vidas: " + vidas);
						}
						
						break; // Solo un rayo puede destruir una nave
					} catch (Exception e) {
						// Ignorar errores al eliminar
					}
				}
			}
		}
	}
	
	private void verificarDisparosEnemigos() {
		// Colisiones: Proyectiles enemigos con jugador
		ArrayList<Proyectil> proyectilesEnemigos = new ArrayList<>(juegoController.getEspacio().getProyectilesEnemigos());
		
		for (Proyectil proy : proyectilesEnemigos) {
			if (proy == null) continue;
			
			ImagenProyectilEnemigo imgProy = (ImagenProyectilEnemigo) proy.getObservador();
			if (imgProy == null) continue;
			
			// Verificar colisión con jugador
			if (hayColision(imgProy, imagenNave)) {
				// Quitar vida
				vidas--;
				etiquetaVidas.setText("Vidas: " + vidas);
				
				// Eliminar proyectil
				try {
					remove(imgProy);
					juegoController.getEspacio().removerProyectilEnemigo(proy);
				} catch (Exception e) {
					// Ignorar errores
				}
				
				break;
			}
			
			// Verificar colisión con muro
			if (muro != null && imagenMuro != null && hayColision(imgProy, imagenMuro)) {
				muro.recibirDanioEnemigo();
				
				// Eliminar proyectil
				try {
					remove(imgProy);
					juegoController.getEspacio().removerProyectilEnemigo(proy);
					
					// Si el muro está destruido
					if (muro.estaDestruido()) {
						remove(imagenMuro);
						imagenMuro = null;
						muro = null;
					}
				} catch (Exception e) {
					// Ignorar errores
				}
			}
		}
		
		// Colisiones: Rayos del jugador con muro
		ArrayList<ObjetoJuegoActualizable> rayosJugador = new ArrayList<>(juegoController.getEspacio().getProyectilesJugador());
		for (ObjetoJuegoActualizable rayo : rayosJugador) {
			if (rayo == null) continue;
			
			ImagenRayo imgRayo = (ImagenRayo) rayo.getObservador();
			if (imgRayo == null) continue;
			
			if (muro != null && imagenMuro != null && hayColision(imgRayo, imagenMuro)) {
				try {
					muro.recibirDanioJugador();
					
					// Eliminar rayo
					remove(imgRayo);
					juegoController.getEspacio().removerProyectilJugador(rayo);
					
					// Si el muro está destruido
					if (muro.estaDestruido()) {
						remove(imagenMuro);
						imagenMuro = null;
						muro = null;
					}
				} catch (Exception e) {
					// Ignorar errores
				}
			}
		}
	}
	
	// Método auxiliar para detectar colisión por dimensiones
	private boolean hayColision(JLabel obj1, JLabel obj2) {
		int x1 = obj1.getX();
		int y1 = obj1.getY();
		int w1 = obj1.getWidth();
		int h1 = obj1.getHeight();
		
		int x2 = obj2.getX();
		int y2 = obj2.getY();
		int w2 = obj2.getWidth();
		int h2 = obj2.getHeight();
		
		return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
	}
	
	private void mostrarGameOver() {
		JOptionPane.showMessageDialog(this, 
			"Game Over!\nPuntaje final: " + puntaje, 
			"Game Over", 
			JOptionPane.INFORMATION_MESSAGE);
		panelInicial.regresar();
	}
	
	private void completarNivel() {
		puntaje += 200;
		JOptionPane.showMessageDialog(this, 
			"¡Nivel completado!\nPuntaje: " + puntaje, 
			"Nivel Completado", 
			JOptionPane.INFORMATION_MESSAGE);
		panelInicial.regresar();
	}
}
