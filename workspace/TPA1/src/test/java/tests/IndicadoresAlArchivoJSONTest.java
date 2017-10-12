package tests;

import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

import indicadoresPredefinidos.IndicadorCustom;
import parserArchivos.ParserJsonString;
import usuario.Indicador;


public class IndicadoresAlArchivoJSONTest {
	
	@Before
 	public void init()  {
 	}
	
	@Test
 	public void agregaCorrectamente1IndicadorAlArchivoJson(){
		Indicador nuevoIndicador = new IndicadorCustom("Indicador0","2+2+2+2+2");
		String nuevoIndicadorJSON = new Gson().toJson(nuevoIndicador); 
		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("indicadoresTest",nuevoIndicadorJSON);	
 	}
 	

	
}
