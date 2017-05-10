import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Mocks.ListaLineasCsvFileMock;
import exceptions.ArchivoInexistenteException;
import exceptions.CSVInexistenteException;
import exceptions.PathIncorrectoException;
import exceptions.TipoDeArchivoIncorrectoException;
import parser.CSVToEmpresas;
import parser.ParserJsonAEmpresaAdapter;
import usuario.Cuenta;
import usuario.Empresa;

public class ParserCSVFileToEmpresasTest {

	CSVToEmpresas parser;
	
	@Before
 	public void init() throws IOException {
 		parser = new CSVToEmpresas("Prueba.csv"); //Solo cargo un archivo porque me lo pide el constructor
 	}
	
	
	@Test
 	public void recibeCorrectamenteCantidadDeEmpresas() throws IOException {
 	   List <Empresa> listaEmpresas = new ArrayList<Empresa>();
 	   listaEmpresas= parser.CSVObjectListToEmpresasList(new ListaLineasCsvFileMock().mockearListaLineas());
 	   assertEquals(2,listaEmpresas.size());
 	}
	
	@Test
	public void filtraBienPorCuentas(){
		List <Empresa> listaEmpresas = new ArrayList<Empresa>();
	 	listaEmpresas= parser.CSVObjectListToEmpresasList(new ListaLineasCsvFileMock().mockearListaLineas());
	 	List<Cuenta> cuentasEmpresa1 = listaEmpresas.get(0).getCuentas();
	 	List<Cuenta> cuentasEmpresa2 = listaEmpresas.get(1).getCuentas();
	 	assertEquals(4,cuentasEmpresa1.size());
	 	assertEquals(5,cuentasEmpresa2.size());
	}
	

	@Test
	public void archivoCSVMalRealizadoEntoncesLaListaDeEmpresasTieneDatosNulos() throws IOException{
		
		CSVToEmpresas CSVparser = new CSVToEmpresas("PruebaFalla.csv");
		
		assertEquals( null , CSVparser.csvFileToEmpresas().get(0).getNombre());
		assertEquals( null , CSVparser.csvFileToEmpresas().get(0).getCuentas().get(0).getNombre());
		assertEquals( null , CSVparser.csvFileToEmpresas().get(0).getCuentas().get(0).getPeriodo());
		assertEquals( 0 , CSVparser.csvFileToEmpresas().get(0).getCuentas().get(0).getValor());
		
	}
	
	@Test(expected = PathIncorrectoException.class)
	public void pasarPathIncorrectoYQueExplote() throws IOException {
		
		CSVToEmpresas CSVparser = new CSVToEmpresas("Inexistente.csv");
		CSVparser.csvFileToEmpresas();
	}
	
	@Test(expected = ArchivoInexistenteException.class)
	public void pasarArchivoInexistenteYQueExplote() throws IOException {
		
		CSVToEmpresas CSVparser = new CSVToEmpresas(null);
		CSVparser.csvFileToEmpresas();
	}
	
	@Test(expected = TipoDeArchivoIncorrectoException.class)
	public void pasarArchivoConExtensionIncorrectaYQueExplote() throws IOException {
		
		CSVToEmpresas CSVparser = new CSVToEmpresas("empresas.json");
		CSVparser.csvFileToEmpresas();
	}
	
	
	
}
