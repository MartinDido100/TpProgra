package RecepcionParque;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class OfertadorTests {
	ArrayList<Usuario> listaUsuarios;
	ParqueAtracciones parque;
	ArrayList<Paquete> paquetes;
	
	@Before
	public void inicializar() {
		Archivo arch = new Archivo("ArchivosTests/UsuariosTest.in");
		listaUsuarios = arch.leerUsuarios();
		arch.setNombre("ArchivosTests/AtraccionesTest.in");
		parque = arch.crearAtracciones();
		
		arch.setNombre("ArchivosTests/PaqueteTest.in");
		paquetes = arch.leerPaquetes(parque);
	}
	
	
	//Se testea que el orden de oferta sea el correcto, Dar todo que si en la consola.
	@Test
	public void ordenDeOfertaPorPreferencia() {
		Recepcion recep = new Recepcion(listaUsuarios,parque,paquetes);
		
		ArrayList<RegistroCompra> registro = recep.recibir();
		
		Archivo arch = new Archivo("ArchivosTests/RegistrosTest.out");
		
		arch.guardarRegistrosCompra(registro);
		
		try {
            byte[] f1 = Files.readAllBytes(Paths.get("ArchivosTests/RegistrosTest.out"));
            byte[] f2 = Files.readAllBytes(Paths.get("ArchivosTests/RegistroEsperadoTest.out"));
            assertArrayEquals(f2, f1);
        } catch (IOException e) {
            fail();
        }
	}

	@Test
	public void ofertaSinCupos() {
		Recepcion recep = new Recepcion(listaUsuarios,parque,paquetes);
		
		ArrayList<RegistroCompra> registro = recep.recibir();
		
		Archivo arch = new Archivo("ArchivosTests/RegistrosTest.out");
		
		arch.guardarRegistrosCompra(registro);
		
		try {
            byte[] f1 = Files.readAllBytes(Paths.get("ArchivosTests/RegistrosTest.out"));
            byte[] f2 = Files.readAllBytes(Paths.get("ArchivosTests/RegistroEsperadoTest.out"));
            assertArrayEquals(f2, f1);
        } catch (IOException e) {
            fail();
        }
	}
	
}
