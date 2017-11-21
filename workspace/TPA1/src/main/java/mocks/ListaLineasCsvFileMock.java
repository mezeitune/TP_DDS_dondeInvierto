package mocks;

import java.util.ArrayList;
import java.util.List;

import parserArchivos.CsvObjectEmpresa;

public class ListaLineasCsvFileMock {

	
	public List<CsvObjectEmpresa> mockearListaLineas(){
		List <CsvObjectEmpresa> listaLineas = new ArrayList<CsvObjectEmpresa> ();
		
		CsvObjectEmpresa linea1 = new CsvObjectEmpresa().init("Facebook","Cuenta 1","2016",14870);
		CsvObjectEmpresa linea2 = new CsvObjectEmpresa().init("Facebook","Cuenta 2","2015",8162);
		CsvObjectEmpresa linea3 = new CsvObjectEmpresa().init("Facebook","Cuenta 3","2016",11617);
		CsvObjectEmpresa linea4 = new CsvObjectEmpresa().init("Facebook","Cuenta 4","2016",27638);
		CsvObjectEmpresa linea5 = new CsvObjectEmpresa().init("Facebook","Cuenta 5","2014",7000);
		CsvObjectEmpresa linea6 = new CsvObjectEmpresa().init("Apple","Cuenta 1","2016",52276);
		CsvObjectEmpresa linea7 = new CsvObjectEmpresa().init("Apple","Cuenta 2","2015",65824);
		CsvObjectEmpresa linea8 = new CsvObjectEmpresa().init("Apple","Cuenta 3","2015",30512);
		CsvObjectEmpresa linea9 = new CsvObjectEmpresa().init("Apple","Cuenta 4","2015",15420);
		
		listaLineas.add(linea1);
		listaLineas.add(linea2);
		listaLineas.add(linea3);
		listaLineas.add(linea4);
		listaLineas.add(linea5);
		listaLineas.add(linea6);
		listaLineas.add(linea7);
		listaLineas.add(linea8);
		listaLineas.add(linea9);
		
		return listaLineas;
	}
	
	
}
