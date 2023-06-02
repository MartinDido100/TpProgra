package ParqueAtracciones;

import java.util.ArrayList;
import java.util.HashMap;

public class ParqueAtracciones {
	private HashMap<String,Atraccion> mapAtracciones;
	
	public ParqueAtracciones(){
		this.mapAtracciones = new HashMap<>();
	}
	
	public Atraccion getAtraccion(String nombre){
		return this.mapAtracciones.get(nombre);
	}
	
	public ArrayList<Atraccion> getAll(){
		return (ArrayList<Atraccion>)this.mapAtracciones.values();
	}
	
	public void setAtraccion(String nombre,Atraccion atraccion) {
		this.mapAtracciones.put(nombre, atraccion);
	}
	
}
