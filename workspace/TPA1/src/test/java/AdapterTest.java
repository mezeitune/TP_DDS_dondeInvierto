import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import usuario.Adapter;
import usuario.Empresa;

public class AdapterTest {
	
Adapter adapter;//Adapter creado en el paquete usuario para que me devuelva la lista de empresas del archivo json
	
 	@Before
 	public void init() {
 		adapter = new Adapter("empresasTest.json");//archivo de tests
 	}
 	
	@Test
 	public void InicializaCorrectamente(){
			/*
			 *  Habria que testear que lanze una excepcion si el archivo no se encuentra.
			 *  Hacer un try - catch en la linea donde se usa new FileReader() y venir aca y testear que se agarre esa excepcion.
			 * 
			 */
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
 	}
 	
}
