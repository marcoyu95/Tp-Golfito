package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class TrampaArena {
	double x;
	double y;
	Image img;
	double radio;
	
	public TrampaArena(int x, int y) {
		this.x=x;
		this.y=y;
		this.img = Herramientas.cargarImagen("arena11.png");
		this.radio=92.5;
	}
	
	public void dibujarse(Entorno entorno) {	
		entorno.dibujarImagen(img, this.x, this.y,0);
	}
	
}
