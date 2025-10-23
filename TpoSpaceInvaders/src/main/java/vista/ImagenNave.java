package vista;

import javax.swing.*;
import java.awt.*;

public class ImagenNave extends ImagenObjetoJuego {
	public ImagenNave() {
		super(50, 50);
		Image imagen = new ImageIcon("nave.png").getImage();
        Image imagenAEscala = imagen.getScaledInstance(getAncho(), getAlto(), Image.SCALE_SMOOTH);
        ImageIcon icono = new ImageIcon(imagenAEscala);
        setIcon(icono);
	}	
}
