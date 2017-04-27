package repository;
import usuario.*;

public class ArchivoRepository {
	
	private static String archivo;
	

	public static void setArchivo(String archivo){
		 ArchivoRepository.archivo=archivo;
	}
	
	public static String getArchivo(){
		ParserJsonAEmpresaAdapter P = new ParserJsonAEmpresaAdapter(archivo);
		return P.getArchivo();
	}
	
}
