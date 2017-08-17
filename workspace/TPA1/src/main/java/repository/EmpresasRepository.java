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

	/*TODO: Desacoplarlo del repository de archivosIndicadores*/
	public static List<Empresa> getEmpresas(){
		return new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo()).csvFileToEmpresas();
	}

	public static List<String> getNombreCuentas() {
		Set<String> unSetNombreCuentas = new HashSet<String> (EmpresasRepository.getAllCuentas()
																				.stream().map(cuenta -> cuenta.getNombre())
																				.collect(Collectors.toList()));
		List<String> nombreCuentasSinRepetidos = new LinkedList<String> (unSetNombreCuentas);
		return nombreCuentasSinRepetidos;
	}
	
	public static List<Cuenta> getAllCuentas(){
		List<Empresa> empresas = EmpresasRepository.getEmpresas();
		List<Cuenta> cuentas = new LinkedList<Cuenta>();
		empresas.stream().forEach(empresa -> EmpresasRepository.agregarCuentas(cuentas,empresa.getCuentas()));
		return cuentas;
	}
	
	
	private static void agregarCuentas(List<Cuenta> cuentas, List<Cuenta> cuentasEmpresa) {
		cuentasEmpresa.forEach(cuenta -> cuentas.add(cuenta));
	}

}
