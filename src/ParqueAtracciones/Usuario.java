package ParqueAtracciones;

import ParqueAtracciones.Enums.TipoAtraccion;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempo;
	private TipoAtraccion preferencia;
	
	public Usuario(String nombre, double presupuesto, double tiempo, TipoAtraccion preferencia) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
		this.preferencia = preferencia;
	}
	
	public void hacerCompra(double horas,double plata) {
		this.presupuesto -= plata;
		this.tiempo -= tiempo;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public double getTiempo() {
		return tiempo;
	}

	public TipoAtraccion getPreferencia() {
		return preferencia;
	}
}
