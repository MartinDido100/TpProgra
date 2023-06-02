package RecepcionParque.Promociones;

import RecepcionParque.Atraccion;

public class PromocionAxB extends Promocion{
	private Atraccion atraccion;
	private double precioAtraccion;
	
	public PromocionAxB(Atraccion atr,double original,double precioAtraccion){
		super(original);
		this.atraccion = atr;
		this.precioAtraccion = precioAtraccion;
	}

	public Atraccion getAtraccion() {
		return atraccion;
	}

	@Override
	public double getPrecioFinal() {
		return this.precioOriginal - this.precioAtraccion;
	}
}
