package tests;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import metodologiasPredefinidas.WarrenBuffet;
import mocks.EmpresasMock;
import model.Empresa;
import model.Metodologia;
import parserIndicadores.ParserFormulaIndicador;

public class MetodologiaTest {

	public Metodologia metodologia = new Metodologia();
	public Metodologia warrenBuffet = new WarrenBuffet();
	public static List<String> periodosAEvaluar= new LinkedList<String>();
	public static List<Empresa> empresasAEvaluar = new LinkedList<Empresa>();
	
	@Before
	public void init(){
		EmpresasMock mockEmpresas = new EmpresasMock();
		
		empresasAEvaluar = mockEmpresas.getEmpresasMockeadas();
		periodosAEvaluar.add("2016");
		
		ParserFormulaIndicador.mockearParserFormulaIndicador(mockEmpresas.getCuentasMockeadas(),warrenBuffet.getIndicadores());
	}
	
	@Test
	public void unaMetodologiaObtieneBienLasEmpresasInvertibles(){
		List<List<Empresa>> listasEmpresasEvaluadas = new LinkedList <List<Empresa>> ();
		List <Empresa>lista1 = new LinkedList <Empresa> ();
		List <Empresa>lista2 = new LinkedList <Empresa> ();
		
		Empresa empresa1 = new Empresa("Facebook");
		Empresa empresa2 = new Empresa("Apple");
		Empresa empresa3 = new Empresa("Oracle");
		Empresa empresa4= new Empresa("Genius");
		lista1.add(empresa1);
		lista1.add(empresa2);
		lista1.add(empresa3);
		lista1.add(empresa4);
		

		Empresa empresa5 = new Empresa("IBM");
		lista2.add(empresa1);
		lista2.add(empresa3);
		lista2.add(empresa5);
		
		
		listasEmpresasEvaluadas.add(lista1);
		listasEmpresasEvaluadas.add(lista2);
		
		List <Empresa>listaEsperada = new LinkedList <Empresa> ();
		listaEsperada.add(empresa1);
		listaEsperada.add(empresa3);
		
		assertEquals(listaEsperada,this.metodologia.obtenerEmpresasInvertibles(listasEmpresasEvaluadas));
	}
	
	@Test
	public void unaMetodologiaObtieneBienLasEmpresasNoInvertibles(){
		List<Empresa> conjuntoDeEmpresasAEvaluar = empresasAEvaluar;
		List<Empresa> empresasInvertibles = new LinkedList<Empresa>();
		
		empresasInvertibles.add(conjuntoDeEmpresasAEvaluar.get(0));
		empresasInvertibles.add(conjuntoDeEmpresasAEvaluar.get(3));
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		listaEsperada.add(conjuntoDeEmpresasAEvaluar.get(1));
		listaEsperada.add(conjuntoDeEmpresasAEvaluar.get(2));
		
		assertEquals(listaEsperada, this.metodologia.obtenerEmpresasNoInvertibles(empresasAEvaluar,empresasInvertibles));
	}
	
	@Test
	public void laMetodologiaWarrenBuffetObtieneCorrectamenteLasEmpresasNoInvertibles(){
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		listaEsperada.add(empresasAEvaluar.get(0));
		
		assertEquals(listaEsperada,warrenBuffet.evaluar(empresasAEvaluar,periodosAEvaluar).get(1));
	}
	
	@Test
	public void laMetodologiaWarrenBuffetObtieneCorrectamenteLasEmpresasInvertiblesOrdenadas(){
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		listaEsperada.add(empresasAEvaluar.get(1));
		listaEsperada.add(empresasAEvaluar.get(3));
		listaEsperada.add(empresasAEvaluar.get(2));
		
		assertEquals(listaEsperada,warrenBuffet.evaluar(empresasAEvaluar,periodosAEvaluar).get(0));
	}
	
	@After
	public void restart() {
		ParserFormulaIndicador.restart();
	}
}
