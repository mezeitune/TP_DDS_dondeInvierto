package repository;
import usuario.Empresa;
import usuario.Cuenta;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import parserArchivos.CSVToEmpresas;

public class EmpresasRepository {
	
	private static String archivo;

	public static List<Empresa> getEmpresas(){
		return new CSVToEmpresas(archivo).csvFileToEmpresas();
	}
	
	public static List<String> getNombreCuentas() {
		Set<String> unSetNombreCuentas = new HashSet<String> (EmpresasRepository.getAllCuentas()
																				.stream().map(cuenta -> cuenta.getNombre())
																				.collect(Collectors.toList()));
		List<String> nombreCuentasSinRepetidos = new LinkedList<String> (unSetNombreCuentas);
		return nombreCuentasSinRepetidos;
	}
	
	public static List<Cuenta> getAllCuentas(){
		List<Cuenta> cuentas = new LinkedList<Cuenta>();
		EmpresasRepository.getEmpresas().stream().forEach(empresa -> empresa.getCuentas().
																			 stream().forEach(cuenta -> cuentas.add(cuenta)));
		System.out.println(cuentas);
		return cuentas;
	}
	

	public static void setArchivo(String archivoEmpresas) {
		archivo = archivoEmpresas;
	}
	
	public static String getArchivo(){
		return archivo;
	}

}
