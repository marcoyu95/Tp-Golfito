package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Hoyo {
	double x;
	double y;
	Image img;
	double radio=12.5;
	
	public Hoyo(double x,double y) {
		this.x=x;
		this.y=y;
		img = Herramientas.cargarImagen("hoyo.png");
	}
	
	public void dibujarse(Entorno entorno) {
			entorno.dibujarImagen(img, this.x, this.y, 0);
	}

}
