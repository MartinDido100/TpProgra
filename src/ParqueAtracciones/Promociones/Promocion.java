package ParqueAtracciones.Promociones;

public abstract class Promocion {
	private double precioFinal; //Precio final
	private double precioOriginal;
	
	public Promocion(double precioFinal,double original) {
		this.precioFinal = precioFinal;
		this.precioOriginal = original;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public double getPrecioOriginal() {
		return precioOriginal;
	}
	
}
