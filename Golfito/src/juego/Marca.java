package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Marca {
	double x;
	double y;
	double tope;
	double angulo;
	Image  marca;
	double movermarca;
	
	public Marca() {
		this.x=30;
		this.y=315;
		this.tope=302.5;
		marca = Herramientas.cargarImagen("marca.jpg");
		movermarca=0;
	}
	
	public void dibujarse(Entorno entorno){	
		entorno.dibujarImagen(marca, this.x, this.y, this.angulo, 0.5);
	}
	
	public int darVelocidad() {
		if(y>=102.5 && y<127.5) {
			return 9;
		}
		if(y>=127.5 && y<152.5) {
			return 8;
		}
		if(y>=152.5 && y<177.5) {
			return 7;
		}
		if(y>=177.5 && y<202.5) {
			return 6;
		}
		if(y>=202.5 && y<227.5) {
			return 5;
		}
		if(y>=227.5 && y<252.5) {
			return 4;
		}
		if(y>=252.5 && y<277.5) {
			return 3;
		}
		if(y>=277.5 && y<302.5) {
			return 2;
		}
		if(y>=302.5 && y<327.5) {
			return 1;
		}
	
		return 10;
	}

	public void iniciar() {
		if(tope>=302.5)
			movermarca-=3;
		if(tope<=77.5)
			movermarca+=3;
		y+=movermarca;
		tope+=movermarca;
	}
}
