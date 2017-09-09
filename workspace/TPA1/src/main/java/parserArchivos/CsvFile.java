package parserArchivos;

public class CsvFile {

	public String nombre;
	public String directorio;
	
	public CsvFile(String directorio) {
		this.directorio = directorio;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDirectorio() {
		return this.directorio;
	}
}
