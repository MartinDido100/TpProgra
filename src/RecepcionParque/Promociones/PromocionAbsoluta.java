package RecepcionParque.Promociones;

public class PromocionAbsoluta extends Promocion{
	private double precioAbsoluto;
	
	public PromocionAbsoluta(double precioOriginal,double precioFinal){
		super(precioOriginal);
		this.precioAbsoluto = precioFinal;
	}

	@Override
	public double getPrecioFinal() {
		return this.precioAbsoluto;
	}
}
