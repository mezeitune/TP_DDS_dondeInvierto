package Mocks;

import java.util.ArrayList;
import java.util.List;

import parser.parserArchivos.CSVObject;

public class ListaLineasCsvFileMock {

	
	public List<CSVObject> mockearListaLineas(){
		List <CSVObject> listaLineas = new ArrayList<CSVObject> ();
		
		CSVObject linea1 = new CSVObject().init("Facebook","Cuenta 1","2016",14870);
		CSVObject linea2 = new CSVObject().init("Facebook","Cuenta 2","2015",8162);
		CSVObject linea3 = new CSVObject().init("Facebook","Cuenta 3","2016",11617);
		CSVObject linea4 = new CSVObject().init("Facebook","Cuenta 4","2016",27638);
		CSVObject linea5 = new CSVObject().init("Facebook","Cuenta 5","2014",7000);
		CSVObject linea6 = new CSVObject().init("Apple","Cuenta 1","2016",52276);
		CSVObject linea7 = new CSVObject().init("Apple","Cuenta 2","2015",65824);
		CSVObject linea8 = new CSVObject().init("Apple","Cuenta 3","2015",30512);
		CSVObject linea9 = new CSVObject().init("Apple","Cuenta 4","2015",15420);
		
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
