package RecepcionParque;

import RecepcionParque.Enums.TipoAtraccion;

public class Atraccion implements Comparable<Atraccion>{
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
		return nombre;
	}

	public double getPrecio() {
		return this.precio;
	}
	
	@Override
	public int compareTo(Atraccion o) {
	    if(this.getPrecio() == o.getPrecio())
	    {
	    	double resta = this.getDuracion() - o.getDuracion();
	        return (resta < 0) ? 1 : (resta > 0) ? -1 : 0;
	    }
	    double resta = this.getPrecio() - o.getPrecio();
	    return (resta < 0) ? 1 : (resta > 0) ? -1 : 0;
	}
}
