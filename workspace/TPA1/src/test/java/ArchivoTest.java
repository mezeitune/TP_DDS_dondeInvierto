import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parserArchivos.ParserCsv;
import usuario.Empresa;

public class ArchivoTest {

	ParserCsv parserCSV;
	
	@Before
 	public void init() throws IOException {
		parserCSV = new ParserCsv("Prueba.csv");
 	}
	
	@Test
 	public void pasarArchivoExistenteYQueFuncioneCorrectamente()  {
 		
		ParserCsv CSVparser = new ParserCsv("Prueba.csv");
		CSVparser.csvFileToEmpresas();
 	    
 	}
 	@Test
	public void pasarPathIncorrectoYQueExplote()  {
		
		ParserCsv parser = new ParserCsv("Inexistente.csv");
		Assert.assertEquals(false, parser.esArchivoExistente("Inexistente.csv"));
	}
	
 	@Test
	public void pasarArchivoConExtensionIncorrectaYQueExplote() {
		
		ParserCsv parser = new ParserCsv("empresas.json");
		Assert.assertEquals(false, parser.extensionValida("empresas.json"));
	}
 	
	@Test
 	public void recibeCorrectamente2EmpresasDeUnArchivoCSV() {
 		List <Empresa> empresasCargadas = parserCSV.csvFileToEmpresas();
 		assertEquals(2,empresasCargadas.size());
 		
 	}
 	
}
