import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Mocks.ListaLineasCsvFileMock;
import parserArchivos.ParserCsv;
import parserArchivos.ParserJsonAObjetosJava;
import usuario.Cuenta;
import usuario.Empresa;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ParserCSVEmpresaTest {

	ParserCsv parser;
	@Before
 	public void init() throws IOException {
 		parser = new ParserCsv("Prueba.csv");
 	}
	
	@Test
	public void filtraCorrectamentePorPeriodoALasEmpresas(){
		String periodo1 = "2015";
		String periodo2 = "2016";
		List <Empresa> listaEmpresas = new ArrayList<Empresa>();
	 	listaEmpresas= parser.CSVObjectListToEmpresasList(new ListaLineasCsvFileMock().mockearListaLineas());
	 	List<Cuenta> cuentasPeriodo1Empresa1 = listaEmpresas.get(0).getCuentasPorPeriodo(periodo1);
	 	List<Cuenta> cuentasPeriodo2Empresa1 = listaEmpresas.get(0).getCuentasPorPeriodo(periodo2);
	 	
	 	assertEquals(3,cuentasPeriodo1Empresa1.size());
	 	assertEquals(1,cuentasPeriodo2Empresa1.size());
	 	
	}
	
	@Test
	public void ListaAsignadaCorrectamenteConParser(){
		ParserJsonAObjetosJava parserMock=Mockito.mock(ParserJsonAObjetosJava.class);
		List<Empresa> someList = new ArrayList<Empresa>();

	    Mockito.doReturn(someList).when(parserMock).getEmpresasDelArchivo();

		Assert.assertEquals(someList, parserMock.getEmpresasDelArchivo());	
	}
	
	@Test
 	public void recibeCorrectamenteCantidadDeEmpresas() throws IOException {
 	   List <Empresa> listaEmpresas = new ArrayList<Empresa>();
 	   listaEmpresas= parser.CSVObjectListToEmpresasList(new ListaLineasCsvFileMock().mockearListaLineas());
 	   assertEquals(2,listaEmpresas.size());
 	}
	
	@Test
	public void filtraBienPorCuentasYNombre(){
		List <Empresa> listaEmpresasConFacebookYApple = new ArrayList<Empresa>();
	 	listaEmpresasConFacebookYApple= parser.CSVObjectListToEmpresasList(new ListaLineasCsvFileMock().mockearListaLineas());
	 	List<Cuenta> cuentasDeFacebook = listaEmpresasConFacebookYApple.get(0).getCuentas();
	 	List<Cuenta> cuentasDeApple = listaEmpresasConFacebookYApple.get(1).getCuentas();
	 	assertEquals(4,cuentasDeFacebook.size());
	 	assertEquals(5,cuentasDeApple.size());
	}
	
	
	
	
	
}
