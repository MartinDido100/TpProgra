package ParqueAtracciones;

public class PromocionAxB extends Promocion{
	private Atraccion atraccion;
	
	PromocionAxB(Atraccion atr){
		super(100);
		this.atraccion = atr;
	}
}
