package usuario;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Adapter {


public List<Empresa> getEmpresasDelArchivo(){
		
		List <Empresa> empresas = new ArrayList <Empresa> ();
		JSONParser parser = new JSONParser();
		Type listType = new TypeToken <List<Empresa>>() {}.getType(); // Esto es para poder castear bien en el Gson.
		   
	        try {
	 
	            Object obj = parser.parse(new FileReader("empresas.json"));
	 
	            JSONArray jsonArray = (JSONArray) obj;
	            String empresaString=jsonArray.toString();

	           empresas = new Gson().fromJson(empresaString,listType);
	           
	      
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return empresas;
		
}
}
