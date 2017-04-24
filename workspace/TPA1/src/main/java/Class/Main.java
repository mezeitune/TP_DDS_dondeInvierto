package Class;
import usuario.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
//import org.uqbar.arena.widgets.List;
import org.uqbar.commons.utils.Observable;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;




@Observable
public class Main {

	
	public static void main(String[] args){
		  
	List<Empresa> empresasPrueba = new Adapter().getEmpresasDelArchivo();
	Main.consultarCuentas(empresasPrueba);
	
	  }
	
	public static void consultarCuentas(List<Empresa> empresas){
		int i,j;
		for(i=0; i< empresas.size();i++){
			System.out.println(empresas.get(i).getNombre());
			for(j=0; j < empresas.get(i).getCuentas().size(); j++){
				System.out.println(empresas.get(i).getCuentas().get(j).getNombre());
				System.out.println(empresas.get(i).getCuentas().get(j).getPeriodo());
				System.out.println(empresas.get(i).getCuentas().get(j).getValor());
			}
		}
	}
	
	}

	
	
	
	
	

//"C:\\arch.txt"