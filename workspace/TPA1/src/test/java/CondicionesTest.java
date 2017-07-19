import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Comparadores.Comparador;
import Comparadores.ComparadorMayor;
import Comparadores.ComparadorMenor;
import Condiciones.Comparativa;
import Condiciones.Condicion;
import Condiciones.Taxativa;
import Condiciones.TipoCondicion;
import Mocks.ListaEmpresasMock;
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
	public void unaCondicionComparativaOrdenaBienUnaListaDeEmpresasComparadas(){
		int peso=10;
		Indicador indicador = new Indicador("Indicador1","EBITDA/15");
		Comparador mayor = new ComparadorMayor();
		Comparativa estadoComparativo = new Comparativa(mayor);
		Condicion condicion = new Condicion(estadoComparativo,indicador,peso);
		
		
		List<Empresa> listaEsperada = new LinkedList<>();
		listaEsperada.add(empresas.get(1));
		listaEsperada.add(empresas.get(3));
		listaEsperada.add(empresas.get(2));
		listaEsperada.add(empresas.get(0));
		
		
		assertEquals(listaEsperada,condicion.evaluar(empresas, periodos)); 
		
	}
	
	@Test
	public void laCondicionTaxativaDiscriminaBien(){ //Simulamos la longevidad taxativa
		int peso=20;
		int anosRequeridos = 3;
		Comparador menor = new ComparadorMenor(); 
		Taxativa estadoTaxativo = new Taxativa(menor,anosRequeridos);
		Condicion longevidad = new Condicion(estadoTaxativo,new Antiguedad(),peso);
		
		List<Empresa> listaEsperada = longevidad.evaluar(empresas, periodos);

		assertEquals("Apple",listaEsperada.get(0).getNombre()); //La unica con antiguedad mayor a 3 anos
		
	}
	
	@Test
	public void maximizarROEComparaBienLasEmpresas(){
		Indicador roe = new Indicador("ROE","Ingreso Neto-Dividendos/Capital Total");
		TipoCondicion comparativa = new Comparativa(new ComparadorMayor());
		Condicion maximizarROE = new Condicion(comparativa,roe,0);
		
		
		
	}
	
	
	
	
	
}
