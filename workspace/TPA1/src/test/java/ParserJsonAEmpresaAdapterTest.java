import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.CSVMalFormadoException;
import parser.ParserJsonAEmpresaAdapter;
import usuario.Empresa;

public class ParserJsonAEmpresaAdapterTest {
	
	ParserJsonAEmpresaAdapter parserJsonAEmpresaAdapter;//Adapter creado en el paquete usuario para que me devuelva la lista de empresas del archivo json
	
 	@Before
 	public void init() throws IOException {
 		parserJsonAEmpresaAdapter = new ParserJsonAEmpresaAdapter("empresasTest.json");
 	}
	

 	@Test(expected = CSVMalFormadoException.class)
 	public void pasarArchivoInexistenteYQueExplote() throws IOException {
 		
 		ParserJsonAEmpresaAdapter parserJsonAEmpresaAdapterTest = new ParserJsonAEmpresaAdapter("inexistente.json");
 		parserJsonAEmpresaAdapterTest.getEmpresasDelArchivo();
 		
 	    
 	}
 	
 	
 	@Test
 	public void pasarArchivoExistente() throws IOException {
 		
 		ParserJsonAEmpresaAdapter parserJsonAEmpresaAdapterTest = new ParserJsonAEmpresaAdapter("empresas.json");
 		parserJsonAEmpresaAdapterTest.getEmpresasDelArchivo();
 	    
 	}
 	
 	@Test
 	public void recibeCorrectamenteCantidadDeEmpresas(){
 		List <Empresa> empresasCargadas = parserJsonAEmpresaAdapter.getEmpresasDelArchivo();
 		assertEquals(2,empresasCargadas.size());
 		
 	}
 	
 	
 	@Test
 	public void almacenaCorrectamenteCuentasEnUnaEmpresa(){
 		
 		Empresa facebook =  parserJsonAEmpresaAdapter.getEmpresasDelArchivo().get(0);

 		assertEquals(4, facebook.getCuentas().size());
 	}
 	
 
 	
}
