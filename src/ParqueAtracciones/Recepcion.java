package ParqueAtracciones;

import java.util.ArrayList;
import java.util.Scanner;

import ParqueAtracciones.Enums.TipoAtraccion;
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
	
	public void recibir() {
		///Aca va el quilombo
		//Ir ofreciendo y hacer un arrayList de vendidas
		
		//Ir recorriendo cada usuario, setear el orden de paquetes y atracciones, ofrecer.
		
		Scanner sc = new Scanner(System.in);
		for(Usuario usuario : this.listaUsuarios) {
			
			ArrayList<Atraccion> atraccionesVendidas = new ArrayList<>();
			
			System.out.println("Buenas, " + usuario.getNombre());
			
			//Orden ofrecer: Paquetes por preferencia precio horas,Atracciones por preferencia precio horas
			ArrayList<Paquete> paquetesPreferencia = new ArrayList<>();
			ArrayList<Paquete> paquetesNoPreferidos = new ArrayList<>();
			
			for(Paquete paq : this.listaPaquetes) {
				if(paq.getTipoAtracciones() == usuario.getPreferencia()) {
					paquetesPreferencia.add(paq);
				}else {
					paquetesNoPreferidos.add(paq);
				}
			}
			
			paquetesPreferencia.sort(null); //Si no anda pasar el ArrayList a Array de paquetes
			paquetesNoPreferidos.sort(null);
		
			for(Paquete paqPref : paquetesPreferencia) {
				if(this.puedoVenderPaquete(usuario, paqPref) && this.ningunaAtraccionVendida(atraccionesVendidas,paqPref.getAtracciones())) {
					System.out.println("Paquete " + paqPref.getNombre() + ":");
					System.out.println("-Atracciones incluidas: " + paqPref.getAtracciones());
					System.out.println("-Duracion: " + paqPref.getHorasTotales());
					System.out.println("-Precio original: " + paqPref.getPrecioTotal());
					System.out.println("-Precio con descuento: " + paqPref.getProm().getPrecioFinal());
					if(paqPref.getProm() instanceof PromocionAxB) {
						PromocionAxB prom = (PromocionAxB)(paqPref.getProm());
						System.out.println("En este paquete tenes como atraccion gratis a " + prom.getAtraccion().getNombre() + "!");
					}

					String rta;
					do {
						System.out.println("¿Acepta la sugerencia? Ingrese S o N");
						rta = sc.next();
					}while(rta != "S" || rta != "N" || rta != "s" || rta != "n");

					if(rta == "S" || rta == "s") {
						System.out.println("Paquete aceptado!");
						paqPref.restarCuposAtracciones();
						atraccionesVendidas.addAll(paqPref.getAtracciones());
					}
				}

			}
			
		}
		
	}
	
	
}
