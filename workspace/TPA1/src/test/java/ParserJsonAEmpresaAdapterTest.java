import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.CSVInexistenteException;
import parser.ParserJsonAEmpresaAdapter;
import usuario.Empresa;

public class ParserJsonAEmpresaAdapterTest {
	
	ParserJsonAEmpresaAdapter parserJsonAEmpresaAdapter;
	
 	@Before
 	public void init() throws IOException {
 		parserJsonAEmpresaAdapter = new ParserJsonAEmpresaAdapter("empresasTest.json");
 	}
	
 	@Test(expected = CSVInexistenteException.class)
 	public void pasarArchivoInexistenteYQueExplote() throws IOException {
 		
 		ParserJsonAEmpresaAdapter parserJsonAEmpresaAdapterTest = new ParserJsonAEmpresaAdapter("inexistente.json");
 		parserJsonAEmpresaAdapterTest.getEmpresasDelArchivo();
 	}
 	
 	
 	@Test
 	public void pasarArchivoExistenteYQueFuncioneCorrectamente() throws IOException {
 		
 		ParserJsonAEmpresaAdapter parserJsonAEmpresaAdapterTest = new ParserJsonAEmpresaAdapter("empresas.json");
 		parserJsonAEmpresaAdapterTest.getEmpresasDelArchivo();
 	    
 	}
 	
 	@Test
 	public void recibeCorrectamente2Empresas(){
 		List <Empresa> empresasCargadas = parserJsonAEmpresaAdapter.getEmpresasDelArchivo();
 		assertEquals(2,empresasCargadas.size());
 		
 	}
 	
 	
 	@Test
 	public void almacenaCorrectamente4CuentasEnUnaEmpresa(){
 		
 		Empresa facebook =  parserJsonAEmpresaAdapter.getEmpresasDelArchivo().get(0);

 		assertEquals(4, facebook.getCuentas().size());
 	}
 	
 
 	
}