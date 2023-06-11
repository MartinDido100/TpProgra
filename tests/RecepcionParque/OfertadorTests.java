package RecepcionParque;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import RecepcionParque.Enums.TipoAtraccion;
import RecepcionParque.Promociones.PromocionPorcentual;

public class OfertadorTests {
	ArrayList<Usuario> listaUsuarios;
	ArrayList<Atraccion> atracciones;
	ArrayList<Paquete> paquetes;
	
	@Before
	public void inicializar() {
		this.listaUsuarios = new ArrayList<>();
		this.atracciones = new ArrayList<>();
		this.paquetes = new ArrayList<>();
		
		this.listaUsuarios.add(new Usuario("Nestor",1000,1000,TipoAtraccion.AVENTURA));
		this.listaUsuarios.add(new Usuario("Juan",100,100,TipoAtraccion.SIMULACION));
		this.listaUsuarios.add(new Usuario("Jose",100,1,TipoAtraccion.SIMULACION));
		this.listaUsuarios.add(new Usuario("Carlos",0.2,100,TipoAtraccion.SIMULACION));
		
		this.atracciones.add(new Atraccion("Star Wars",2.2,1,20,TipoAtraccion.SIMULACION));
		this.atracciones.add(new Atraccion("SpiderMan",1.5,20,20,TipoAtraccion.SIMULACION));
		this.atracciones.add(new Atraccion("Lilo y Stitch",6,11,22,TipoAtraccion.SIMULACION));		
		this.atracciones.add(new Atraccion("Jurasik World",1.6,15,26,TipoAtraccion.AVENTURA));
		this.atracciones.add(new Atraccion("Harry Potter",2.7,7,23,TipoAtraccion.AVENTURA));
		this.atracciones.add(new Atraccion("Terra Mitica",1.5,2,21,TipoAtraccion.AVENTURA));
		
		ArrayList<Atraccion> atrPaquete = new ArrayList<>();
		
		atrPaquete.add(this.atracciones.get(0));
		atrPaquete.add(this.atracciones.get(1));
		atrPaquete.add(this.atracciones.get(2));
		
		//Nombre Atracciones Promocion
		paquetes.add(new Paquete("Disney Cinema",atrPaquete,new PromocionPorcentual(0.4, 67)));
		
		atrPaquete.clear();
		atrPaquete.add(this.atracciones.get(3));
		atrPaquete.add(this.atracciones.get(4));
		atrPaquete.add(this.atracciones.get(5));
		
		paquetes.add(new Paquete("Aventura Extrema",atrPaquete,new PromocionPorcentual(0.2, 70)));
		
	}
	
	//Chequear bien el orden de oferta
	@Test
	public void ordenDeOfertaPaquetesPorPreferencia() {
		Ofertador ofertador = new Ofertador();
		
		ofertador.setUsuario(this.listaUsuarios.get(0));
		
		ofertador.procesarPaquetes(this.paquetes);

		ArrayList<Paquete> esperado = new ArrayList<>();
		esperado.add(this.paquetes.get(1));
		esperado.add(this.paquetes.get(0));
		
		assertEquals(esperado, ofertador.getPaquetesAOfertar());
	}

	
	@Test
	public void compraDeUnaAtraccionNoSeaNuevamenteOfertada() {
		Ofertador ofertador = new Ofertador();
		
		ofertador.setUsuario(this.listaUsuarios.get(1));
		
		ofertador.procesarAtracciones(this.atracciones);
		
		//El order correcto seria
		ArrayList<Atraccion> esperado = new ArrayList<>();
		esperado.add(this.atracciones.get(2));
		esperado.add(this.atracciones.get(0));
		esperado.add(this.atracciones.get(1));
		esperado.add(this.atracciones.get(3));
		esperado.add(this.atracciones.get(4));
		esperado.add(this.atracciones.get(5));
		
		assertEquals(esperado, ofertador.getAtraccionesAOfertar());
	}
	
	//Que si compro una atr con 1 cupo que no la oferte para el 2do usuario
	@Test
	public void atraccionSinCupoEnPaquete() {
		Ofertador ofertador = new Ofertador();
		
		ofertador.setUsuario(this.listaUsuarios.get(0));
		
		ofertador.procesarPaquetes(this.paquetes);
		ofertador.procesarAtracciones(this.atracciones);

		ofertador.hacerCompra(this.paquetes.get(0));
		
		ofertador.reiniciarOferta();
		ofertador.setUsuario(this.listaUsuarios.get(1));
		ofertador.procesarAtracciones(atracciones);
		ofertador.procesarPaquetes(paquetes);
		
		ArrayList<Paquete> esperado = new ArrayList<>();
		esperado.add(this.paquetes.get(1));
		
		assertEquals(esperado, ofertador.getPaquetesAOfertar());
	}
	
	//Que no oferte una atraccion comprada en un paquete
	@Test
	public void atraccionYaComprada() {
		Ofertador ofertador = new Ofertador();
		
		ofertador.setUsuario(this.listaUsuarios.get(0));
		
		ofertador.procesarPaquetes(this.paquetes);

		ofertador.hacerCompra(this.paquetes.get(0));
		
		ofertador.procesarAtracciones(this.atracciones);
		
		ArrayList<Atraccion> esperado = new ArrayList<>();
		esperado.add(this.atracciones.get(3));
		esperado.add(this.atracciones.get(4));
		esperado.add(this.atracciones.get(5));
		
		assertEquals(esperado, ofertador.getAtraccionesAOfertar());
	}
	
	//Si el usuario no tiene plata que no la oferte
	//Que no oferte una atraccion comprada en un paquete
	@Test
	public void usuarioSinPlata() {
		Ofertador ofertador = new Ofertador();
		
		ofertador.setUsuario(this.listaUsuarios.get(2));
		
		ofertador.procesarPaquetes(this.paquetes);

		
		ArrayList<Atraccion> esperado = new ArrayList<>();
		
		assertEquals(esperado, ofertador.getAtraccionesAOfertar());
	}
	
	//Si el usuario no tiene tiempo que no la oferte
	@Test
	public void usuarioSinTiempo() {
		Ofertador ofertador = new Ofertador();
		
		ofertador.setUsuario(this.listaUsuarios.get(2));
		
		ofertador.procesarPaquetes(this.paquetes);

		ArrayList<Atraccion> esperado = new ArrayList<>();
		
		assertEquals(esperado, ofertador.getAtraccionesAOfertar());
	}
}
