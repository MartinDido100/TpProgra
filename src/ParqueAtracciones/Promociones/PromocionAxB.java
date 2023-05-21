package ParqueAtracciones.Promociones;

import ParqueAtracciones.Atraccion;

public class PromocionAxB extends Promocion{
	private Atraccion atraccion;
	
	public PromocionAxB(Atraccion atr,double original,double precioFinal){
		super(precioFinal,original);
		this.atraccion = atr;
	}

	public Atraccion getAtraccion() {
		return atraccion;
	}
}
