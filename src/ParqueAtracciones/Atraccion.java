package ParqueAtracciones;

import ParqueAtracciones.Enums.TipoAtraccion;

public class Atraccion {
	private String nombre;
	private double duracion;
	private int cupos;
	private double precio;
	private TipoAtraccion tipo;
	
	public String getNombre() {
		return nombre;
	}

	public double getDuracion() {
		return duracion;
	}

	public int getCupos() {
		return cupos;
	}

	public TipoAtraccion getTipo() {
		return tipo;
	}

	public void reducirCupos(){
		this.cupos--;
	}
	
	public Atraccion(String nombre, double duracion, int cantVisitantes, double precio, TipoAtraccion tipo) {
		this.nombre = nombre;
		this.duracion = duracion;
		this.cupos = cantVisitantes;
		this.precio = precio;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "[" + nombre + "]";
	}

	public double getPrecio() {
		return this.precio;
	}
}
