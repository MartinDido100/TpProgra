package ParqueAtracciones;

public abstract class Promocion {
	private int descuento;
	private int original;
	
	public Promocion(int desc) {
		this.descuento = desc;
		this.original = 0;
	}

//	private int calcularDescuento() {
//		return 0;
//	}
}
