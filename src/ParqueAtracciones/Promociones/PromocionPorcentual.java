package ParqueAtracciones.Promociones;

public class PromocionPorcentual extends Promocion{
	private double porcentajeDto;
	
	public PromocionPorcentual(double porcentajeDto,double precioOriginal) {
		super(precioOriginal,precioOriginal - (precioOriginal*porcentajeDto));
		this.porcentajeDto = porcentajeDto;
	}

	public double getPorcentajeDto() {
		return porcentajeDto;
	}

}
