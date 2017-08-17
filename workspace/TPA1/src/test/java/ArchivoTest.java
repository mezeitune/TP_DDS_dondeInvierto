import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import excepciones.ArchivoInexistenteException;
import excepciones.PathIncorrectoException;
import excepciones.TipoDeArchivoIncorrectoException;
import parserArchivos.CSVToEmpresas;
import usuario.Empresa;

public class ArchivoTest {

	CSVToEmpresas parserCSV;
	
	@Before
 	public void init() throws IOException {
		parserCSV = new CSVToEmpresas("Prueba.csv");
 	}
	
	@Test
 	public void pasarArchivoExistenteYQueFuncioneCorrectamente() throws IOException {
 		
		CSVToEmpresas CSVparser = new CSVToEmpresas("Prueba.csv");
		CSVparser.csvFileToEmpresas();
 	    
 	}
 	
 	@Test(expected = PathIncorrectoException.class)
	public void pasarPathIncorrectoYQueExplote() throws IOException {
		
		CSVToEmpresas CSVparser = new CSVToEmpresas("Inexistente.csv");
		CSVparser.csvFileToEmpresas();
	}
	
	@Test(expected = ArchivoInexistenteException.class)
	public void pasarArchivoInexistenteYQueExplote() throws IOException {
		
		CSVToEmpresas CSVparser = new CSVToEmpresas(null);
		CSVparser.csvFileToEmpresas();
	}
	
	@Test(expected = TipoDeArchivoIncorrectoException.class)
	public void pasarArchivoConExtensionIncorrectaYQueExplote() throws IOException {
		
		CSVToEmpresas CSVparser = new CSVToEmpresas("empresas.json");
		CSVparser.csvFileToEmpresas();
	}
 	
	@Test
 	public void recibeCorrectamente2EmpresasDeUnArchivoCSV() throws IOException{
 		List <Empresa> empresasCargadas = parserCSV.csvFileToEmpresas();
 		assertEquals(2,empresasCargadas.size());
 		
 	}
 	
}
