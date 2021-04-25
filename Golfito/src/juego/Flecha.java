package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Flecha {
	double x;
	double y;
	double angulo;
	Image img;
	
	public Flecha(int x, int y) {
		this.x=x;
		this.y=y;
		img = Herramientas.cargarImagen("flecha1113.png");
	}
	
	public void girar(double modificador) {
		this.angulo = this.angulo + modificador;
	}
	
	public void dibujarse(Entorno entorno){
		entorno.dibujarImagen(img, this.x, this.y, this.angulo, 0.5);
	}
}
