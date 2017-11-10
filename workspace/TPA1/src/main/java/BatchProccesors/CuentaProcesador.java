package BatchProccesors;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.Empresa;
import parserArchivos.ParserCsv;
import repositorios.RepositorioEmpresas;

public class CuentaProcesador {

	private RepositorioEmpresas repositorioEmpresas = new RepositorioEmpresas();
	private ParserCsv parser = new ParserCsv();
	private static File folder;
	
	public CuentaProcesador(String directorio)
	{
		folder= new File(directorio);
	}
		
		public void cargarCuentas() {
			List<Empresa> empresas = getArchivos(folder).stream()
														.map(archivo -> parser.getEmpresas(archivo.getAbsolutePath()))
														.flatMap(empresas_mapeadas -> empresas_mapeadas.stream())
														.distinct()
														.collect(Collectors.toList());
		
		repositorioEmpresas.agregarEmpresas(empresas);
		System.out.println("Empresas y cuentas cargadas");
		}
	
	
	  private static List<File> getArchivos(final File folder) {
		  List<File> archivos = new LinkedList<File>();
		  String temp="";
	    for (final File fileEntry : folder.listFiles()) {
	    	if (fileEntry.isDirectory()) getArchivos(fileEntry);
	        else if(fileEntry.isFile()) {
	        
	          temp = fileEntry.getName();
	          if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("csv"))
	            archivos.add(fileEntry);
	        }

	      }
	    return archivos;
	  }
	  
	  public static void main(String[] args) {
			new CuentaProcesador("C:/Users/Manu/Documents/GitHub/2017-jm-group-11/workspace/TPA1/src/main/resources/Cuentas").cargarCuentas();
		}
		
	  
	   

	
	
}
