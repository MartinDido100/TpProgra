package Main;

import java.util.ArrayList;

import RecepcionParque.*;

public class Main {

	public static void main(String[] args) {
		String linea = "-".repeat(146);
		System.out.println(String.format("\n%90s", "Welcome to Disney World"));
		System.out.println(linea);
		
		//Cargar archivos
		Archivo arch = new Archivo("Usuarios");
		ArrayList<Usuario> listaUsuarios = arch.leerUsuarios();
		arch.setNombre("Atracciones");
		ParqueAtracciones parque = arch.crearAtracciones();
		
		arch.setNombre("Paquetes");
		ArrayList<Paquete> listaPaquetes = arch.leerPaquetes(parque);
		
		Recepcion recep = new Recepcion(listaUsuarios,parque,listaPaquetes);
		
		recep.recibir();
	}

}
