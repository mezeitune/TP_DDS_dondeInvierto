import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import Comparadores.Comparador;
import Comparadores.ComparadorMayor;
import Comparadores.ComparadorMenor;
import Condiciones.Comparativa;
import Condiciones.Condicion;
import Condiciones.Mixta;
import Condiciones.Taxativa;
import Condiciones.TipoCondicion;
import Mocks.EmpresasMock;
import indicadoresPredefinidos.Antiguedad;
import parserIndicadores.ParserFormulaIndicador;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.Indicador;

public class CondicionesTest {

	public List<Empresa> empresas = new LinkedList<Empresa>();
	public List<Cuenta> cuentas = new LinkedList<Cuenta>();
	public List<String> periodos = new LinkedList<String>();
	
	@Before
	public void init(){
		EmpresasMock listasEmpresasMockeadas = new EmpresasMock();
		
		empresas = listasEmpresasMockeadas.getEmpresasMockeadas();
		cuentas = listasEmpresasMockeadas.getCuentasMockeadas();
		periodos.add("2016");
		ParserFormulaIndicador.init(cuentas);
	}
	
	@Test
	public void unaCondicionComparativaOrdenaBienUnaListaDeEmpresasComparadas(){
		int peso=10;
		Indicador indicador = new Indicador("Indicador1","EBITDA/15");
		Comparador mayor = new ComparadorMayor();
		Comparativa estadoComparativo = new Comparativa(mayor);
		Condicion condicion = new Condicion("condicion",estadoComparativo,indicador,peso);
		
		
		List<Empresa> listaEsperada = new LinkedList<>();
		listaEsperada.add(empresas.get(1));
		listaEsperada.add(empresas.get(3));
		listaEsperada.add(empresas.get(2));
		listaEsperada.add(empresas.get(0));
		
		
		assertEquals(listaEsperada,condicion.evaluar(empresas, periodos)); 
		
	}
	
	@Test
	public void maximizarROEComparaBienLasEmpresasEnUnUnicoPeriodo(){
		Indicador roe = new Indicador("ROE","Ingreso Neto-Dividendos/Capital Total");
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
		Indicador nivelDeuda = new Indicador ("Nivel de deuda","Activo/Pasivo");
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
		Condicion longevidad = new Condicion("longevidadTaxativa",estadoTaxativo,new Antiguedad(),peso);
		
		List<Empresa> listaEsperada = longevidad.evaluar(empresas, periodos);
		
		assertEquals("Apple",listaEsperada.get(0).getNombre()); 
		
	}
	@Test
	public void laLongevidadComparativaComparaBien(){ 
		TipoCondicion comparativa = new Comparativa(new ComparadorMayor());
		Condicion longevidad = new Condicion("longevidadComparativa",comparativa,new Antiguedad(),0);
		
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
		
		Condicion longevidad = new Condicion("longevidadMixta",mixta,new Antiguedad(),0);
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		listaEsperada.add(empresas.get(3));
		listaEsperada.add(empresas.get(1));
		listaEsperada.add(empresas.get(2));

		assertEquals(listaEsperada,longevidad.evaluar(empresas, periodos)); 
	}
	
	
	@Test
	public void losMargenesConsistenementesCrecientesComparanBienPeroParaUnSoloAno(){
		Indicador margen = new Indicador("Margen","Activo/Capital Total");
		TipoCondicion comparativa = new Comparativa(new ComparadorMayor());
		//MargenesCrecientes margenesCrecientes = new MargenesCrecientes(comparativa,margen,0);
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		listaEsperada.add(empresas.get(1));
		listaEsperada.add(empresas.get(0));
		listaEsperada.add(empresas.get(3));
		listaEsperada.add(empresas.get(2));
		
		assertEquals(listaEsperada,new Condicion("margenesCrecientes",comparativa,margen,0).evaluar(empresas, periodos)); 
	}
	
	
	
}
