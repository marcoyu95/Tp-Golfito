package juego;

public class Colisiones {
	
	public static boolean entreDosCirculos(double c1x,double c1y,double radioc1,double c2x,double c2y,double radioc2) {
		
		if((Math.sqrt((c1x-c2x)*(c1x-c2x)+(c1y-c2y)*(c1y-c2y))-radioc1-radioc2)<0) {// ó <=0
			return true;
		}
		return false;
	}
	
	public static boolean entreCirculoyCuadrado(double p1x,double p1y,double radio,double trx,double tray,double tlado) {
		
		if(p1x+radio>=trx-tlado/2 && p1x-radio<=trx+tlado/2 && p1y+radio>=tray-tlado/2 && p1y-radio<=tray+tlado/2) {
//			System.out.println("se tocan");
			return true;
		}
		return false;					
	}
	
	public static boolean entreCirculoyRectangulo(double p1x,double p1y,double radio,double trx,double tray,double talto,double tancho) {

		if(p1x+radio>=trx-tancho/2 && p1x-radio<=trx+tancho/2 && p1y+radio>=tray-talto/2 && p1y-radio<=tray+talto/2) {
			return true;
		}
		return false;					
	}
	
	
	public static boolean TocaBarra(double x,double y,double radio) {
		
		if(x+radio>=12.5 && x-radio<=47.5 && y+radio>=77 && y-radio<=327) {
			return true;
		}
		return false;
	}
	
	
	public static boolean entreCuadradoyRectangulo(double trax,double tray,double tlado,double tra1x,double tra1y,double tra1alto,double tra1ancho) {
		
		double rx=trax-tlado/2;//trampa de agua
		double rx1=trax+tlado/2;
		double ry=tray-tlado/2;
		double ry1=tray+tlado/2;
		
		double p1x=tra1x-tra1ancho/2;
		double p1y=tra1y-tra1alto/2;
		double p2x=tra1x+tra1ancho/2;
		double p2y=tra1y-tra1alto/2;
		double p3x=tra1x-tra1ancho/2;
		double p3y=tra1y+tra1alto/2;
		double p4x=tra1x+tra1ancho/2;
		double p4y=tra1y+tra1alto/2;
		
		
		if(estaEnRectangulo(p1x,p1y,rx,rx1,ry,ry1) || estaEnRectangulo(p2x,p2y,rx,rx1,ry,ry1) || estaEnRectangulo(p3x,p3y,rx,rx1,ry,ry1) || estaEnRectangulo(p4x,p4y,rx,rx1,ry,ry1))
			return true;
		return false;
	}
	
	public static boolean estaEnRectangulo(double px,double py,double rx,double rx1,double ry,double ry1) {//toma los vertices del rectangulo(trampa speeder)
		
		if(px>=rx && px<=rx1 && py>=ry && py<=ry1)
			return true;
		return false;
	}
	
}
