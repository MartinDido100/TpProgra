package Main;

import java.util.ArrayList;

import RecepcionParque.*;

public class Main {

	public static void main(String[] args) {
		String linea = "-".repeat(146);
		System.out.println(String.format("\n%30s", "Welcome to Disney World"));
		System.out.println(linea);

		//Cargar archivos
		Archivo arch = new Archivo("Archivos/Usuarios.in");
		ArrayList<Usuario> listaUsuarios = arch.leerUsuarios();
		arch.setNombre("Archivos/Atracciones.in");
		ParqueAtracciones parque = arch.crearAtracciones();
		
		arch.setNombre("Archivos/Paquetes.in");
		ArrayList<Paquete> listaPaquetes = arch.leerPaquetes(parque);
		
		Recepcion recep = new Recepcion(listaUsuarios,parque,listaPaquetes);
		
		arch.setNombre("Archivos/Registros.out");
		arch.guardarRegistrosCompra(recep.recibir());
		
	}

}
