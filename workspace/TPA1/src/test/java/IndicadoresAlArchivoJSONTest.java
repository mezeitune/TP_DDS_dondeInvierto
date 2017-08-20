import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

import indicadoresPredefinidos.IndicadorCustom;
import parserArchivos.ParserJsonString;
import repositorios.IndicadoresRepository;
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
 	
	@Test
	public void consultaCorrectamenteUnIndicadorEnElArchivoJSON(){
		List<Indicador> indicadores = IndicadoresRepository.getIndicadoresDefinidosPorElUsuario();;
		assertEquals("Indicador0",indicadores.get(0).getNombre());
 	}
	
}
