package tests;
import static org.junit.Assert.*;

import parserIndicadores.ParserFormulaIndicador;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.UserException;

import mocks.EmpresasMock;
import mocks.IndicadoresMock;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
public class ParserIndicadoresTest {
	
	public static List<Indicador> indicadoresMockeados;
	public static List<Cuenta> cuentasMockeadas;
	public static Empresa empresa;
	public static String periodo;
	public static ParserFormulaIndicador parser;

	@Before
 	public void init()  {
		
		indicadoresMockeados = new IndicadoresMock().getIndicadoresMockeados();
		cuentasMockeadas = new EmpresasMock().getCuentasMockeadas();
		
		empresa = new EmpresasMock().getEmpresasMockeadas().get(0);
		periodo = "2016";
		
		ParserFormulaIndicador.mockearParserFormulaIndicador(cuentasMockeadas,indicadoresMockeados);
		parser = ParserFormulaIndicador.getInstance();
 	}

	@Test
	public void FormulaSoloConSumasFuncionaCorrectamente() throws UserException{
		Indicador indicadorConFormulaSuma = indicadoresMockeados.get(0);
		indicadorConFormulaSuma.construirOperadorRaiz();
		assertEquals(2.0,indicadorConFormulaSuma.calcular(),0);
	}
	
	@Test
	public void FormulaSoloConRestasFuncionaCorrectamente() throws UserException{
		Indicador indicadorConFormulaResta = indicadoresMockeados.get(1);
		indicadorConFormulaResta.construirOperadorRaiz();
		assertEquals(2.0,indicadorConFormulaResta.calcular(),0);
	}
	@Test
	public void FormulaSoloConProductosFuncionaCorrectamente() throws UserException{
		Indicador indicadorConFormulaProducto = indicadoresMockeados.get(2);
		indicadorConFormulaProducto.construirOperadorRaiz();
		assertEquals(2.0,indicadorConFormulaProducto.calcular(),0);
	}
	@Test
	public void FormulaSoloConDivisionesFuncionaCorrectamente() throws UserException{
		Indicador indicadorConFormulaDivisiones = indicadoresMockeados.get(3);
		indicadorConFormulaDivisiones.construirOperadorRaiz();
		assertEquals(2.0,indicadorConFormulaDivisiones.calcular(),0);
	}
	@Test
	public void FormulaDeNumerosConDistintasOperacionesFuncionaCorrectamente() {
		Indicador indicadorConDistintasOperaciones = indicadoresMockeados.get(5);
		indicadorConDistintasOperaciones.construirOperadorRaiz();
		assertEquals(-1.0,indicadorConDistintasOperaciones.calcular(),0);
	}
	@Test
	public void FormulaCombinadaDeOperadoresConIndicadoresCuentasYNumeros() {
		Indicador indicadorConDistintasOperaciones = indicadoresMockeados.get(6);
		indicadorConDistintasOperaciones.construirOperadorRaiz(empresa,periodo);
		assertEquals(198.0,indicadorConDistintasOperaciones.calcular(),0);
	}
	
	@After
	public void restart(){
		ParserFormulaIndicador.restart();
	}
	
}	
