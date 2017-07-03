import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Comparadores.Comparador;
import Comparadores.ComparadorMayor;
import Comparadores.ComparadorMenor;
import Mocks.ListaEmpresasMock;
import metodologias.Condicion;
import metodologias.CondicionComparativa;
import metodologias.CondicionTaxativa;
import parserFormulaInidicador.ParserFormulaToIndicador;
import usuario.Cuenta;
import usuario.Antiguedad;
import usuario.Empresa;
import usuario.Indicador;

public class CondicionesTest {

	public List<Empresa> empresas;
	public List<Cuenta> cuentas;
	public List<String> periodos = new LinkedList<String>();
	
	@Before
	public void init(){
		empresas = new ListaEmpresasMock().mockearListaEmpresas();
		cuentas = new ListaEmpresasMock().mockearListaCuentas();
		periodos.add("2016");
		ParserFormulaToIndicador.init(cuentas);
	}
	
	@Test
	public void unaCondicionComparativaOtorgaBienElPeso(){
		int peso=10;
		Indicador indicador = new Indicador("Indicador1","EBITDA/15");
		Comparador mayor = new ComparadorMayor();
		CondicionComparativa estadoComparativo = new CondicionComparativa(mayor,peso);
		Condicion condicion = new Condicion(estadoComparativo,indicador);
		
		List<Empresa> listaEsperada = condicion.evaluar(empresas, periodos);
		Empresa empresa1 = listaEsperada.get(0);
		Empresa empresa2= listaEsperada.get(1);
		
		assertEquals(0,empresa1.getPeso());
		assertEquals(10,empresa2.getPeso()); //En el mock, se hace que la empresa 2 tenga mayor EBITDA
		
	}
	
	@Test
	public void laCondicionTaxativaDiscriminaBien(){ //Simulamos la longevidad taxativa
		int anosRequeridos = 3;
		Comparador menor = new ComparadorMenor(); 
		CondicionTaxativa estadoTaxativo = new CondicionTaxativa(menor,anosRequeridos);
		Condicion longevidad = new Condicion(estadoTaxativo,new Antiguedad());
		
		List<Empresa> listaEsperada = longevidad.evaluar(empresas, periodos);

		assertEquals("Apple",listaEsperada.get(0).getNombre()); //La unica con antiguedad mayor a 3 anos
		
	}
	
	
	
}
