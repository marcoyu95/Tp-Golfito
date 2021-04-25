package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class TrampaAgua {
	double x;
	double y;
	Image img;
	double lado;
	
	public TrampaAgua(int x, int y) {
		this.x=x;
		this.y=y;
		img = Herramientas.cargarImagen("agua3.png");
		lado=240;
	}
	
	public void dibujarse(Entorno entorno){	
		entorno.dibujarImagen(img, this.x, this.y,0);
	}
	
}
