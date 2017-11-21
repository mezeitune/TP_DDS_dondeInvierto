package parserArchivos;

public class CsvFile {

	public String nombre;
	public String directorio;
	
	public CsvFile(String directorio) {
		this.directorio = directorio;
		String [] directorioParticionado = directorio.split("/");
		this.nombre = directorioParticionado[directorioParticionado.length - 1];
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public void setDirectorio(String directorio) {
		this.directorio=directorio;
	}
	
	public String getDirectorio() {
		return this.directorio;
	}
	
	
}
