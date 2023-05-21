package ParqueAtracciones.Promociones;

public class PromocionAbsoluta extends Promocion{
	private double precioAbsoluto;
	
	public PromocionAbsoluta(double precioOriginal,double precioFinal){
		super(precioOriginal,precioFinal);
		this.precioAbsoluto = precioFinal;
	}

	public double getPrecioAbsoluto() {
		return precioAbsoluto;
	}
}
