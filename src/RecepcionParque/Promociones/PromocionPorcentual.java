package RecepcionParque.Promociones;


public class PromocionPorcentual extends Promocion{
	private double porcentajeDto;
	
	public PromocionPorcentual(double porcentajeDto,double precioOriginal) {
		super(precioOriginal);
		this.porcentajeDto = porcentajeDto;
	}

	@Override
	public double getPrecioFinal() {
		return this.precioOriginal - (this.precioOriginal*porcentajeDto);
	}
}
