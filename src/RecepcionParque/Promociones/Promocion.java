package RecepcionParque.Promociones;


public abstract class Promocion {
	protected double precioOriginal;
	
	public Promocion(double original) {
		this.precioOriginal = original;
	}

	public double getPrecioOriginal() {
		return precioOriginal;
	}

	public abstract double getPrecioFinal();
}
