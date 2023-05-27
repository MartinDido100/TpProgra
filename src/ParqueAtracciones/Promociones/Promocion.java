package ParqueAtracciones.Promociones;

public abstract class Promocion {
	protected double precioOriginal;
	
	public Promocion(double original) {
		this.precioOriginal = original;
	}

	public abstract double getPrecioFinal();
}
