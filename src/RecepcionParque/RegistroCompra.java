package RecepcionParque;

import java.util.ArrayList;

public class RegistroCompra {
	private double precioTotal;
	private ArrayList<Atraccion> atracciones;
	private ArrayList<Paquete> paquetes;
	private Usuario usuario;
	private double horas;
	
	public RegistroCompra() {
		this.precioTotal = 0;
		this.atracciones = new ArrayList<>();
		this.paquetes = new ArrayList<>();
		this.usuario = null;
		this.horas = 0;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void addPrecioTotal(double precio) {
		this.precioTotal += precio;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(ArrayList<Atraccion> atraccionesVendidas) {
		this.atracciones = atraccionesVendidas;
	}

	public ArrayList<Paquete> getPaquetes() {
		return paquetes;
	}

	public void addPaquete(Paquete paquete) {
		this.paquetes.add(paquete);
	}

	public double getHoras() {
		return horas;
	}

	public void addHoras(double horas) {
		this.horas += horas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Usuario: " + usuario.getNombre() + "\n" +
			   "Atracciones compradas: " + atracciones + "\n" +
			   "Paquetes incluidos: " + paquetes + "\n" +
			   "Horas totales: " + String.format("%.2f",horas) + "\n" +
			   "Precio pagado: " + String.format("%.2f",precioTotal); 
	}
	
}
