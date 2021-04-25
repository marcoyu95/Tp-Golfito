package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Pelota {
	
	double x;
	double y;
	double angulo;
	Image img;
	double velocidad;
	double sentidoX=1;
	double sentidoY=1;
	double radio=9.75;
	
		public Pelota(int x, int y) {
			this.x=x;
			this.y=y;
			img = Herramientas.cargarImagen("golF.png");
		}
		
		public boolean seMueve() {
			return velocidad>0;
		}
		
		public void dibujarse(Entorno entorno){
			entorno.dibujarImagen(img, this.x, this.y, 0, 0.5);
		}
		
		public void moverAdelante() {//es solo opcional
			this.x += Math.cos(this.angulo)*3;
			this.y += Math.sin(this.angulo)*3;
		}
		
		public void moverse() {
			x+= Math.cos(angulo)*velocidad*sentidoX;
			y+= Math.sin(angulo)*velocidad*sentidoY;
			velocidad-=0.07;
		
			if(x>=793 || x<=17) {
				sentidoX=-sentidoX;
				Herramientas.play("Bounce.wav");
			}
			if(y>=593 || y<=17) {
				sentidoY=-sentidoY;
				Herramientas.play("Bounce.wav");
			}
		}

		public void detener() {
			velocidad=0;
		}
		
}
