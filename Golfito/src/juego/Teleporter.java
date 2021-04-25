package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Teleporter {
	double x;
	double y;
	Image img;
	double radio=14;
	
	public Teleporter(double x,double y,int num) {
		this.x=x;
		this.y=y;
		if(num==1)
			img = Herramientas.cargarImagen("teleporter.png");
		else
			img = Herramientas.cargarImagen("teleporter2.png");
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, this.x, this.y, 0);
	}
	
}
