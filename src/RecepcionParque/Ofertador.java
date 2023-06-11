package RecepcionParque;

import java.util.ArrayList;
public class Ofertador {

	private Usuario usuario;
	private ArrayList<Paquete> paquetesAOfertar;
	private ArrayList<Atraccion> atraccionesAOfertar;
	private RegistroCompra compra;
	
	public Ofertador() {
		usuario = null;
		paquetesAOfertar = new ArrayList<>();
		atraccionesAOfertar = new ArrayList<>();
		compra = new RegistroCompra();
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		this.compra.setUsuario(usuario);
	}
	
	private boolean puedoVenderAtraccion(Atraccion atraccion) {
		return this.usuario.getPresupuesto() >= atraccion.getPrecio() && this.usuario.getTiempo() >= atraccion.getDuracion()
				&& atraccion.getCupos() > 0 && !this.compra.getAtracciones().contains(atraccion);
	}
	
	private boolean puedoVenderPaquete(Paquete paq) {
		return this.usuario.getPresupuesto() >= paq.getPrecioFinal() && this.usuario.getTiempo() >= paq.getHorasTotales()
				&& paq.getCuposPaquete() > 0 && this.puedoOfertarPaquete(paq);
	}
	
	public boolean atraccionComprada(Atraccion atr) {
		return this.compra.getAtracciones().contains(atr);
	}
	
	public boolean puedoOfertarPaquete(Paquete paq) {
		boolean cond = true;
		for(Atraccion atr : paq.getAtracciones()) {
			if(this.atraccionComprada(atr)) {
				cond = false;
			}
		}
		
		return cond;
	}
	
	public void procesarPaquetes(ArrayList<Paquete> paquetes) {
		ArrayList<Paquete> paquetesPreferencia = new ArrayList<>();
		ArrayList<Paquete> paquetesNoPreferidos = new ArrayList<>();
		
		for(Paquete paq : paquetes) {
			if(this.puedoVenderPaquete(paq)){
				if(paq.getTipoAtracciones() == usuario.getPreferencia()) {
					paquetesPreferencia.add(paq);
				}else {
					paquetesNoPreferidos.add(paq);
				}
			}
		}
		
		paquetesNoPreferidos.sort(null);
		paquetesPreferencia.sort(null);
		
		this.paquetesAOfertar.addAll(paquetesPreferencia);
		this.paquetesAOfertar.addAll(paquetesNoPreferidos);
	}
	
	public void procesarAtracciones(ArrayList<Atraccion> atracciones) {
		ArrayList<Atraccion> atraccionesPreferencia = new ArrayList<>();
		ArrayList<Atraccion> atraccionesNoPreferidos = new ArrayList<>();
		
		for(Atraccion  atr : atracciones) {
			if(this.puedoVenderAtraccion(atr)) {
				if(atr.getTipo() == usuario.getPreferencia()) {
					atraccionesPreferencia.add(atr);
				}else {
					atraccionesNoPreferidos.add(atr);
				}
			}
		}
		
		atraccionesPreferencia.sort(null);
		atraccionesNoPreferidos.sort(null);
		
		this.atraccionesAOfertar.addAll(atraccionesPreferencia);
		this.atraccionesAOfertar.addAll(atraccionesNoPreferidos);
	}
	

	public void hacerCompra(Paquete paq) {		
		this.usuario.hacerCompra(paq.getHorasTotales(), paq.getPrecioFinal());
		this.compra.addAtracciones(paq.getAtracciones());
		
		this.compra.addPaquete(paq);
		this.compra.addHoras(paq.getHorasTotales());
		this.compra.addPrecioTotal(paq.getPrecioFinal());
		
		for(Atraccion atr : paq.getAtracciones()) {
			atr.reducirCupos();
			
		}
		
	}
	
	public void hacerCompra(Atraccion atraccion) {
		this.usuario.hacerCompra(atraccion.getDuracion(), atraccion.getPrecio());
		this.compra.addAtraccion(atraccion);
		this.compra.addHoras(atraccion.getDuracion());
		this.compra.addPrecioTotal(atraccion.getPrecio());
		atraccion.reducirCupos();
	}
	
	public void reiniciarOferta() {
		this.paquetesAOfertar.clear();
		this.atraccionesAOfertar.clear();
		this.compra = new RegistroCompra();
	}
	
	public ArrayList<Paquete> getPaquetesAOfertar() {
		return paquetesAOfertar;
	}

	public ArrayList<Atraccion> getAtraccionesAOfertar() {
		return atraccionesAOfertar;
	}

	public RegistroCompra getCompra() {
		return this.compra;
	}
}
