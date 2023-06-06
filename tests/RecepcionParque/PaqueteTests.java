package RecepcionParque;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import RecepcionParque.Enums.TipoAtraccion;
import RecepcionParque.Promociones.PromocionAbsoluta;
import RecepcionParque.Promociones.PromocionAxB;
import RecepcionParque.Promociones.PromocionPorcentual;

public class PaqueteTests {

	ArrayList<Atraccion> atraccionesPaquete;
	
	@Before
	public void inicializar() {
		atraccionesPaquete = new ArrayList<>();

		atraccionesPaquete.add(new Atraccion("Star Wars",2.2,1,20,TipoAtraccion.SIMULACION));
		atraccionesPaquete.add(new Atraccion("SpiderMan",1.5,20,25,TipoAtraccion.SIMULACION));
		atraccionesPaquete.add(new Atraccion("Lilo y Stitch",6,11,22,TipoAtraccion.SIMULACION));

	}
	
	@Test
	public void promocionPorcentualTest() {
		Paquete paq = new Paquete("PaqueteTest",atraccionesPaquete,new PromocionPorcentual(0.4, 67));
		
		double precioObtenido = paq.getPrecioFinal();
		double precioEsperado = 40.2;
		
		assertEquals(precioObtenido, precioEsperado,0.001);
	}

	@Test
	public void promocionAxBTest() {
		Paquete paq = new Paquete("PaqueteTest",atraccionesPaquete,new PromocionAxB(atraccionesPaquete.get(0), 67,atraccionesPaquete.get(0).getPrecio()));
		
		double precioObtenido = paq.getPrecioFinal();
		double precioEsperado = 47;
		
		assertEquals(precioObtenido, precioEsperado,0.001);
	}
	
	@Test
	public void promocionAbsolutaTest() {
		Paquete paq = new Paquete("PaqueteTest0",atraccionesPaquete,new PromocionAbsoluta(67, 50));
		
		double precioObtenido = paq.getPrecioFinal();
		double precioEsperado = 50;
		
		assertEquals(precioObtenido, precioEsperado,0.001);
	}
	
	//Se prueba el compareTo de los paquetes (Ordena del mas caro al mas barato)
	@Test
	public void ordenarPaquetes() {
		Paquete[] paquetes = new Paquete[3];
		
		paquetes[0] = new Paquete("PaqueteTest0",atraccionesPaquete,new PromocionAbsoluta(67, 50));
		paquetes[1] = new Paquete("PaqueteTest1",atraccionesPaquete,new PromocionPorcentual(0.4, 67));
		paquetes[2] = new Paquete("PaqueteTest2",atraccionesPaquete,new PromocionAxB(atraccionesPaquete.get(0), 67,atraccionesPaquete.get(0).getPrecio()));
		
		
		Paquete[] esperado = {
			paquetes[0],
			paquetes[2],
			paquetes[1]
		};
		
		Arrays.sort(paquetes);
		
		assertArrayEquals(esperado, paquetes);
	}
	
	@Test
	public void cuposPaqueteCorrectos() {
		Paquete paq = new Paquete("PaqueteTest0",atraccionesPaquete,new PromocionAbsoluta(67, 50));
		
		assertEquals(1, paq.getCuposPaquete());
	}
	
}
