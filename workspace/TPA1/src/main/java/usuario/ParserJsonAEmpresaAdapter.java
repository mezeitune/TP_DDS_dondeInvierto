package usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ParserJsonAEmpresaAdapter {
	//private String archivoParaJson;
	private List <Empresa> empresasObtenidasDelArchivo = new ArrayList <Empresa> ();
	private JSONParser parserJsonAObjetos = new JSONParser();
	private Object objetoAOtrosObjetos;
	private static String archivo;
	
	public List<Empresa> getEmpresasDelArchivo() {
			
		Type listType = new TypeToken <List<Empresa>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
		
		this.empresasObtenidasDelArchivo = new Gson().fromJson(stringEmpresasParaGson(),listType);
	
	    return this.empresasObtenidasDelArchivo;
	}
	
	
	public void definirObjetosDelArchivo(String archivoParaJson) throws IOException {
		
		try {
			this.objetoAOtrosObjetos = parserJsonAObjetos.parse(new FileReader(archivoParaJson));
			this.setArchivo(archivoParaJson);
			
		} catch (IOException | ParseException e) {

			throw new IOException();
		}
		
	}
	
	public void setArchivo(String archivoParaJson){
		this.archivo= archivoParaJson;
	}
	
	public static String getArchivo(){
		return archivo;
	}
	
	public String stringEmpresasParaGson(){
		JSONArray jsonArray=ParserJsonString.pasarDeObjetosAJSON(objetoAOtrosObjetos);
		return ParserJsonString.pasarDeJSONArrayAString(jsonArray);
	}


	

	
	
	
}