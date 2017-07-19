import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import usuario.Empresa;
import usuario.Metodologia;

public class MetodologiaTest {

	public Metodologia metodologia = new Metodologia();
	
	@Before
	public void init(){
		List<Empresa> conjuntoDeEmpresasAEvaluar = new LinkedList<Empresa>();
		Empresa empresa1 = new Empresa("Facebook");
		Empresa empresa2 = new Empresa("Apple");
		Empresa empresa3 = new Empresa("Oracle");
		Empresa empresa4 = new Empresa("Genius");
		Empresa empresa5 = new Empresa("IBM");
		conjuntoDeEmpresasAEvaluar.add(empresa1);
		conjuntoDeEmpresasAEvaluar.add(empresa2);
		conjuntoDeEmpresasAEvaluar.add(empresa3);
		conjuntoDeEmpresasAEvaluar.add(empresa4);
		conjuntoDeEmpresasAEvaluar.add(empresa5);
		metodologia.setEmpresasAEvaluar(conjuntoDeEmpresasAEvaluar);
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
		
		Assert.assertEquals(listaEsperada,this.metodologia.obtenerEmpresasInvertibles(listasEmpresasEvaluadas));
	}
	
	@Test
	public void unaMetodologiaObtieneBienLasEmpresasNoInvertibles(){
		List<Empresa> conjuntoDeEmpresasAEvaluar = this.metodologia.getConjuntoDeEmpresasAEvaluar();
		List<Empresa> empresasInvertibles = new LinkedList<Empresa>();
		
		empresasInvertibles.add(conjuntoDeEmpresasAEvaluar.get(0));
		empresasInvertibles.add(conjuntoDeEmpresasAEvaluar.get(3));
		
		List<Empresa> listaEsperada = new LinkedList<Empresa>();
		listaEsperada.add(conjuntoDeEmpresasAEvaluar.get(1));
		listaEsperada.add(conjuntoDeEmpresasAEvaluar.get(2));
		listaEsperada.add(conjuntoDeEmpresasAEvaluar.get(4));
		
		Assert.assertEquals(listaEsperada, this.metodologia.obtenerEmpresasNoInvertibles(empresasInvertibles));
		
	}
}
