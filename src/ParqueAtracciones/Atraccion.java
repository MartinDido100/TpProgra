package ParqueAtracciones;

import ParqueAtracciones.Enums.TipoAtraccion;

public class Atraccion {
	private String nombre;
	private double duracion;
	private int cantVisitantes;
	private double precio;
	private TipoAtraccion tipo;
	
	public String getNombre() {
		return nombre;
	}

	public double getDuracion() {
		return duracion;
	}

	public int getCantVisitantes() {
		return cantVisitantes;
	}

	public TipoAtraccion getTipo() {
		return tipo;
	}

	public Atraccion(String nombre, double duracion, int cantVisitantes, double precio, TipoAtraccion tipo) {
		this.nombre = nombre;
		this.duracion = duracion;
		this.cantVisitantes = cantVisitantes;
		this.precio = precio;
		this.tipo = tipo;
	}

	public double getPrecio() {
		return this.precio;
	}
}
