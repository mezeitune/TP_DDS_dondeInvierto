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
	String archivoParaJson;
	
	public Adapter(String archivo){
		archivoParaJson=archivo;
	}
	
	public List<Empresa> getEmpresasDelArchivo(){
			
			List <Empresa> empresas = new ArrayList <Empresa> ();
			JSONParser parser = new JSONParser();
			Type listType = new TypeToken <List<Empresa>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
			   
		        try {
		            Object obj = parser.parse(new FileReader(archivoParaJson));
		            JSONArray jsonArray = (JSONArray) obj;
		            String empresaString=jsonArray.toString(); // fromJson(2) pide como primer parametro un String.
		           empresas = new Gson().fromJson(empresaString,listType);
		      
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		        return empresas;
	}
}
