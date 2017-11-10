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
		// TODO Auto-generated constructor stub
	}

	public  List<CsvEmpresa> CSVFileToCSVObjectList(String archivo) throws IOException{
		HeaderColumnNameMappingStrategy<CsvEmpresa> strategy = new HeaderColumnNameMappingStrategy<>();
		strategy.setType(CsvEmpresa.class);
		CsvToBean<CsvEmpresa> lineToCSVObject = new CsvToBean<>();
		CSVReader reader = new CSVReader(new FileReader(archivo));
		List <CsvEmpresa> CSVObjectList = lineToCSVObject.parse(strategy,reader);
		return CSVObjectList;
	}
	
	public List<Empresa> getEmpresas(String archivo){
		List<CsvEmpresa> CSVObjectList = null;
		try {
			CSVObjectList = this.CSVFileToCSVObjectList(archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.CSVObjectListToEmpresasList(CSVObjectList);
	}
	
	public List <Empresa> CSVObjectListToEmpresasList(List <CsvEmpresa> csvObjectList){
		int i=0;
		String nombreNuevaEmpresa;
		List <Empresa> empresas = new ArrayList <Empresa> ();
		
		
		Set<String> setNombreEmpresas = new HashSet<>(csvObjectList.stream().map(line -> line.getEmpresa()).collect(Collectors.toList()));
		List<String> nombreEmpresasSinRepetidos = new ArrayList<>(setNombreEmpresas);
		
		while(i < nombreEmpresasSinRepetidos.size()){
			Empresa empresa = new Empresa();
			
			nombreNuevaEmpresa=nombreEmpresasSinRepetidos.get(i);
			
			List <CsvEmpresa> empresasByName = this.filtrarPorNombre(nombreNuevaEmpresa,csvObjectList);
			
			List <Cuenta> cuentasByEmpresa = this.filtrarPorCuentas(empresasByName);
			
			empresa.setCuentas(cuentasByEmpresa);
			empresa.setNombre(nombreNuevaEmpresa);
			
			empresas.add(empresa/*new Empresa(nombreNuevaEmpresa,cuentasByEmpresa)*/);
			i++;
		}
		return empresas;
	}
	
	
	public List <CsvEmpresa> filtrarPorNombre(String empresa, List<CsvEmpresa> listCSVObjects){
		List <CsvEmpresa> listaFiltrada = new ArrayList<CsvEmpresa> ();
		
		try{
			listaFiltrada = listCSVObjects.stream().filter(line -> line.getEmpresa().equals(empresa)).collect(Collectors.toList());
		} catch (NullPointerException e){
			e.getStackTrace();
		}
		return listaFiltrada;
	}
	
	public List <Cuenta> filtrarPorCuentas(List <CsvEmpresa> empresasByName){

		return empresasByName.stream().map(line ->this.convertirACuenta(line)).collect(Collectors.toList());
	}
	
	public Cuenta convertirACuenta(CsvEmpresa line){
		return new Cuenta(line.getCuenta(),line.getPeriodo(),line.getValor());
	}

	public boolean esArchivoExistente(String archivo) {
		return Files.exists(Paths.get(archivo));
	}

	public boolean extensionValida(String archivo) {
		return FilenameUtils.getExtension(archivo).equals("csv");
	}
	
}
