import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Mocks.ListaLineasCsvFileMock;
import parser.CSVToEmpresas;
import parser.ParserJsonAEmpresaAdapter;
import usuario.Cuenta;
import usuario.Empresa;


import static org.mockito.Mockito.when;   // ...or...

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;      // ...with the caveat noted below.


public class EmpresaTest {

	CSVToEmpresas parser;
	@Before
 	public void init() throws IOException {
 		parser = new CSVToEmpresas("Prueba.csv");
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
	
	
	
	
	
	//Mock con el filter por periodo (un test)
	
	
	/*@Test
	public void ListaAsignadaCorrectamenteConParser(){
		ParserJsonAEmpresaAdapter parserMock=Mockito.mock(ParserJsonAEmpresaAdapter.class);
		List<Empresa> someList = new ArrayList<Empresa>();

	    Mockito.doReturn(someList).when(parserMock).getEmpresasDelArchivo();

		Assert.assertEquals(someList, parserMock.getEmpresasDelArchivo());
		
		
	}*/
	
	
}
