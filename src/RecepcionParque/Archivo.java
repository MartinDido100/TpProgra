package RecepcionParque;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import RecepcionParque.Enums.TipoAtraccion;
import RecepcionParque.Enums.TipoPromocion;
import RecepcionParque.Promociones.Promocion;
import RecepcionParque.Promociones.PromocionAbsoluta;
import RecepcionParque.Promociones.PromocionAxB;
import RecepcionParque.Promociones.PromocionPorcentual;

public class Archivo {

	private String path;
	
	public Archivo(String path){
		this.path = path;
	}
	
	public void setNombre(String path){
		this.path = path;
	}
	
	public ArrayList<Usuario> leerUsuarios(){
		Scanner sc = null;
		ArrayList<Usuario> usuarios = null;
		
		try {
			File arch = new File(path);
			sc = new Scanner(arch);
			
			usuarios = new ArrayList<>();
			
			while(sc.hasNextLine()) {
				String[] campos = sc.nextLine().split("\\|");
				
				String nombre = campos[0];
				Double presupuesto = Double.parseDouble(campos[1]);
				Double tiempo = Double.parseDouble(campos[2]);
				TipoAtraccion preferencia = TipoAtraccion.valueOf(campos[3].toUpperCase());
				
				usuarios.add(new Usuario(nombre,presupuesto,tiempo,preferencia));
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
		
		return usuarios;
	}
	
	public ParqueAtracciones crearAtracciones() {
		Scanner sc = null;
		ParqueAtracciones p = null;
		try {
			File arch = new File(path);
			sc = new Scanner(arch);
			
			p = new ParqueAtracciones();
			
			while(sc.hasNextLine()) {
				String[] campos = sc.nextLine().split("\\|");
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
	
	
	public void guardarRegistrosCompra(ArrayList<RegistroCompra> registros) {
	    FileWriter file = null;
	    PrintWriter printerWriter = null;
		  try {
			  file = new FileWriter(path);
			  printerWriter = new PrintWriter(file);

			  for(RegistroCompra reg : registros) {
				  printerWriter.println(reg);
				  printerWriter.println("--------------------------------------------");
			  }
			  
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        if (file != null) {
		            try {
		                file.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		
	}

	
	public ArrayList<Paquete> leerPaquetes(ParqueAtracciones parque){
		ArrayList<Paquete> listaPaquetes = null;
		Scanner sc = null;
		
		try {
			File arch = new File(path);
			sc = new Scanner(arch);
			
			sc.useLocale(Locale.ENGLISH);
			
			listaPaquetes = new ArrayList<Paquete>();
			Paquete paq;
			while(sc.hasNextLine()) {
				String[] linea = sc.nextLine().split("\\|");
				
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
					
					Promocion prom = new PromocionAxB(parque.getAtraccion(atrGratis),precioOriginalPaquete,parque.getAtraccion(atrGratis).getPrecio());
					
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
