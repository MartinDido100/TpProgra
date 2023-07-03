package RecepcionParque;

import java.util.ArrayList;
import java.util.Scanner;

import RecepcionParque.Promociones.PromocionAxB;

public class Recepcion {

	private final ArrayList<Usuario> listaUsuarios;
	private final ParqueAtracciones parque;
	private final ArrayList<Paquete> listaPaquetes;
	
	public Recepcion(ArrayList<Usuario> listaUsuarios, ParqueAtracciones parque, ArrayList<Paquete> listaPaquetes) {
		this.listaUsuarios = listaUsuarios;
		this.parque = parque;
		this.listaPaquetes = listaPaquetes;
	}
	
	public static void bienvenida() {
		System.out.println("::::::::: ::::::::::: :::::::::: ::::    ::: :::     ::: :::::::::: ::::    ::: ::::::::::: :::::::::   ::::::::   ::::::::\r\n"
				+ ":+:    :+:    :+:     :+:        :+:+:   :+: :+:     :+: :+:        :+:+:   :+:     :+:     :+:    :+: :+:    :+: :+:    :+: \r\n"
				+ "+:+    +:+    +:+     +:+        :+:+:+  +:+ +:+     +:+ +:+        :+:+:+  +:+     +:+     +:+    +:+ +:+    +:+ +:+\r\n"
				+ "+#++:++#+     +#+     +#++:++#   +#+ +:+ +#+ +#+     +:+ +#++:++#   +#+ +:+ +#+     +#+     +#+    +:+ +#+    +:+ +#++:++#++ \r\n"
				+ "+#+    +#+    +#+     +#+        +#+  +#+#+#  +#+   +#+  +#+        +#+  +#+#+#     +#+     +#+    +#+ +#+    +#+        +#+ \r\n"
				+ "#+#    #+#    #+#     #+#        #+#   #+#+#   #+#+#+#   #+#        #+#   #+#+#     #+#     #+#    #+# #+#    #+# #+#    #+# \r\n"
				+ "######### ########### ########## ###    ####     ###     ########## ###    #### ########### #########   ########   ########");
	}
	
	private boolean preguntar(Scanner sc) {
		String linea;
		char rta;
		do {
			System.out.println("\n¿Acepta la sugerencia? Ingrese S o N");
			linea = sc.next();
			rta = linea.charAt(0);
		}while((rta != 'S' || rta != 'N' || rta != 's' || rta != 'n') && linea.length() != 1);

		if(rta == 'S' || rta == 's') {
			System.out.println("Compra aceptada!");
			return true;
		}
		return false;
	}
	
	public boolean ofrecerAtraccion(Atraccion atraccion,Scanner sc) {
		System.out.println("Atraccion " + atraccion.getNombre() + ": ");
		System.out.println("-Duracion: " + atraccion.getDuracion());
		System.out.println("-Precio: " + atraccion.getPrecio());
		
		return this.preguntar(sc);
	}
	
	public boolean ofrecerPaquete(Paquete paqOfrecido,Scanner sc) {
		System.out.println("Paquete " + paqOfrecido.getNombre() + ":");
		System.out.println("-Atracciones incluidas: " + paqOfrecido.getAtracciones());
		System.out.println("-Duracion: " + String.format("%.2f", paqOfrecido.getHorasTotales()));   
		System.out.println("-Precio original: " + String.format("%.2f", paqOfrecido.getPrecioOriginal()));
		System.out.println("-Precio con descuento: " + paqOfrecido.getPrecioFinal());
		if(paqOfrecido.getProm() instanceof PromocionAxB) {
			PromocionAxB prom = (PromocionAxB)(paqOfrecido.getProm());
			System.out.println("En este paquete tenes como atraccion gratis a " + prom.getAtraccion().getNombre() + "!");
		}

		return this.preguntar(sc);
	}
	
	public void recibir() {
		
		Scanner sc = new Scanner(System.in);
		
		Ofertador ofertador = new Ofertador();
		
		Archivo arch = new Archivo("Archivos/Registros.out");
		
		for(Usuario usuario : this.listaUsuarios) {
			
			
			ofertador.setUsuario(usuario);
			
			System.out.println("Buenas, " + usuario.getNombre());
			
			ofertador.procesarPaquetes(this.listaPaquetes);
			
			for(Paquete paqOfrecido : ofertador.getPaquetesAOfertar()) {
				if(ofertador.puedoVenderPaquete(paqOfrecido)) {
					System.out.println(usuario.getPresupuesto());

					boolean vendido = this.ofrecerPaquete(paqOfrecido,sc);
					if(vendido) {
						ofertador.hacerCompra(paqOfrecido,null);
					}
				}
			}

			ofertador.procesarAtracciones(this.parque.getAll());

			System.out.println(ofertador.getAtraccionesAOfertar());
			
			for(Atraccion atrAOfrecer : ofertador.getAtraccionesAOfertar()) {
				if(ofertador.puedoVenderAtraccion(atrAOfrecer)){
					System.out.println(usuario.getPresupuesto());
					boolean vendido = this.ofrecerAtraccion(atrAOfrecer,sc);
					//String linea = "-".repeat(146);
					//System.out.println(linea);
					
					if(vendido) {
						ofertador.hacerCompra(null,atrAOfrecer);
					}
				}
			}
			
			arch.guardarRegistroCompra(ofertador.getCompra());
			
			ofertador.reiniciarOferta();
		
			System.out.println(" ::::::::  :::::::::      :::      :::::::: :::::::::::     :::      ::::::::\r\n"
					+ ":+:    :+: :+:    :+:   :+: :+:   :+:    :+:    :+:       :+: :+:   :+:    :+: \r\n"
					+ "+:+        +:+    +:+  +:+   +:+  +:+           +:+      +:+   +:+  +:+\r\n"
					+ ":#:        +#++:++#:  +#++:++#++: +#+           +#+     +#++:++#++: +#++:++#++ \r\n"
					+ "+#+   +#+# +#+    +#+ +#+     +#+ +#+           +#+     +#+     +#+        +#+ \r\n"
					+ "#+#    #+# #+#    #+# #+#     #+# #+#    #+#    #+#     #+#     #+# #+#    #+# \r\n"
					+ " ########  ###    ### ###     ###  ######## ########### ###     ###  ########");
			
		}
		
		sc.close();
	}
}
