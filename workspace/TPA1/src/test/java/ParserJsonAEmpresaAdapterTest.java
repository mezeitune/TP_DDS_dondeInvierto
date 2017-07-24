import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import excepciones.CSVInexistenteException;
import parser.parserArchivos.ParserJsonAObjetosJava;
import usuario.Empresa;

public class ParserJsonAEmpresaAdapterTest {
	
	ParserJsonAObjetosJava parserJsonAEmpresaAdapter;
	
 	@Before
 	public void init() throws IOException {
 		parserJsonAEmpresaAdapter = new ParserJsonAObjetosJava("empresasTest.json");
 	}
	
 	
 	@Test
 	public void almacenaCorrectamente4CuentasEnUnaEmpresa(){
 		
 		Empresa facebook =  parserJsonAEmpresaAdapter.getEmpresasDelArchivo().get(0);

 		assertEquals(4, facebook.getCuentas().size());
 	}
 	
 	
 	
 
 	
}