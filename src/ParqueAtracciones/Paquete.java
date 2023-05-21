package ParqueAtracciones;

import java.util.ArrayList;

import ParqueAtracciones.Promociones.Promocion;

public class Paquete {
	private String nombre;
	private ArrayList<Atraccion> atracciones;
	private Promocion prom;
	
	Paquete(String nombre,ArrayList<Atraccion> atracciones,Promocion prom){
		this.nombre = nombre;
		this.atracciones = new ArrayList<>();
		this.atracciones.addAll(atracciones);
		this.prom = prom;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public Promocion getProm() {
		return prom;
	}
}
