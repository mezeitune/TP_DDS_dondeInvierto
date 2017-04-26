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
	private String archivoParaJson;
	private List <Empresa> empresasObtenidasDelArchivo = new ArrayList <Empresa> ();
	private JSONParser parserJsonAObjetos = new JSONParser();
	private Object objetoAOtrosObjetos;
	
	public ParserJsonAEmpresaAdapter(String archivo){
		archivoParaJson=archivo;
		setObjecArchivo();
	}
	
	public List<Empresa> getEmpresasDelArchivo() {
			
		Type listType = new TypeToken <List<Empresa>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
		
		empresasObtenidasDelArchivo = new Gson().fromJson(stringEmpresasParaGson(),listType);
	
	    return empresasObtenidasDelArchivo;
	}
	
	
	public void setObjecArchivo() {
		
		try {
			objetoAOtrosObjetos = parserJsonAObjetos.parse(new FileReader(archivoParaJson));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String stringEmpresasParaGson(){
		JSONArray jsonArray=ParserJsonString.pasarDeObjetosAJSON(objetoAOtrosObjetos);
		return ParserJsonString.pasarDeJSONArrayAString(jsonArray);
	}
	

	
	
	
}
