package ParqueAtracciones;

import java.util.ArrayList;
import java.util.Scanner;


import ParqueAtracciones.Promociones.PromocionAxB;

public class Recepcion {

	private final ArrayList<Usuario> listaUsuarios;
	private final ParqueAtracciones parque;
	private final ArrayList<Paquete> listaPaquetes;
	
	public Recepcion(ArrayList<Usuario> listaUsuarios, ParqueAtracciones parque, ArrayList<Paquete> listaPaquetes) {
		this.listaUsuarios = listaUsuarios;
		this.parque = parque;
		this.listaPaquetes = listaPaquetes;
	}
	
	private boolean hayCuposPaquete(Paquete paq) {
		boolean cond = true;
		for(Atraccion atr : paq.getAtracciones()) {
			if(atr.getCupos() == 0) {
				cond = false;
			}
		}
		
		return cond;
	}
	
	private boolean puedoVenderAtraccion(Usuario usuario,Atraccion atraccion) {
		return usuario.getPresupuesto() > atraccion.getPrecio() && usuario.getTiempo() > atraccion.getDuracion()
				&& atraccion.getCupos() > 0;
	}
	
	private boolean puedoVenderPaquete(Usuario usuario,Paquete paq) {
		return usuario.getPresupuesto() > paq.getPrecioTotal() && usuario.getTiempo() > paq.getHorasTotales()
				&& hayCuposPaquete(paq);
	}
	
	private boolean ningunaAtraccionVendida(ArrayList<Atraccion> atraccionesVendidas,ArrayList<Atraccion> atrPaquete) {
		boolean cond = true;
		for(Atraccion atr : atrPaquete) {
			if(atraccionesVendidas.contains(atr)) {
				cond = false;
			}
		}
		
		return cond;
	}
	
	public boolean ofrecerAtraccion(Atraccion atraccion,Scanner sc) {
		System.out.println("Atraccion " + atraccion.getNombre() + ": ");
		System.out.println("-Duracion: " + atraccion.getDuracion());
		System.out.println("-Precio: " + atraccion.getPrecio());
		
		String rta;
		do {
			System.out.println("¿Acepta la sugerencia? Ingrese S o N");
			rta = sc.next();
		}while(rta != "S" || rta != "N" || rta != "s" || rta != "n");

		if(rta == "S" || rta == "s") {
			System.out.println("Paquete aceptado!");
			atraccion.reducirCupos();
			return true;
		}
		return false;
	}
	
	public boolean ofrecerPaquete(Paquete paqOfrecido,Scanner sc) {
		System.out.println("Paquete " + paqOfrecido.getNombre() + ":");
		System.out.println("-Atracciones incluidas: " + paqOfrecido.getAtracciones());
		System.out.println("-Duracion: " + paqOfrecido.getHorasTotales());
		System.out.println("-Precio original: " + paqOfrecido.getPrecioTotal());
		System.out.println("-Precio con descuento: " + paqOfrecido.getProm().getPrecioFinal());
		if(paqOfrecido.getProm() instanceof PromocionAxB) {
			PromocionAxB prom = (PromocionAxB)(paqOfrecido.getProm());
			System.out.println("En este paquete tenes como atraccion gratis a " + prom.getAtraccion().getNombre() + "!");
		}

		String rta;
		do {
			System.out.println("¿Acepta la sugerencia? Ingrese S o N");
			rta = sc.next();
		}while(rta != "S" || rta != "N" || rta != "s" || rta != "n");

		if(rta == "S" || rta == "s") {
			System.out.println("Paquete aceptado!");
			paqOfrecido.restarCuposAtracciones();
			return true;
		}
		return false;
	}
	
	public void recibir() {
		
		Scanner sc = new Scanner(System.in);
		
		for(Usuario usuario : this.listaUsuarios) {
			
			RegistroCompra registroCompra = new RegistroCompra();
			
			ArrayList<Atraccion> atraccionesVendidas = new ArrayList<>();
			
			System.out.println("Buenas, " + usuario.getNombre());
			
			ArrayList<Paquete> paquetesPreferencia = new ArrayList<>();
			ArrayList<Paquete> paquetesNoPreferidos = new ArrayList<>();
			
			for(Paquete paq : this.listaPaquetes) {
				if(paq.getTipoAtracciones() == usuario.getPreferencia()) {
					paquetesPreferencia.add(paq);
				}else {
					paquetesNoPreferidos.add(paq);
				}
			}
			
			ArrayList<Paquete> paquetesAOfrecer = new ArrayList<>();
			
			paquetesPreferencia.sort(null); //Si no anda pasar el ArrayList a Array de paquetes
			paquetesNoPreferidos.sort(null);
			
			paquetesAOfrecer.addAll(paquetesPreferencia);
			paquetesAOfrecer.addAll(paquetesNoPreferidos);
		
			for(Paquete paqOfrecido : paquetesAOfrecer) {
				if(this.puedoVenderPaquete(usuario,paqOfrecido) && this.ningunaAtraccionVendida(atraccionesVendidas,paqOfrecido.getAtracciones())) {
					boolean vendido = this.ofrecerPaquete(paqOfrecido,sc);
					if(vendido) {
						atraccionesVendidas.addAll(paqOfrecido.getAtracciones());
						registroCompra.addHoras(paqOfrecido.getHorasTotales());
						registroCompra.addPaquete(paqOfrecido);
						registroCompra.addPrecioTotal(paqOfrecido.getPrecioTotal());
						usuario.hacerCompra(paqOfrecido.getHorasTotales(), paqOfrecido.getPrecioTotal());
					}
				}
			}

			ArrayList<Atraccion> atraccionesPreferencia = new ArrayList<>();
			ArrayList<Atraccion> atraccionesNoPreferidos = new ArrayList<>();
			
			for(Atraccion  atr : this.parque.getAll()) {
				if(!atraccionesVendidas.contains(atr)) {
					if(atr.getTipo() == usuario.getPreferencia()) {
						atraccionesPreferencia.add(atr);
					}else {
						atraccionesNoPreferidos.add(atr);
					}
				}
			}
			
			atraccionesNoPreferidos.sort(null);
			atraccionesPreferencia.sort(null);
			
			ArrayList<Atraccion> atraccionesAOfrecer = new ArrayList<>();
			
			atraccionesAOfrecer.addAll(atraccionesPreferencia);
			atraccionesAOfrecer.addAll(atraccionesAOfrecer);
			
			for(Atraccion atrAOfrecer : atraccionesAOfrecer) {
				if(this.puedoVenderAtraccion(usuario,atrAOfrecer)) {
					boolean vendido = this.ofrecerAtraccion(atrAOfrecer, sc);
					if(vendido) {
						usuario.hacerCompra(atrAOfrecer.getDuracion(),atrAOfrecer.getPrecio());
						registroCompra.addHoras(atrAOfrecer.getDuracion());
						registroCompra.addPrecioTotal(atrAOfrecer.getPrecio());
						atraccionesVendidas.add(atrAOfrecer);
					}
				}
			}

			registroCompra.setAtracciones(atraccionesVendidas);
			registroCompra.setUsuario(usuario);
	
			
			
		}
		
		
		sc.close();
	}
}
