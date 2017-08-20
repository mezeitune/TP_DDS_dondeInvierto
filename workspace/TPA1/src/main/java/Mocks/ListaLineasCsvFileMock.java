package Mocks;

import java.util.ArrayList;
import java.util.List;

import parserArchivos.CsvEmpresa;

public class ListaLineasCsvFileMock {

	
	public List<CsvEmpresa> mockearListaLineas(){
		List <CsvEmpresa> listaLineas = new ArrayList<CsvEmpresa> ();
		
		CsvEmpresa linea1 = new CsvEmpresa().init("Facebook","Cuenta 1","2016",14870);
		CsvEmpresa linea2 = new CsvEmpresa().init("Facebook","Cuenta 2","2015",8162);
		CsvEmpresa linea3 = new CsvEmpresa().init("Facebook","Cuenta 3","2016",11617);
		CsvEmpresa linea4 = new CsvEmpresa().init("Facebook","Cuenta 4","2016",27638);
		CsvEmpresa linea5 = new CsvEmpresa().init("Facebook","Cuenta 5","2014",7000);
		CsvEmpresa linea6 = new CsvEmpresa().init("Apple","Cuenta 1","2016",52276);
		CsvEmpresa linea7 = new CsvEmpresa().init("Apple","Cuenta 2","2015",65824);
		CsvEmpresa linea8 = new CsvEmpresa().init("Apple","Cuenta 3","2015",30512);
		CsvEmpresa linea9 = new CsvEmpresa().init("Apple","Cuenta 4","2015",15420);
		
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
