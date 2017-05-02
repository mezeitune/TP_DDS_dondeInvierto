import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import exceptions.CSVMalFormadoException;
import parser.CSVToEmpresas;


public class TestCSV {

	CSVToEmpresas CSVparser;
	
	@Before
	public void init() throws IOException {
 		CSVparser = new CSVToEmpresas("Prueba.csv");
 	}
	
	
	
	@Test(expected = FileNotFoundException.class)
	public void pasarArchivoInexistenteYQueExplote() throws IOException {
		
		CSVparser = new CSVToEmpresas("inexistente.csv");
		CSVparser.csvFileToEmpresas();
	}

}
