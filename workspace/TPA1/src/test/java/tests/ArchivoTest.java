package tests;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Empresa;
import parserArchivos.ParserCsv;

public class ArchivoTest {

	ParserCsv parserCSV;
	
	
	@Before
 	public void init() throws IOException {
		parserCSV = new ParserCsv();
 	}
	
	@Test
 	public void pasarArchivoExistenteYQueFuncioneCorrectamente()  {
		parserCSV.getEmpresas("Prueba.csv");
 	    
 	}
 	@Test
	public void pasarPathIncorrectoYQueExplote()  {
		Assert.assertEquals(false, parserCSV.esArchivoExistente("Inexistente.csv"));
	}
	
 	@Test
	public void pasarArchivoConExtensionIncorrectaYQueExplote() {
		
		Assert.assertEquals(false, parserCSV.extensionValida("empresas.json"));
	}
 	
	@Test
 	public void recibeCorrectamente2EmpresasDeUnArchivoCSV() {
 		List <Empresa> empresasCargadas = parserCSV.getEmpresas("Prueba.csv");
 		assertEquals(2,empresasCargadas.size());
 		
 	}
 	
}
