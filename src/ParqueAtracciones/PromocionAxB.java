package ParqueAtracciones;

public class PromocionAxB extends Promocion{
	private Atraccion atraccion;
	
	PromocionAxB(Atraccion atr,int original){
		super(100,original);
		this.atraccion = atr;
	}
}
