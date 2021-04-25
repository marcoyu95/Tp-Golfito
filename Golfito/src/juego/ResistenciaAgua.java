package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class ResistenciaAgua {
	double x;
	double y;
	Image img;
	double lado;
	
	public ResistenciaAgua(double x,double y) {
		this.x=x;
		this.y=y;
		img = Herramientas.cargarImagen("restAgua.png");
		this.lado=30;
	}
	
	public void dibujarse(Entorno entorno){	
		entorno.dibujarImagen(img, this.x, this.y,0);
	}
	
}
