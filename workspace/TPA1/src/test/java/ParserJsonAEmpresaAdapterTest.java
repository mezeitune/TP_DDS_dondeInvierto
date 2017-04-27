import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import usuario.ParserJsonAEmpresaAdapter;
import usuario.Empresa;

public class ParserJsonAEmpresaAdapterTest {
	
	ParserJsonAEmpresaAdapter parserJsonAEmpresaAdapter;//Adapter creado en el paquete usuario para que me devuelva la lista de empresas del archivo json
	
 	@Before
 	public void init() throws IOException {
 		parserJsonAEmpresaAdapter = new ParserJsonAEmpresaAdapter();//archivo de tests
 		parserJsonAEmpresaAdapter.definirObjetosDelArchivo("empresasTest.json");
 	}
	

 	@Test(expected = Exception.class)
 	public void pasarArchivoInexistenteYQueExplote() throws IOException {
 		
 		ParserJsonAEmpresaAdapter parserJsonAEmpresaAdapterTest = new ParserJsonAEmpresaAdapter();
 		parserJsonAEmpresaAdapterTest.definirObjetosDelArchivo("inexistente.json");
 		
 	    
 	}
 	
 	
 	@Test
 	public void pasarArchivoExistente() throws IOException {
 		
 		ParserJsonAEmpresaAdapter parserJsonAEmpresaAdapterTest = new ParserJsonAEmpresaAdapter();
 		parserJsonAEmpresaAdapterTest.definirObjetosDelArchivo("empresas.json");
 	    
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
