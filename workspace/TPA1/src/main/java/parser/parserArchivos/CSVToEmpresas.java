package parser.parserArchivos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import exceptions.ArchivoInexistenteException;
import exceptions.CSVMalFormadoException;
import exceptions.PathIncorrectoException;
import exceptions.TipoDeArchivoIncorrectoException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;

import usuario.Cuenta;
import usuario.Empresa;

public class CSVToEmpresas {

	private  String archivo;
	private  String extension;
	public CSVToEmpresas(String archivo){
		if(archivo==null)
			throw new ArchivoInexistenteException();
		Path path = Paths.get(archivo);
		if (Files.notExists(path))
			throw new PathIncorrectoException();
		if(!((extension=FilenameUtils.getExtension(archivo)).equals("csv")))
			throw new TipoDeArchivoIncorrectoException();
		this.archivo=archivo;
	}
	
	public  List<CSVObject> CSVFileToCSVObjectList() throws IOException{
		HeaderColumnNameMappingStrategy<CSVObject> strategy = new HeaderColumnNameMappingStrategy<>();
		strategy.setType(CSVObject.class);
		CsvToBean<CSVObject> lineToCSVObject = new CsvToBean<>();
		CSVReader reader = new CSVReader(new FileReader(archivo));
		List <CSVObject> CSVObjectList = lineToCSVObject.parse(strategy,reader);
		return CSVObjectList;
	}
	
	
	public List<Empresa> csvFileToEmpresas() throws IOException{
		List<CSVObject> CSVObjectList = this.CSVFileToCSVObjectList();
		
		return this.CSVObjectListToEmpresasList(CSVObjectList);
	}
	
	public List <Empresa> CSVObjectListToEmpresasList(List <CSVObject> csvObjectList){
		int i=0;
		String nombreNuevaEmpresa;
		List <Empresa> empresas = new ArrayList <Empresa> ();
		
		Set<String> setNombreEmpresas = new HashSet<>(csvObjectList.stream().map(line -> line.getEmpresa()).collect(Collectors.toList()));
		List<String> nombreEmpresasSinRepetidos = new ArrayList<>(setNombreEmpresas);
		
		while(i < nombreEmpresasSinRepetidos.size()){
			
			nombreNuevaEmpresa=nombreEmpresasSinRepetidos.get(i);
			
			List <CSVObject> empresasByName = this.filtrarPorNombre(nombreNuevaEmpresa,csvObjectList);
			
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
