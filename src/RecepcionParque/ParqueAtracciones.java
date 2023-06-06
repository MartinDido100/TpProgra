package RecepcionParque;

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
		ArrayList<Atraccion> aL = new ArrayList<>();
		aL.addAll(this.mapAtracciones.values());
		return aL;
	}
	
	public void setAtraccion(String nombre,Atraccion atraccion) {
		this.mapAtracciones.put(nombre, atraccion);
	}
	
}
