import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.opencsv.CSVParser;

import exceptions.ArchivoInexistenteException;
import exceptions.PathIncorrectoException;
import exceptions.TipoDeArchivoIncorrectoException;
import parser.CSVToEmpresas;


public class TestCSV {

	CSVToEmpresas CSVparser;
	
	@Before
	public void init() throws IOException {
 		CSVparser = new CSVToEmpresas("Prueba.csv");
 	}
	
	
	@Test
	public void archivoCSVMalRealizadoEntoncesLaListaDeEmpresasTieneDatosNulos() throws IOException{
		
		CSVparser = new CSVToEmpresas("PruebaFalla.csv");
		
		assertEquals( null , CSVparser.csvFileToEmpresas().get(0).getNombre());
		assertEquals( null , CSVparser.csvFileToEmpresas().get(0).getCuentas().get(0).getNombre());
		assertEquals( null , CSVparser.csvFileToEmpresas().get(0).getCuentas().get(0).getPeriodo());
		assertEquals( 0 , CSVparser.csvFileToEmpresas().get(0).getCuentas().get(0).getValor());
		
	}
	
	@Test(expected = PathIncorrectoException.class)
	public void pasarPathIncorrectoYQueExplote() throws IOException {
		
		CSVparser = new CSVToEmpresas("Inexistente.csv");
		CSVparser.csvFileToEmpresas();
	}
	
	@Test(expected = ArchivoInexistenteException.class)
	public void pasarArchivoInexistenteYQueExplote() throws IOException {
		
		CSVparser = new CSVToEmpresas(null);
		CSVparser.csvFileToEmpresas();
	}
	
	@Test(expected = TipoDeArchivoIncorrectoException.class)
	public void pasarArchivoConExtensionIncorrectaYQueExplote() throws IOException {
		
		CSVparser = new CSVToEmpresas("empresas.json");
		CSVparser.csvFileToEmpresas();
	}
	
 	
 	
}
