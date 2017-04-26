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
 	public void init() {
 		parserJsonAEmpresaAdapter = new ParserJsonAEmpresaAdapter("test.json");//archivo de tests
 	}
	

 	
 	//Todo lo de DaSilva antes de que adapte
 	/*@Before
 	public void init() {
 		adapter = new Adapter("empresasTest.json");//archivo de tests
 	}
 	
	@Test
 	public void InicializaCorrectamente(){
			
			//  Habria que testear que lanze una excepcion si el archivo no se encuentra.
			 //  Hacer un try - catch en la linea donde se usa new FileReader() y venir aca y testear que se agarre esa excepcion.
			 
			 
 	}
 	
 	@Test
 	public void recibeCorrectamenteCantidadDeEmpresas(){
 		List <Empresa> empresasCargadas = adapter.getEmpresasDelArchivo();
 		assertEquals(2,empresasCargadas.size());
 		
 	}
 	
 	@Test
 	public void almacenaCorrectamenteCuentasEnUnaEmpresa(){
 		Empresa facebook = adapter.getEmpresasDelArchivo().get(0);

 		assertEquals(4, facebook.getCuentas().size());
 	}*/
 	
}
