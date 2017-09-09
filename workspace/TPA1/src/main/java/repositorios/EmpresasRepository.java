package repositorios;
import usuario.Empresa;
import usuario.Cuenta;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import parserArchivos.CsvFile;
import parserArchivos.ParserCsv;

public class EmpresasRepository {
	

	public static List<Empresa> getEmpresas(){
		List<Empresa> empresas = new LinkedList<Empresa>();
		ArchivosCuentasRepository.getArchivosCuentas().forEach(file -> EmpresasRepository.concatenarEmpresas(file,empresas));
		return empresas;
	}
	
	public static void concatenarEmpresas(CsvFile archivoCuentas,List<Empresa> empresas) {
		ParserCsv parser = new ParserCsv();
		parser.csvFileToEmpresas(archivoCuentas.getDirectorio()).forEach(empresa -> empresas.add(empresa));
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
		return cuentas;
	}

}
