package ParqueAtracciones;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Archivo {

	private String nombre;
	
	Archivo(String nom){
		this.nombre = nom;
	}
	
	
	//Crear leerAtracciones
	
	//Crear leerUsuarios
	
	public ArrayList<Paquete> leerPaquetes(){
		ArrayList<Paquete> aL = null;
		Scanner sc = null;
		
		try {
			File arch = new File("Archivos/" + this.nombre + ".in");
			sc = new Scanner(arch);
			
			sc.useLocale(Locale.ENGLISH);
			
			aL = new ArrayList<Paquete>();
			Paquete paq;
			
			while(sc.hasNextLine()) {
				String[] linea = sc.nextLine().split("|"); //["Atr1;Atr2","Nombre Paquete","ABSOLUTA,50"]
				
				String[] atracciones = linea[0].split(";"); //["Atr1","Atr2"]
				ArrayList<Atraccion> listaAtracciones = new ArrayList<>();
				
				for(int i = 0; i<atracciones.length; i++) {
					/*
					 * 1- Recorrer el archivo de atracciones y crear la clase parque
					 * 2- Con el array atracciones ir a buscar la atraccion al parque
					 * 3- Con la atraccion, la agrego a listaAtracciones para mandarlo despues al paquete
					 * 
					 * */
					listaAtracciones.add(new Atraccion());
				}
				
				String nombrePaquete = linea[1]; // "Nombre Paquete"
				TipoPromocion tipoProm = TipoPromocion.valueOf(linea[2].toUpperCase()); //ABSOLUTA
				String valorProm = linea[3];
				
				if(tipoProm == TipoPromocion.ABSOLUTA || tipoProm == TipoPromocion.PORCENTUAL) {
					
					int dto = Integer.parseInt(valorProm);
					
					Promocion prom = (tipoProm == TipoPromocion.ABSOLUTA) ? new PromocionAbsoluta() : new PromocionPorcentual();
					
					paq = new Paquete(nombrePaquete,listaAtracciones,prom);
					
				}else {
					//Caso promocion AxB (Igual que arriba pero con prom AxB)
					
					
				}
				
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
		
		return aL;
	}
	
}
