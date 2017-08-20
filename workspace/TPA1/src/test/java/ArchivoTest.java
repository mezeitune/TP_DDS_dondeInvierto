import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parserArchivos.CSVToEmpresas;
import usuario.Empresa;

public class ArchivoTest {

	CSVToEmpresas parserCSV;
	
	@Before
 	public void init() throws IOException {
		parserCSV = new CSVToEmpresas("Prueba.csv");
 	}
	
	@Test
 	public void pasarArchivoExistenteYQueFuncioneCorrectamente()  {
 		
		CSVToEmpresas CSVparser = new CSVToEmpresas("Prueba.csv");
		CSVparser.csvFileToEmpresas();
 	    
 	}
 	@Test
	public void pasarPathIncorrectoYQueExplote()  {
		
		CSVToEmpresas parser = new CSVToEmpresas("Inexistente.csv");
		Assert.assertEquals(false, parser.esArchivoExistente("Inexistente.csv"));
	}
	
 	@Test
	public void pasarArchivoConExtensionIncorrectaYQueExplote() {
		
		CSVToEmpresas parser = new CSVToEmpresas("empresas.json");
		Assert.assertEquals(false, parser.extensionValida("empresas.json"));
	}
 	
	@Test
 	public void recibeCorrectamente2EmpresasDeUnArchivoCSV() {
 		List <Empresa> empresasCargadas = parserCSV.csvFileToEmpresas();
 		assertEquals(2,empresasCargadas.size());
 		
 	}
 	
}
