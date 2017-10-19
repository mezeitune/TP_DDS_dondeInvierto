package tests;

import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

import model.Indicador;
import parserArchivos.ParserJsonString;

public class IndicadoresAlArchivoJSONTest {
	
	@Before
 	public void init()  {
 	}
	
	@Test
 	public void agregaCorrectamente1IndicadorAlArchivoJson(){
		Indicador nuevoIndicador = new Indicador("Indicador0","2+2+2+2+2");
		String nuevoIndicadorJSON = new Gson().toJson(nuevoIndicador); 
		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("indicadoresTest",nuevoIndicadorJSON);	
 	}
 	

	
}
