package ParqueAtracciones;

public abstract class Promocion {
	private int descuento;
	private int original;
	
	public Promocion(int desc,int original) {
		this.descuento = desc;
		this.original = original;
	}

//	private int calcularDescuento() {
//		return 0;
//	}
}
