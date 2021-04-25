package juego;


import java.awt.Color;
import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;
	int 			cantgolpes;//sumador para contabilizar la cantidad de golpes
	ResistenciaAgua ayuda1;
	int 			resistAgua;//bandera que se usa para darle resistencia al agua a la pelota
	Teleporter		tpEntrada;
	Teleporter 		tpSalida;
	Bonificacion 	ayuda2;
	Image			inicio;
	Image			gameOver;
	TrampaAgua 		tAgua;
	TrampaArena 	tArena;
	TrampaSpeeder 	tSpeeder;
	Pelota 			pelota;
	Flecha  		flecha;
	Barra			barra;
	Image			fondo;
	Marca			marca;
	Hoyo			hoyo;
	int				flagpot;//bandera que se usa para dar velocidad a la pelota luego de cargar la barra de potencia.
	int 			cargargolpe;//bandera que se usa para saber que se presiono la barra de potencia.
	int				estoydentrodearena;//bandera que se usa para indicar si la pelota ingreso en la trampa de arena, para que luego la pelota pueda salir con la potencia indicada.
	int				mostrarbarra;//bandera para mostrar la barra de potencia, ya que el enunciado pedia que la barra se muestre luego de dar el primer golpe.
	boolean			juegoterminado;//bandera que se usa para saber si el juego termino.
	int 			toqueSpeeder;//bandera que se usa para aumentar la velocidad de la pelota solo cuando la pelota toca el speeder y no mientras esta dentro del speeder.
	int 			iniciarse;//bandera que se usa para mostrar un cartel de bienvenida.

	Juego() {
		
		inicio = Herramientas.cargarImagen("inicio.gif");
		gameOver= Herramientas.cargarImagen("gameOver.gif");
		this.entorno = new Entorno(this, "Golfito", 800, 600);

		resistAgua=0;
		iniciarse=0;
		cargargolpe=0;
		flagpot=0;
		mostrarbarra=0;
		juegoterminado=false;
		toqueSpeeder=0;
		estoydentrodearena=0;
		cantgolpes=0;
		tpEntrada= new Teleporter(200,300,0);
		tpSalida = new Teleporter(200,300,1);
		tArena=new TrampaArena(50,75);
		tAgua=new TrampaAgua(ThreadLocalRandom.current().nextInt(170,670), ThreadLocalRandom.current().nextInt(130,460));
		tSpeeder=new TrampaSpeeder(ThreadLocalRandom.current().nextInt(200,675), ThreadLocalRandom.current().nextInt(130,460));
		hoyo=new Hoyo(ThreadLocalRandom.current().nextInt(400,770),ThreadLocalRandom.current().nextInt(150,475));
		pelota=new Pelota(50,75);
		flecha=new Flecha(50,75);
		barra=new Barra();
		fondo=Herramientas.cargarImagen("fondo.jpg");
		marca= new Marca();
		ayuda1=new ResistenciaAgua(300, 500);
		ayuda2=new Bonificacion(300, 500);
		
		//verificacion de colisiones antes de dibujarlas
		while(Colisiones.TocaBarra(pelota.x, pelota.y, pelota.radio)) {//si la pelota toca la barra no la dibuja
			pelota.x=ThreadLocalRandom.current().nextInt(30,100);
			pelota.y=ThreadLocalRandom.current().nextInt(30,550); 
		}
		while(Colisiones.TocaBarra(tArena.x, tArena.y, tArena.radio) || Colisiones.entreDosCirculos(tArena.x, tArena.y, tArena.radio, pelota.x, pelota.y, pelota.radio)) {//la trampa solo se dibuja dentro de la ventana(800x600)
			tArena.x=ThreadLocalRandom.current().nextInt(125,675);
			tArena.y=ThreadLocalRandom.current().nextInt(125,475);
		}
		while(Colisiones.entreCirculoyCuadrado(tArena.x, tArena.y, tArena.radio,tAgua.x,tAgua.y,tAgua.lado) ||  Colisiones.entreCirculoyCuadrado(pelota.x, pelota.y, pelota.radio,tAgua.x,tAgua.y,tAgua.lado)) {
			tAgua.x=ThreadLocalRandom.current().nextInt(170,670);
			tAgua.y=ThreadLocalRandom.current().nextInt(130,460);
		}
		while(Colisiones.entreCirculoyCuadrado(hoyo.x, hoyo.y, hoyo.radio, tAgua.x, tAgua.y, tAgua.lado) || Colisiones.entreDosCirculos(tArena.x, tArena.y, tArena.radio, hoyo.x, hoyo.y, hoyo.radio)) {
			hoyo.x=ThreadLocalRandom.current().nextInt(400,770);
			hoyo.y=ThreadLocalRandom.current().nextInt(30,570);
		}
		while(Colisiones.entreCirculoyRectangulo(tArena.x, tArena.y, tArena.radio, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCirculoyRectangulo(hoyo.x, hoyo.y, hoyo.radio, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCuadradoyRectangulo(tAgua.x, tAgua.y, tAgua.lado, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho)) {//trampa speeder 
			tSpeeder.x=ThreadLocalRandom.current().nextInt(200,675);
			tSpeeder.y=ThreadLocalRandom.current().nextInt(150,475);
		}
		while(Colisiones.entreCuadradoyRectangulo(tAgua.x, tAgua.y, tAgua.lado, ayuda1.x, ayuda1.y, ayuda1.lado, ayuda1.lado) || Colisiones.entreCirculoyRectangulo(tArena.x, tArena.y, tArena.radio, ayuda1.x, ayuda1.y, ayuda1.lado, ayuda1.lado) || Colisiones.entreCuadradoyRectangulo(ayuda1.x, ayuda1.y, ayuda1.lado, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCirculoyRectangulo(hoyo.x, hoyo.y, hoyo.radio, ayuda1.x, ayuda1.y, ayuda1.lado, ayuda1.lado)) {
			ayuda1.x=ThreadLocalRandom.current().nextInt(200,675);
			ayuda1.y=ThreadLocalRandom.current().nextInt(150,475);
		}
		while(Colisiones.entreCuadradoyRectangulo(tAgua.x, tAgua.y, tAgua.lado, ayuda2.x, ayuda2.y, ayuda2.lado, ayuda2.lado) || Colisiones.entreCirculoyRectangulo(tArena.x, tArena.y, tArena.radio, ayuda2.x, ayuda2.y, ayuda2.lado, ayuda2.lado) || Colisiones.entreCuadradoyRectangulo(ayuda2.x, ayuda2.y, ayuda2.lado, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCuadradoyRectangulo(ayuda2.x, ayuda2.y, ayuda2.lado, ayuda1.x, ayuda1.y, ayuda1.lado, ayuda1.lado) || Colisiones.entreCirculoyRectangulo(hoyo.x, hoyo.y, hoyo.radio, ayuda2.x, ayuda2.y, ayuda2.lado, ayuda2.lado)) {
			ayuda2.x=ThreadLocalRandom.current().nextInt(200,675);
			ayuda2.y=ThreadLocalRandom.current().nextInt(150,475);
		}
		while(Colisiones.entreCirculoyCuadrado(tpEntrada.x, tpEntrada.y, tpEntrada.radio, tAgua.x, tAgua.y, tAgua.lado) || Colisiones.entreCirculoyRectangulo(tpEntrada.x, tpEntrada.y, tpEntrada.radio, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCirculoyCuadrado(tpEntrada.x, tpEntrada.y, tpEntrada.radio, ayuda1.x, ayuda1.y, ayuda1.lado) || Colisiones.entreCirculoyCuadrado(tpEntrada.x, tpEntrada.y, tpEntrada.radio, ayuda2.x, ayuda2.y, ayuda2.lado) || Colisiones.entreDosCirculos(tpEntrada.x, tpEntrada.y, tpEntrada.radio, tArena.x, tArena.y, tArena.radio)) {
			tpEntrada.x=ThreadLocalRandom.current().nextInt(150,475);
			tpEntrada.y=ThreadLocalRandom.current().nextInt(80,475);
		}
		while(Colisiones.entreCirculoyCuadrado(tpSalida.x, tpSalida.y, tpSalida.radio, tAgua.x, tAgua.y, tAgua.lado) || Colisiones.entreCirculoyRectangulo(tpSalida.x, tpSalida.y, tpSalida.radio, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCirculoyCuadrado(tpSalida.x, tpSalida.y, tpSalida.radio, ayuda1.x, ayuda1.y, ayuda1.lado) || Colisiones.entreCirculoyCuadrado(tpSalida.x, tpSalida.y, tpSalida.radio, ayuda2.x, ayuda2.y, ayuda2.lado) || Colisiones.entreDosCirculos(tpSalida.x, tpSalida.y, tpSalida.radio, tArena.x, tArena.y, tArena.radio) || Colisiones.entreDosCirculos(tpSalida.x, tpSalida.y, tpSalida.radio, tpEntrada.x, tpEntrada.y, tpEntrada.radio) || Colisiones.entreDosCirculos(tpSalida.x, tpSalida.y, tpSalida.radio, hoyo.x, hoyo.y, hoyo.radio)) {
			tpSalida.x=ThreadLocalRandom.current().nextInt(550,675);
			tpSalida.y=ThreadLocalRandom.current().nextInt(80,475);
		}
		
		this.entorno.iniciar();
	}

	public void tick() {	
		
	if(iniciarse==0) {//al inicio se muestra un cartel de bienvenida
		entorno.dibujarImagen(inicio, 400, 300, 0,1.3);
		if(entorno.sePresiono(entorno.TECLA_ENTER)) {
			iniciarse=1;
		}
	}
	else {	
		if(!juegoterminado)	{
		
		this.entorno.dibujarImagen(fondo, 402, 302,0);//fondo
		tpEntrada.dibujarse(entorno);
		tpSalida.dibujarse(entorno);
		tAgua.dibujarse(entorno);
		tArena.dibujarse(entorno);
		tSpeeder.dibujarse(entorno);
		hoyo.dibujarse(entorno);
		
		if(!pelota.seMueve() && !entorno.estaPresionada(entorno.TECLA_ESPACIO)) {//mover flecha cuando no se mueve y no esta presionado el espacio
			if (entorno.estaPresionada(entorno.TECLA_DERECHA))
				this.flecha.girar(Herramientas.radianes(3.5));//original 4.5
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
				this.flecha.girar(Herramientas.radianes(-3.5));
			flecha.dibujarse(entorno);
		}
		
		//quitar solo es auxiliar
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			pelota.moverAdelante();
		}
		
		//pelota toca ResistenciaAgua
		if(!Colisiones.entreCirculoyRectangulo(pelota.x, pelota.y, pelota.radio, ayuda1.x, ayuda1.y, ayuda1.lado, ayuda1.lado)) {//resistencia agua
			ayuda1.dibujarse(entorno);
		}
		else {
			ayuda1.x=1000;
			resistAgua=1;
		}
		
		//pelota toca Bonificacion
		if(!Colisiones.entreCirculoyRectangulo(pelota.x, pelota.y, pelota.radio, ayuda2.x, ayuda2.y, ayuda2.lado, ayuda2.lado)) {//bonificacion/penalizacion
			ayuda2.dibujarse(entorno);
			
			entorno.cambiarFont("Arial", 25, Color.white);
			entorno.escribirTexto(String.format("%.0f", ayuda2.bonus), ayuda2.x-7, ayuda2.y+8);
			
		}
		else {
			ayuda2.x=1000;
			cantgolpes+=ayuda2.bonus;
		}
		
		if(mostrarbarra==1) {//muestra barra y marca(esta se "pausara" o se movera dependiendo).
			barra.dibujarse(entorno);
			marca.dibujarse(entorno);
		}
		
		if(entorno.estaPresionada(entorno.TECLA_ESPACIO)&&!pelota.seMueve()) {//dibuja la barra mientras este presionado la tecla espacio	
			marca.dibujarse(entorno);//hace que la marca en la barra se "mueva"			
			marca.iniciar();
			mostrarbarra=1;
			
			flecha.dibujarse(entorno);
			cargargolpe=1;	
		}
		
		if(!entorno.estaPresionada(entorno.TECLA_ESPACIO)&&!pelota.seMueve()) {//dibuja flecha
			flecha.dibujarse(entorno);
		}
		
		if(cargargolpe==1 && !entorno.estaPresionada(entorno.TECLA_ESPACIO)) {//presione la barra y la solte
	
			if(flagpot==0) {
				pelota.velocidad=marca.darVelocidad();
				flagpot=1;
				Herramientas.play("ShootSound.wav");
				cantgolpes++;
			}
			
			if(pelota.seMueve()) {
				pelota.moverse();
			}
			else {
				flagpot=0;//reinicio flags
				pelota.detener();//la velocidad queda negativa
				cargargolpe=0;
				pelota.sentidoX=1;
				pelota.sentidoY=1;
			}
		}
		
		//pelota toca trampa de arena
		if(Colisiones.entreDosCirculos(pelota.x, pelota.y, pelota.radio, tArena.x, tArena.y, tArena.radio)&&estoydentrodearena==0) {//la pelota toco la trampa de arena, dismnuir la velocidad de la pelota de golf.NO modificar +2 -7
			pelota.velocidad-=5;
			estoydentrodearena=1;
		}
			
		if(estoydentrodearena==1&&!Colisiones.entreDosCirculos(pelota.x, pelota.y, pelota.radio, tArena.x, tArena.y, tArena.radio)) {
			estoydentrodearena=0;
		}		
			
		//pelota toca trampa de agua
		if(Colisiones.entreCirculoyCuadrado(pelota.x, pelota.y, pelota.radio,tAgua.x,tAgua.y,tAgua.lado)&&pelota.velocidad!=0 && resistAgua==0) {
			pelota.x-= Math.cos(pelota.angulo)*pelota.velocidad*pelota.sentidoX;
			pelota.y-= Math.sin(pelota.angulo)*pelota.velocidad*pelota.sentidoY;
			pelota.detener();
			cantgolpes++;
		}
	
		//pelota toca trampa speeder
		if(Colisiones.entreCirculoyRectangulo(pelota.x, pelota.y, pelota.radio, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) && toqueSpeeder==0) {
			pelota.velocidad+=5;
			toqueSpeeder=1;
			Herramientas.play("velocidad.wav");
		}	
		
		if(!Colisiones.entreCirculoyRectangulo(pelota.x, pelota.y, pelota.radio, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho)) {
			toqueSpeeder=0;
		}
		
		//pelota toca teleporter
		if(Colisiones.entreDosCirculos(pelota.x, pelota.y, pelota.radio/2, tpEntrada.x, tpEntrada.y, tpEntrada.radio/2)) {
			Herramientas.play("Teleport Sound.wav");
			pelota.x=tpSalida.x;
			pelota.y=tpSalida.y;
		}
		
		flecha.x=pelota.x;//asigna el centro de la pelota al centro de la flecha
		flecha.y=pelota.y;
		pelota.angulo=flecha.angulo;
			
		entorno.cambiarFont("Arial", 25, Color.white);
		entorno.escribirTexto("Golpes actuales: " + cantgolpes, 50, 25);	
	
		if(pelota.seMueve()) {//hace que se mueve la pelota si presiono espacio mientras se mueve, pues hacia que se "pausara" el juego
			if(entorno.estaPresionada(entorno.TECLA_ESPACIO) || entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				pelota.moverse();
			}
		}
		
		pelota.dibujarse(entorno);
		}
		
		//entro en al hoyo
		if(Colisiones.entreDosCirculos(pelota.x, pelota.y, pelota.radio/2, hoyo.x, hoyo.y, hoyo.radio/2)&&pelota.velocidad<=6) {
			pelota.velocidad=0;
			juegoterminado=true;
		}
		
		if(juegoterminado) {
			
			entorno.dibujarImagen(gameOver, 400, 300, 0,2);
			entorno.cambiarFont("Arial", 80, Color.white);
			entorno.escribirTexto("Golpes :" + cantgolpes, 245, 200);
			
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				
				inicio = Herramientas.cargarImagen("inicio.gif");
				gameOver= Herramientas.cargarImagen("gameOver.gif");
				
				resistAgua=0;
				cargargolpe=0;
				flagpot=0;
				mostrarbarra=0;
				juegoterminado=false;
				toqueSpeeder=0;
				estoydentrodearena=0;
				cantgolpes=0;
				
				tpEntrada= new Teleporter(200,300,0);
				tpSalida = new Teleporter(200,300,1);
				
				tArena=new TrampaArena(50,75);
				tAgua=new TrampaAgua(ThreadLocalRandom.current().nextInt(170,670), ThreadLocalRandom.current().nextInt(130,460));
				tSpeeder=new TrampaSpeeder(ThreadLocalRandom.current().nextInt(200,675), ThreadLocalRandom.current().nextInt(130,460));//hacer que sea aleatorio
				
				hoyo=new Hoyo(ThreadLocalRandom.current().nextInt(400,770),ThreadLocalRandom.current().nextInt(150,475));
				pelota=new Pelota(50,75);
				flecha=new Flecha(50,75);
				barra=new Barra();
				fondo=Herramientas.cargarImagen("fondo.jpg");
				marca= new Marca();
				ayuda1=new ResistenciaAgua(300, 500);
				ayuda2=new Bonificacion(300, 500);
				juegoterminado=false;
				
				while(Colisiones.TocaBarra(pelota.x, pelota.y, pelota.radio)) {//si la pelota toca la barra no la dibuja
					pelota.x=ThreadLocalRandom.current().nextInt(30,100);
					pelota.y=ThreadLocalRandom.current().nextInt(30,550); 
				}
				while(Colisiones.TocaBarra(tArena.x, tArena.y, tArena.radio) || Colisiones.entreDosCirculos(tArena.x, tArena.y, tArena.radio, pelota.x, pelota.y, pelota.radio)) {//la trampa solo se dibuja dentro de la ventana(800x600)
					tArena.x=ThreadLocalRandom.current().nextInt(125,675);
					tArena.y=ThreadLocalRandom.current().nextInt(125,475);
				}
				while(Colisiones.entreCirculoyCuadrado(tArena.x, tArena.y, tArena.radio,tAgua.x,tAgua.y,tAgua.lado) ||  Colisiones.entreCirculoyCuadrado(pelota.x, pelota.y, pelota.radio,tAgua.x,tAgua.y,tAgua.lado)){
					tAgua.x=ThreadLocalRandom.current().nextInt(170,670);
					tAgua.y=ThreadLocalRandom.current().nextInt(130,460);
				}
				while(Colisiones.entreCirculoyCuadrado(hoyo.x, hoyo.y, hoyo.radio, tAgua.x, tAgua.y, tAgua.lado) || Colisiones.entreDosCirculos(tArena.x, tArena.y, tArena.radio, hoyo.x, hoyo.y, hoyo.radio)) {
					hoyo.x=ThreadLocalRandom.current().nextInt(400,770);
					hoyo.y=ThreadLocalRandom.current().nextInt(30,570);
				}
				while(Colisiones.entreCirculoyRectangulo(tArena.x, tArena.y, tArena.radio, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCirculoyRectangulo(hoyo.x, hoyo.y, hoyo.radio, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCuadradoyRectangulo(tAgua.x, tAgua.y, tAgua.lado, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho)) {//trampa speeder 
					tSpeeder.x=ThreadLocalRandom.current().nextInt(200,675);
					tSpeeder.y=ThreadLocalRandom.current().nextInt(150,475);
				}
				while(Colisiones.entreCuadradoyRectangulo(tAgua.x, tAgua.y, tAgua.lado, ayuda1.x, ayuda1.y, ayuda1.lado, ayuda1.lado) || Colisiones.entreCirculoyRectangulo(tArena.x, tArena.y, tArena.radio, ayuda1.x, ayuda1.y, ayuda1.lado, ayuda1.lado) || Colisiones.entreCuadradoyRectangulo(ayuda1.x, ayuda1.y, ayuda1.lado, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCirculoyRectangulo(hoyo.x, hoyo.y, hoyo.radio, ayuda1.x, ayuda1.y, ayuda1.lado, ayuda1.lado) ) {
					ayuda1.x=ThreadLocalRandom.current().nextInt(200,675);
					ayuda1.y=ThreadLocalRandom.current().nextInt(150,475);
				}
				while(Colisiones.entreCuadradoyRectangulo(tAgua.x, tAgua.y, tAgua.lado, ayuda2.x, ayuda2.y, ayuda2.lado, ayuda2.lado) || Colisiones.entreCirculoyRectangulo(tArena.x, tArena.y, tArena.radio, ayuda2.x, ayuda2.y, ayuda2.lado, ayuda2.lado) || Colisiones.entreCuadradoyRectangulo(ayuda2.x, ayuda2.y, ayuda2.lado, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCuadradoyRectangulo(ayuda2.x, ayuda2.y, ayuda2.lado, ayuda1.x, ayuda1.y, ayuda1.lado, ayuda1.lado) || Colisiones.entreCirculoyRectangulo(hoyo.x, hoyo.y, hoyo.radio, ayuda2.x, ayuda2.y, ayuda2.lado, ayuda2.lado) ) {
					ayuda2.x=ThreadLocalRandom.current().nextInt(200,675);
					ayuda2.y=ThreadLocalRandom.current().nextInt(150,475);
				}
				while(Colisiones.entreCirculoyCuadrado(tpEntrada.x, tpEntrada.y, tpEntrada.radio, tAgua.x, tAgua.y, tAgua.lado) || Colisiones.entreCirculoyRectangulo(tpEntrada.x, tpEntrada.y, tpEntrada.radio, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCirculoyCuadrado(tpEntrada.x, tpEntrada.y, tpEntrada.radio, ayuda1.x, ayuda1.y, ayuda1.lado) || Colisiones.entreCirculoyCuadrado(tpEntrada.x, tpEntrada.y, tpEntrada.radio, ayuda2.x, ayuda2.y, ayuda2.lado) || Colisiones.entreDosCirculos(tpEntrada.x, tpEntrada.y, tpEntrada.radio, tArena.x, tArena.y, tArena.radio)) {
					tpEntrada.x=ThreadLocalRandom.current().nextInt(150,475);
					tpEntrada.y=ThreadLocalRandom.current().nextInt(80,475);
				}
				while(Colisiones.entreCirculoyCuadrado(tpSalida.x, tpSalida.y, tpSalida.radio, tAgua.x, tAgua.y, tAgua.lado) || Colisiones.entreCirculoyRectangulo(tpSalida.x, tpSalida.y, tpSalida.radio, tSpeeder.x, tSpeeder.y, tSpeeder.alto, tSpeeder.ancho) || Colisiones.entreCirculoyCuadrado(tpSalida.x, tpSalida.y, tpSalida.radio, ayuda1.x, ayuda1.y, ayuda1.lado) || Colisiones.entreCirculoyCuadrado(tpSalida.x, tpSalida.y, tpSalida.radio, ayuda2.x, ayuda2.y, ayuda2.lado) || Colisiones.entreDosCirculos(tpSalida.x, tpSalida.y, tpSalida.radio, tArena.x, tArena.y, tArena.radio) || Colisiones.entreDosCirculos(tpSalida.x, tpSalida.y, tpSalida.radio, tpEntrada.x, tpEntrada.y, tpEntrada.radio) || Colisiones.entreDosCirculos(tpSalida.x, tpSalida.y, tpSalida.radio, hoyo.x, hoyo.y, hoyo.radio)) {
					tpSalida.x=ThreadLocalRandom.current().nextInt(550,675);
					tpSalida.y=ThreadLocalRandom.current().nextInt(80,475);
				}
				
				}
			
			}
			
		}
	
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
