package tests;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comparadores.Comparador;
import comparadores.ComparadorMayor;
import comparadores.ComparadorMenor;
import condiciones.Comparativa;
import condiciones.Condicion;
import condiciones.Mixta;
import condiciones.Taxativa;
import condiciones.TipoCondicion;
import indicadoresPredefinidos.Antiguedad;
import mocks.EmpresasMock;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import parserIndicadores.ParserFormulaIndicador;

public class CondicionesTest {

	public List<Empresa> empresas = new LinkedList<Empresa>();
	public List<Cuenta> cuentas = new LinkedList<Cuenta>();
	public List<String> periodos = new LinkedList<String>();
	public List<Indicador> indicadores = new LinkedList<Indicador>();
	
	public static Indicador indicadorEstandar;
	public static Indicador roe;
	public static Indicador nivelDeuda;
	public static Indicador antiguedad;
	public static Indicador margen;
	
	@Before
	public void init(){
		EmpresasMock empresasMockeadas = new EmpresasMock();
		
		empresas = empresasMockeadas.getEmpresasMockeadas();
		cuentas = empresasMockeadas.getCuentasMockeadas();
		
		periodos.add("2016");
		
		indicadorEstandar = new Indicador("Indicador1","EBITDA/15");
		roe = new Indicador("ROE","Ingreso Neto-Dividendos/Capital Total");
		nivelDeuda = new Indicador ("Nivel de deuda","Activo/Pasivo");
		antiguedad = new Antiguedad();
		margen = new Indicador("Margen","Activo/Capital Total");
		
		indicadores.add(indicadorEstandar);
		indicadores.add(antiguedad);
		indicadores.add(margen);
		indicadores.add(nivelDeuda);
		indicadores.add(roe);
		
		
		ParserFormulaIndicador.mockearParserFormulaIndicador(cuentas,indicadores);
	}
	
	@Test
	public void unaCondicionComparativaOrdenaBienUnaListaDeEmpresasComparadas(){
		int peso=10;
		Comparador mayor = new ComparadorMayor();
		Comparativa estadoComparativo = new Comparativa(mayor);
		Condicion condicion = new Condicion("condicion",estadoComparativo,indicadorEstandar,peso);
		
		
		List<Empresa> listaEsperada = new LinkedList<>();
		listaEsperada.add(empresas.get(1));
		listaEsperada.add(empresas.get(3));
		listaEsperada.add(empresas.get(2));
		listaEsperada.add(empresas.get(0));
		
		
		assertEquals(listaEsperada,condicion.evaluar(empresas, periodos)); 
		
	}
	
	@Test
	public void maximizarROEComparaBienLasEmpresasEnUnUnicoPeriodo(){
		Condicion maximizarROE = new Condicion("maximizarRoe",new Comparativa(new ComparadorMayor()),roe,0);
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		
		listaEsperada.add(empresas.get(2));
		listaEsperada.add(empresas.get(0));
		listaEsperada.add(empresas.get(3));
		listaEsperada.add(empresas.get(1));
		
		assertEquals(listaEsperada,maximizarROE.evaluar(empresas,periodos));
		
	}
	
	@Test
	public void nivelDeDeudaComparaBienLasEmpresasEnUnUnicoPeriodo(){
		TipoCondicion comparativa = new Comparativa(new ComparadorMenor());
		Condicion minimizarDeuda = new Condicion("minimizarDeuda",comparativa,nivelDeuda,0);
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		listaEsperada.add(empresas.get(2));
		listaEsperada.add(empresas.get(0));
		listaEsperada.add(empresas.get(1));
		listaEsperada.add(empresas.get(3));
		
		assertEquals(listaEsperada,minimizarDeuda.evaluar(empresas,periodos));
	}
	
	
	@Test
	public void laLongevidadTaxativaDiscriminaBien(){ /*TODO: Tener en cuentas las nuevas empresas que se agregaron al Mock*/
		int peso=20;
		int anosRequeridos = 3;
		Taxativa estadoTaxativo = new Taxativa(new ComparadorMenor(),anosRequeridos);
		Condicion longevidad = new Condicion("longevidadTaxativa",estadoTaxativo,antiguedad,peso);
		
		List<Empresa> listaEsperada = longevidad.evaluar(empresas, periodos);
		
		assertEquals("Apple",listaEsperada.get(0).getNombre()); 
		
	}
	@Test
	public void laLongevidadComparativaComparaBien(){ 
		TipoCondicion comparativa = new Comparativa(new ComparadorMayor());
		Condicion longevidad = new Condicion("longevidadComparativa",comparativa,antiguedad,0);
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		listaEsperada.add(empresas.get(3));
		listaEsperada.add(empresas.get(1));
		listaEsperada.add(empresas.get(2));
		listaEsperada.add(empresas.get(0));

		assertEquals(listaEsperada,longevidad.evaluar(empresas, periodos)); 
		
	}
	
	@Test
	public void laLongevidadTaxativaDiscriminaYComparaBien(){
		int anosRequeridos = 3;
		TipoCondicion comparativa = new Comparativa(new ComparadorMayor());
		TipoCondicion taxativa = new Taxativa(new ComparadorMenor(),anosRequeridos);
		List<TipoCondicion> tiposCondiciones = new LinkedList<TipoCondicion>();
		tiposCondiciones.add(comparativa);
		tiposCondiciones.add(taxativa);
		TipoCondicion mixta = new Mixta(tiposCondiciones);
		
		Condicion longevidad = new Condicion("longevidadMixta",mixta,antiguedad,0);
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		listaEsperada.add(empresas.get(3));
		listaEsperada.add(empresas.get(1));
		listaEsperada.add(empresas.get(2));

		assertEquals(listaEsperada,longevidad.evaluar(empresas, periodos)); 
	}
	
	
	@Test
	public void losMargenesConsistenementesCrecientesComparanBienPeroParaUnSoloAno(){
		
		TipoCondicion comparativa = new Comparativa(new ComparadorMayor());
		//MargenesCrecientes margenesCrecientes = new MargenesCrecientes(comparativa,margen,0);
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		listaEsperada.add(empresas.get(1));
		listaEsperada.add(empresas.get(0));
		listaEsperada.add(empresas.get(3));
		listaEsperada.add(empresas.get(2));
		
		assertEquals(listaEsperada,new Condicion("margenesCrecientes",comparativa,margen,0).evaluar(empresas, periodos)); 
	}
	
	@After
	public void restart() {
		ParserFormulaIndicador.restart();
	}
	
	
}
