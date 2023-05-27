package ParqueAtracciones;


import java.util.ArrayList;

import ParqueAtracciones.Enums.TipoAtraccion;
import ParqueAtracciones.Promociones.Promocion;

public class Paquete implements Comparable<Paquete>{
	private String nombre;
	private ArrayList<Atraccion> atracciones;
	private Promocion prom;
	private TipoAtraccion tipoAtracciones;
	
	public TipoAtraccion getTipoAtracciones() {
		return tipoAtracciones;
	}

	Paquete(String nombre,ArrayList<Atraccion> atracciones,Promocion prom){
		this.nombre = nombre;
		this.atracciones = new ArrayList<>();
		this.atracciones.addAll(atracciones);
		this.prom = prom;
	}

	public void restarCuposAtracciones() {
		for(Atraccion atr : this.atracciones) {
			atr.reducirCupos();
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atracciones;
	}

	public Promocion getProm() {
		return prom;
	}

	public double getHorasTotales() {
		double horas = 0;
		for(Atraccion atr : this.atracciones) {
			horas += atr.getDuracion();
		}
		
		return horas;
	}
	
	public double getPrecioTotal() {
		double precio = 0;
		for(Atraccion atr : this.atracciones) {
			precio += atr.getPrecio();
		}
		
		return precio;
	}
	
	@Override
	public int compareTo(Paquete o) {
	    if(this.getPrecioTotal() == o.getPrecioTotal())
	    {
	    	double resta = this.getHorasTotales() - o.getHorasTotales();
	        return (resta < 0) ? -1 : (resta > 0) ? 1 : 0;
	    }
	    double resta = this.getPrecioTotal() - o.getPrecioTotal();
	    return (resta < 0) ? -1 : (resta > 0) ? 1 : 0;
	}
}
