package RecepcionParque;


import java.util.ArrayList;

import RecepcionParque.Enums.TipoAtraccion;
import RecepcionParque.Promociones.Promocion;

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
		this.tipoAtracciones = atracciones.get(0).getTipo();
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
	
	public double getPrecioOriginal() {
		return this.prom.getPrecioOriginal();
	}
	
	public double getPrecioFinal() {
		return this.prom.getPrecioFinal();
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
	
	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int compareTo(Paquete o) {
	    if(this.getPrecioFinal() == o.getPrecioFinal())
	    {
	    	double resta = this.getHorasTotales() - o.getHorasTotales();
	        return (resta < 0) ? 1 : (resta > 0) ? -1 : 0;
	    }
	    double resta = this.getPrecioFinal() - o.getPrecioFinal();
	    return (resta < 0) ? 1 : (resta > 0) ? -1 : 0;
	}
}
