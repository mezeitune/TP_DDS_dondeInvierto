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
}
