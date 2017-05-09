package parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import exceptions.CSVMalFormadoException;

import java.io.FileReader;
import java.io.IOException;

import usuario.Cuenta;
import usuario.Empresa;

public class CSVToEmpresas {

	private  String archivo;
	public CSVToEmpresas(String archivo){
		this.archivo=archivo;
	}
	
	public  List<CSVObject> csvFileToCSVObject() throws IOException{
		HeaderColumnNameMappingStrategy<CSVObject> strategy = new HeaderColumnNameMappingStrategy<>();
		strategy.setType(CSVObject.class);
		CsvToBean<CSVObject> csvToCSVObject = new CsvToBean<>();
		CSVReader reader = new CSVReader(new FileReader(archivo));
		List <CSVObject> CSVObjectList = csvToCSVObject.parse(strategy,reader);
		return CSVObjectList;
	}
	
	
	public List<Empresa> csvFileToEmpresas() throws IOException{
		int i=0;
		String nombreNuevaEmpresa;
		List <Empresa> empresas = new ArrayList <Empresa> ();
		List<CSVObject> CSVObjectList = this.csvFileToCSVObject();
		
		Set<String> setNombreEmpresas = new HashSet<>(CSVObjectList.stream().map(line -> line.getEmpresa()).collect(Collectors.toList()));
		List<String> nombreEmpresasSinRepetidos = new ArrayList<>(setNombreEmpresas);
		
		while(i < nombreEmpresasSinRepetidos.size()){
			
			nombreNuevaEmpresa=nombreEmpresasSinRepetidos.get(i);
			
			List <CSVObject> empresasByName = this.filtrarPorNombre(nombreNuevaEmpresa,CSVObjectList);
			
			List <Cuenta> cuentasByEmpresa = this.filtrarPorCuentas(empresasByName);
			
			empresas.add(new Empresa(nombreNuevaEmpresa,cuentasByEmpresa));
			i++;
		}
		return empresas;
	}
	
	
	public List <CSVObject> filtrarPorNombre(String empresa, List<CSVObject> listCSVObjects){
		List <CSVObject> listaFiltrada = new ArrayList<CSVObject> ();
		
		try{
			listaFiltrada = listCSVObjects.stream().filter(line -> line.getEmpresa().equals(empresa)).collect(Collectors.toList());
		} catch (NullPointerException e){
			e.getStackTrace();
		}
		return listaFiltrada;
	}
	
	public List <Cuenta> filtrarPorCuentas(List <CSVObject> empresasByName){

		return empresasByName.stream().map(line ->this.convertirACuenta(line)).collect(Collectors.toList());
	}
	
	public Cuenta convertirACuenta(CSVObject line){
		return new Cuenta(line.getCuenta(),line.getPeriodo(),line.getValor());
	}

}
