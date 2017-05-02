package parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

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
		CSVReader reader = new CSVReader(new FileReader("/home/manuel/empresas.csv"));
		List <CSVObject> CSVObjectList = csvToCSVObject.parse(strategy,reader);
		return CSVObjectList;
	}
	
	
	public  List<Empresa> csvFileToEmpresas() throws IOException{
		List <Empresa> empresas = new ArrayList <Empresa> ();
		List<CSVObject> CSVObjectList =this.csvFileToCSVObject();
		String empresa;
		int i=0;
		
		Set<String> setNombreEmpresas = new HashSet<>(CSVObjectList.stream().map(line -> line.getEmpresa()).collect(Collectors.toList()));
		List<String> nombreEmpresas = new ArrayList<>(setNombreEmpresas);
		
		while(i < setNombreEmpresas.size()){
		Empresa nuevaEmpresa=new Empresa();
		empresa=nombreEmpresas.get(i);
		List <CSVObject> empresasByName = this.filtrarPorNombre(empresa,CSVObjectList);
		List <Cuenta> cuentasByEmpresa = this.filtrarPorCuentas(empresasByName);
		nuevaEmpresa.setNombre(empresa);
		nuevaEmpresa.setCuentas(cuentasByEmpresa);
		empresas.add(nuevaEmpresa);
		i++;
		}
		return empresas;
	}
	
	public  Cuenta convertirACuenta(CSVObject line){
		Cuenta nuevaCuenta = new Cuenta();
		nuevaCuenta.setNombre(line.getCuenta());
		nuevaCuenta.setPeriodo(line.getPeriodo());
		nuevaCuenta.setValor(line.getValor());
		return nuevaCuenta;
	}
	
	public List <CSVObject> filtrarPorNombre(String empresa, List<CSVObject> list){
		return list.stream().filter(line -> line.getEmpresa().equals(empresa)).collect(Collectors.toList());
	}
	
	public List <Cuenta> filtrarPorCuentas(List <CSVObject> empresasByName){
		return empresasByName.stream().map(line ->this.convertirACuenta(line)).collect(Collectors.toList());
	}
	
	
	
	/*Esto es para testear rapidamente
	public static void main(String[] args) throws IOException{
		int i,j;
		
		List<Empresa> empresas =CSVToEmpresas.csvFileToEmpresas();
		System.out.println("------------------");
		for(i=0; i< empresas.size();i++){
			System.out.println(empresas.get(i).getNombre());
			for(j=0; j < empresas.get(i).getCuentas().size(); j++){
				System.out.println(empresas.get(i).getCuentas().get(j).getNombre());
				System.out.println(empresas.get(i).getCuentas().get(j).getPeriodo());
				System.out.println(empresas.get(i).getCuentas().get(j).getValor());
			}
		}
}
*/
	
}
