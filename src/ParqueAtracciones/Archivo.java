package ParqueAtracciones;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import ParqueAtracciones.Enums.TipoAtraccion;
import ParqueAtracciones.Enums.TipoPromocion;
import ParqueAtracciones.Promociones.Promocion;
import ParqueAtracciones.Promociones.PromocionAbsoluta;
import ParqueAtracciones.Promociones.PromocionAxB;
import ParqueAtracciones.Promociones.PromocionPorcentual;

public class Archivo {

	private String nombre;
	
	Archivo(String nom){
		this.nombre = nom;
	}
	
	public void setNombre(String nom){
		this.nombre = nom;
	}
	
	//Leer usuarios
	public ArrayList<Usuario> leerUsuarios(){
		Scanner sc = null;
		ArrayList<Usuario> usuarios = null;
		
		
		try {
			File arch = new File("Archivos/" + this.nombre + ".in");
			sc = new Scanner(arch);
			
			usuarios = new ArrayList<>();
			
			while(sc.hasNextLine()) {
				String[] campos = sc.nextLine().split("|");
				
				String nombre = campos[0];
				Double presupuesto = Double.parseDouble(campos[1]);
				Double tiempo = Double.parseDouble(campos[2]);
				TipoAtraccion preferencia = TipoAtraccion.valueOf(campos[3]);
				
				usuarios.add(new Usuario(nombre,presupuesto,tiempo,preferencia));
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
		
		return usuarios;
	}
	
	//Crear leerAtracciones
	public ParqueAtracciones crearAtracciones() {
		Scanner sc = null;
		ParqueAtracciones p = null;
		
		try {
			File arch = new File("Archivos/" + this.nombre + ".in");
			sc = new Scanner(arch);
			
			p = new ParqueAtracciones();
			
			while(sc.hasNextLine()) {
				String[] campos = sc.nextLine().split("|");
				
				String nombre = campos[0];
				double hs = Double.parseDouble(campos[1]);
				int cupo = Integer.parseInt(campos[2]);
				double precio = Double.parseDouble(campos[3]);
				TipoAtraccion tipoA = TipoAtraccion.valueOf(campos[4].toUpperCase());
				
				p.setAtraccion(nombre, new Atraccion(nombre,hs,cupo,precio,tipoA));
				
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
		
		return p;
	}
	
	
	//Crear leerUsuarios
	
	public ArrayList<Paquete> leerPaquetes(ParqueAtracciones parque){
		ArrayList<Paquete> listaPaquetes = null;
		Scanner sc = null;
		
		try {
			File arch = new File("Archivos/" + this.nombre + ".in");
			sc = new Scanner(arch);
			
			sc.useLocale(Locale.ENGLISH);
			
			listaPaquetes = new ArrayList<Paquete>();
			Paquete paq;
			
			while(sc.hasNextLine()) {
				String[] linea = sc.nextLine().split("|");
				
				String[] atracciones = linea[0].split(";");
				ArrayList<Atraccion> listaAtracciones = new ArrayList<>();
				
				double precioOriginalPaquete = 0;
				
				for(int i = 0; i<atracciones.length; i++) {
					Atraccion atr = parque.getAtraccion(atracciones[i]);
					precioOriginalPaquete += atr.getPrecio();
					listaAtracciones.add(atr);
				}
				
				String nombrePaquete = linea[1];
				TipoPromocion tipoProm = TipoPromocion.valueOf(linea[2].toUpperCase());
				
				if(tipoProm == TipoPromocion.ABSOLUTA || tipoProm == TipoPromocion.PORCENTUAL) {
					
					double dto = Double.parseDouble(linea[3]);
					
					Promocion prom = (tipoProm == TipoPromocion.ABSOLUTA) ? new PromocionAbsoluta(precioOriginalPaquete,dto) : new PromocionPorcentual(dto,precioOriginalPaquete);
					
					paq = new Paquete(nombrePaquete,listaAtracciones,prom);
					
					listaPaquetes.add(paq);
				}else {
					String atrGratis = linea[3];
					
					Promocion prom = new PromocionAxB(parque.getAtraccion(atrGratis),precioOriginalPaquete,precioOriginalPaquete - parque.getAtraccion(atrGratis).getPrecio());
					
					paq = new Paquete(nombrePaquete,listaAtracciones,prom);
					
					listaPaquetes.add(paq);
				}
				
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
		
		return listaPaquetes;
	}
	
}
