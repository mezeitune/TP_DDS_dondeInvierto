package repositorios;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import model.Cuenta;
import model.Empresa;
import parserArchivos.CsvFile;
import parserArchivos.ParserCsv;

public class EmpresasRepository extends DBRelacionalRepository<Empresa> {
	
	
	
	public EmpresasRepository() {
	}
	
	public List<Empresa> getEmpresas(){
		return entityManager().createQuery("from Empresa", Empresa.class).getResultList();
	}
	
	public static void concatenarEmpresas(CsvFile archivoCuentas,List<Empresa> empresas) {
		ParserCsv parser = new ParserCsv();
		parser.csvFileToEmpresas(archivoCuentas.getDirectorio()).forEach(empresa -> empresas.add(empresa));
	}
	
	public List<String> getNombreCuentas() {
		Set<String> unSetNombreCuentas = new HashSet<String> (this.getAllCuentas()
																				.stream().map(cuenta -> cuenta.getNombre())
																				.collect(Collectors.toList()));
		List<String> nombreCuentasSinRepetidos = new LinkedList<String> (unSetNombreCuentas);
		return nombreCuentasSinRepetidos;
	}
	
	public List<Cuenta> getAllCuentas(){
		List<Cuenta> cuentas = new LinkedList<Cuenta>();
		this.getEmpresas().stream().forEach(empresa -> empresa.getCuentas().
																			 stream().forEach(cuenta -> cuentas.add(cuenta)));
		return cuentas;
	}


	
	public Map<String, Object> getHashMapPeriodos(){

		List<Empresa> empresas = new LinkedList<Empresa>();
		List<List<Cuenta>> cuentasPorEmpresa = new LinkedList<List<Cuenta>>();
		List<List<String>> periodos = new LinkedList<List<String>>();
		

		empresas = this.getEmpresas();

		cuentasPorEmpresa = empresas.stream().map(e -> e.getCuentas()).collect(Collectors.toList());

		
		periodos = cuentasPorEmpresa.stream().map(cuentasPorEmp -> cuentasPorEmp.stream().map(cuenta -> cuenta.getPeriodo()).collect(Collectors.toList())).collect(Collectors.toList());

		periodos = periodos.stream().map(p -> p.stream().distinct().collect(Collectors.toList())).collect(Collectors.toList());
		Map<String, Object> diccionarioPeriodos = new HashMap<String, Object>();
		
		
		for (int i = 0; i < periodos.size(); i++) {
			diccionarioPeriodos.put(empresas.get(i).getNombre(), periodos.get(i));
		}
		return diccionarioPeriodos;
		
	}
	
	public Empresa getEmpresa(String nombreEmpresa){
		return this.getEmpresas().stream().filter(empresa -> empresa.getNombre().equals(nombreEmpresa)).collect(Collectors.toList()).get(0);
		
	}

}
