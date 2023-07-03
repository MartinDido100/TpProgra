package RecepcionParque;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import RecepcionParque.Enums.TipoAtraccion;
import RecepcionParque.Promociones.PromocionPorcentual;

public class TotalizadorItinerariosTests {

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
		
		paquetes.add(new Paquete("Disney Cinema",atrPaquete,new PromocionPorcentual(0.4, 67)));
		
		atrPaquete.clear();
		atrPaquete.add(this.atracciones.get(3));
		atrPaquete.add(this.atracciones.get(4));
		atrPaquete.add(this.atracciones.get(5));
		
		paquetes.add(new Paquete("Aventura Extrema",atrPaquete,new PromocionPorcentual(0.2, 70)));
	}
	
	//Que cuando compro se reduzca bien la plata del usuario
	@Test
	public void totalDeCompraCorrecta() {
		
		Ofertador ofertador = new Ofertador();
		
		ofertador.setUsuario(this.listaUsuarios.get(0));
		
		ofertador.procesarPaquetes(this.paquetes);

		ofertador.hacerCompra(this.paquetes.get(0),null);
		ofertador.hacerCompra(this.paquetes.get(1),null);
		
		assertEquals(903.8,this.listaUsuarios.get(0).getPresupuesto(),0.001);
	}
	
	//Que cuando compro se reduzca bien el tiempo de usuario
	@Test
	public void totalDeTiempoCorrecta() {
		
		Ofertador ofertador = new Ofertador();
		
		ofertador.setUsuario(this.listaUsuarios.get(0));
		
		ofertador.procesarPaquetes(this.paquetes);

		ofertador.hacerCompra(this.paquetes.get(0),null);
		ofertador.hacerCompra(this.paquetes.get(1),null);
		
		assertEquals(984.5,this.listaUsuarios.get(0).getTiempo(),0.00001);
	}
	
}
