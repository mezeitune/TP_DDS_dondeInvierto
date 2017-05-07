package repository;

import java.io.FileNotFoundException;

public class ArchivoRepository {
	
	private static String archivo;
	

	public static void setArchivo(String archivo){
		 ArchivoRepository.archivo=archivo;
	}
	
	public static String getArchivo(){
		return archivo;
	}
	
}
