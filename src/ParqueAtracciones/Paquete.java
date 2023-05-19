package ParqueAtracciones;

import java.util.ArrayList;

public class Paquete {
	private String nombre;
	private ArrayList<Atraccion> atracciones;
	private Promocion prom;
	
	private int calcularPrecioOriginal(){
		int precio = 0;
		for(Atraccion a : this.atracciones) {
			precio += a.getPrecio();
		}
		return precio;
	}
	
	Paquete(String nombre,ArrayList<Atraccion> atracciones,Promocion prom){
		this.nombre = nombre;
		this.atracciones = new ArrayList<>();
		this.atracciones.addAll(atracciones);
		this.prom = prom;
		
		
//		if(tipoProm == TipoPromocion.ABSOLUTA) {
//			this.tipoProm = new PromocionAbsoluta();
//		}else if (tipoProm == TipoPromocion.AXB) {
//			this.tipoProm = new PromocionAxB(precioOriginal);
//		}else {
//			this.tipoProm = new PromocionPorcentual();
//		}
	}
}
