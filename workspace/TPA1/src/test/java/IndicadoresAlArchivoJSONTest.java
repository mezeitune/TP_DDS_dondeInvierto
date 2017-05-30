import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

import parser.ParserFormulaToIndicador;
import parser.ParserJsonAEmpresaAdapter;
import parser.ParserJsonString;
import repository.ArchivoEIndicadoresUsuarioRepository;
import usuario.Indicador;
import usuario.IndicadorCustom;


public class IndicadoresAlArchivoJSONTest {
	
	@Before
 	public void init() throws IOException {
		ParserJsonAEmpresaAdapter parserEmpIndicador = new ParserJsonAEmpresaAdapter("indicadores.json");
		ArchivoEIndicadoresUsuarioRepository.setIndicadoresDefinidosPorElUsuario(parserEmpIndicador.getIndicadoresDelArchivo());
 	}
	
	@Test
 	public void agregaCorrectamente1IndicadorAlArchivoJson(){
		Indicador nuevoIndicador = new IndicadorCustom("Indicador0","2+2+2+2+2");
		String nuevoIndicadorJSON = new Gson().toJson(nuevoIndicador); 
		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("indicadoresTest",nuevoIndicadorJSON);	
 	}
 	
	@Test
	public void consultaCorrectamenteUnIndicadorEnElArchivoJSON(){
		List<Indicador> indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();;
		assertEquals("Indicador0",indicadores.get(0).getNombre());
 	}
	
}
