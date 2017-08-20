import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Mocks.EmpresasMock;
import metodologias.Predefinidas.WarrenBuffet;
import parserIndicadores.ParserFormulaIndicador;
import usuario.Empresa;
import usuario.Metodologia;

public class MetodologiaTest {

	public Metodologia metodologia = new Metodologia();
	public List<String> periodosAEvaluar= new LinkedList<String>();
	public List<Empresa> empresasAEvaluar = new LinkedList<Empresa>();
	
	@Before
	public void init(){
		EmpresasMock mockEmpresas = new EmpresasMock();
		
		this.empresasAEvaluar = mockEmpresas.getEmpresasMockeadas();
		this.periodosAEvaluar.add("2016");
		
		ParserFormulaIndicador.init(mockEmpresas.getCuentasMockeadas());
		ParserFormulaIndicador.setModeTest(true); /*TODO: Desacoplarlo del archivo*/
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
		List<Empresa> conjuntoDeEmpresasAEvaluar = this.empresasAEvaluar;
		List<Empresa> empresasInvertibles = new LinkedList<Empresa>();
		
		empresasInvertibles.add(conjuntoDeEmpresasAEvaluar.get(0));
		empresasInvertibles.add(conjuntoDeEmpresasAEvaluar.get(3));
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		listaEsperada.add(conjuntoDeEmpresasAEvaluar.get(1));
		listaEsperada.add(conjuntoDeEmpresasAEvaluar.get(2));
		
		assertEquals(listaEsperada, this.metodologia.obtenerEmpresasNoInvertibles(this.empresasAEvaluar,empresasInvertibles));
	}
	
	@Test
	public void laMetodologiaWarrenBuffetObtieneCorrectamenteLasEmpresasNoInvertibles(){
		WarrenBuffet warrenBuffet = new WarrenBuffet();
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		listaEsperada.add(this.empresasAEvaluar.get(0));
		
		assertEquals(listaEsperada,warrenBuffet.evaluar(this.empresasAEvaluar,this.periodosAEvaluar).get(1));
	}
	
	@Test
	public void laMetodologiaWarrenBuffetObtieneCorrectamenteLasEmpresasInvertiblesOrdenadas(){
		WarrenBuffet warrenBuffet = new WarrenBuffet();
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		
		listaEsperada.add(this.empresasAEvaluar.get(1));
		listaEsperada.add(this.empresasAEvaluar.get(3));
		listaEsperada.add(this.empresasAEvaluar.get(2));
		
		assertEquals(listaEsperada,warrenBuffet.evaluar(this.empresasAEvaluar,this.periodosAEvaluar).get(0));
	}
}
