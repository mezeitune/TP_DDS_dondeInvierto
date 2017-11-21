package parserArchivos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import model.Cuenta;
import model.Empresa;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParserCsv {

	
	public ParserCsv() {
	}

	private  List<CsvObjectEmpresa> CSVFileToCSVObjectList(String archivo) throws IOException{
		HeaderColumnNameMappingStrategy<CsvObjectEmpresa> strategy = new HeaderColumnNameMappingStrategy<>();
		strategy.setType(CsvObjectEmpresa.class);
		CsvToBean<CsvObjectEmpresa> lineToCSVObject = new CsvToBean<>();
		CSVReader reader = new CSVReader(new FileReader(archivo));
		List <CsvObjectEmpresa> CSVObjectList = lineToCSVObject.parse(strategy,reader);
		return CSVObjectList;
	}
	
	public List<Empresa> getEmpresas(String archivo){
		List<CsvObjectEmpresa> CSVObjectList = null;
		try {
			CSVObjectList = this.CSVFileToCSVObjectList(archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.CSVObjectListToEmpresasList(CSVObjectList);
	}
	
	public List <Empresa> CSVObjectListToEmpresasList(List <CsvObjectEmpresa> csvObjectList){
		int i=0;
		String nombreNuevaEmpresa;
		List <Empresa> empresas = new ArrayList <Empresa> ();
		
		
		Set<String> setNombreEmpresas = new HashSet<>(csvObjectList.stream().map(line -> line.getEmpresa()).collect(Collectors.toList()));
		List<String> nombreEmpresasSinRepetidos = new ArrayList<>(setNombreEmpresas);
		
		while(i < nombreEmpresasSinRepetidos.size()){
			Empresa empresa = new Empresa();
			
			nombreNuevaEmpresa=nombreEmpresasSinRepetidos.get(i);
			
			List <CsvObjectEmpresa> empresasByName = this.filtrarPorNombre(nombreNuevaEmpresa,csvObjectList);
			
			List <Cuenta> cuentasByEmpresa = this.filtrarPorCuentas(empresasByName);
			
			empresa.setCuentas(cuentasByEmpresa);
			empresa.setNombre(nombreNuevaEmpresa);
			
			empresas.add(empresa);
			i++;
		}
		return empresas;
	}
	
	
	private List <CsvObjectEmpresa> filtrarPorNombre(String empresa, List<CsvObjectEmpresa> listCSVObjects){
		List <CsvObjectEmpresa> listaFiltrada = new ArrayList<CsvObjectEmpresa> ();
		
		try{
			listaFiltrada = listCSVObjects.stream().filter(line -> line.getEmpresa().equals(empresa)).collect(Collectors.toList());
		} catch (NullPointerException e){
			e.getStackTrace();
		}
		return listaFiltrada;
	}
	
	private List <Cuenta> filtrarPorCuentas(List <CsvObjectEmpresa> empresasByName){

		return empresasByName.stream().map(line ->this.convertirACuenta(line)).collect(Collectors.toList());
	}
	
	private Cuenta convertirACuenta(CsvObjectEmpresa line){
		return new Cuenta(line.getCuenta(),line.getPeriodo(),line.getValor());
	}

	public boolean esArchivoExistente(String archivo) {
		return Files.exists(Paths.get(archivo));
	}

	public boolean extensionValida(String archivo) {
		return FilenameUtils.getExtension(archivo).equals("csv");
	}
	
}
