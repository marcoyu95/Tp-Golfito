package juego;

import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

import entorno.Entorno;
import entorno.Herramientas;

public class Bonificacion {
	double x;
	double y;
	Image img;
	double lado;
	double bonus;
	
	public Bonificacion(double x,double y) {
		this.x=x;
		this.y=y;
		img = Herramientas.cargarImagen("bonus.png");
		this.lado=30;
		
		if(ThreadLocalRandom.current().nextInt(1,3)==1) {//suma golpes
			bonus=ThreadLocalRandom.current().nextInt(2,5);
		}
		else
			bonus=ThreadLocalRandom.current().nextInt(2,4)*-1;//resta golpes
	}
	
	public void dibujarse(Entorno entorno){	
		entorno.dibujarImagen(img, this.x, this.y,0);
	}
	
}
