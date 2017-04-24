import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import usuario.Adapter;


public class JsonTest {
	Adapter adapter;//Adapter creado en el paquete usuario para que me devuelva la lista de empresas del archivo json
	
 	@Before
 	public void init() {
 		adapter = new Adapter("test.json");//archivo de tests
 	}
	
	

    @Test
    public void testDeInicializacionDeNombredelArchivoTestJson() {
    	
    	assertEquals("Diseno de Sistemas", adapter.getEmpresasDelArchivo().get(0).getNombre());
   
        //assertEquals("Result", result, tester.multiply(m1, m2));
    	//Assert.assertTrue(rodri + " deberia ser moroso", rodri.esMoroso)
    	
    }
	
}
