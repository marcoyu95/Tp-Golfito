package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Barra {
	double x;
	double y;
	double angulo;
	Image  barr1;
	
	public Barra() {
		this.x=30;
		this.y=202;
		barr1 = Herramientas.cargarImagen("barra002.jpg");
	}
	
	public void dibujarse(Entorno entorno) {		
		entorno.dibujarImagen(barr1, this.x, this.y, this.angulo, 0.5);
	}
	
}
