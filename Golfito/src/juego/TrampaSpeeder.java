package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class TrampaSpeeder {
	
	double x;
	double y;
	Image img;
	double alto;
	double ancho;
	
	public TrampaSpeeder(int x, int y) {
		this.x=x;
		this.y=y;
		img = Herramientas.cargarImagen("speeder.png");
		this.alto=80;
		this.ancho=150;
	}
	
	public void dibujarse(Entorno entorno) {	
		entorno.dibujarImagen(img, this.x, this.y,0);
	}
	
	
}
