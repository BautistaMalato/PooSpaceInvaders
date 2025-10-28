package vista;

import javax.swing.*;
import java.awt.*;

public class ImagenNave extends ImagenObjetoJuego {
	public ImagenNave() {
		super(50, 50);
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/nave.png"));
		if (iconoOriginal.getIconWidth() > 0) {
			Image imagen = iconoOriginal.getImage();
			Image imagenAEscala = imagen.getScaledInstance(getAncho(), getAlto(), Image.SCALE_SMOOTH);
			ImageIcon icono = new ImageIcon(imagenAEscala);
			setIcon(icono);
		}
	}	
}
