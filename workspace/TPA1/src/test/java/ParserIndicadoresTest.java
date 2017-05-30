import static org.junit.Assert.*;

import Mocks.ListaEmpresasMock;
import Mocks.ListaIndicadoresMock;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.UserException;

import usuario.Empresa;
import usuario.Indicador;
public class ParserIndicadoresTest {
	
	private List<Indicador> indicadores;

	@Before
 	public void init() throws IOException {
		ListaIndicadoresMock mockListaIndicadores = new ListaIndicadoresMock ();
		mockListaIndicadores.setIndicadoresMockeados();
		indicadores = mockListaIndicadores.getIndicadoresMockeados();
 	}

	@Test
	public void FormulaSoloConSumasFuncionaCorrectamente() throws UserException{
		Indicador indicadorConFormulaSuma = indicadores.get(0);
		assertEquals(2.0,indicadorConFormulaSuma.calcular(),0);
	}
	
	@Test
	public void FormulaSoloConRestasFuncionaCorrectamente() throws UserException{
		Indicador indicadorConFormulaResta = indicadores.get(1);
		assertEquals(2.0,indicadorConFormulaResta.calcular(),0);
	}
	@Test
	public void FormulaSoloConProductosFuncionaCorrectamente() throws UserException{
		Indicador indicadorConFormulaProducto = indicadores.get(2);
		assertEquals(2.0,indicadorConFormulaProducto.calcular(),0);
	}
	@Test
	public void FormulaSoloConDivisionesFuncionaCorrectamente() throws UserException{
		Indicador indicadorConFormulaDivisiones = indicadores.get(3);
		assertEquals(2.0,indicadorConFormulaDivisiones.calcular(),0);
	}
	@Test
	public void FormulaConDistintasOperacionesFuncionaCorrectamente() throws UserException{
		Indicador indicadorConDistintasOperaciones = indicadores.get(5);
		assertEquals(10.0,indicadorConDistintasOperaciones.calcular(),0);
	}
	@Test
	public void FormulaConIndicadoresYCuentasFuncionaCorrectamente() throws UserException{
		Indicador indicadorConIndicadoresYCuentas = indicadores.get(4);
		assertEquals(0.0,indicadorConIndicadoresYCuentas.calcular(),0);
	}
}	
