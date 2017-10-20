package repositorios;

import java.util.LinkedList;
import java.util.List;

import parserArchivos.CsvFile;

public class RepositorioArchivosCuentas {

	
	public static List<CsvFile> archivos = new LinkedList<CsvFile>();
	
	public static void addArchivo(CsvFile nuevoArchivo) {
		archivos.add(nuevoArchivo);
	}
	
	public static List<CsvFile> getArchivosCuentas(){
		return archivos;
	}

	public static boolean existenArchivosCargados() {
		return !archivos.isEmpty();
	}
}
