package repository;
import java.io.IOException;

import parser.CSVToEmpresas;
import parser.ParserJsonAEmpresaAdapter;
import usuario.*;

public class ArchivoRepository {
	
	private static String archivo;
	

	public static void setArchivo(String archivo){
		 ArchivoRepository.archivo=archivo;
	}
	
	public static String getArchivo(){
		CSVToEmpresas P = new CSVToEmpresas(archivo);
		return archivo;
	}
	
}
