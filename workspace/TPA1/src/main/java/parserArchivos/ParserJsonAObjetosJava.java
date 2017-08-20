package parserArchivos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import condiciones.Condicion;
import excepciones.CSVInexistenteException;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;

public class ParserJsonAObjetosJava {

	private JSONParser parserJsonAObjetos = new JSONParser();
	
	private String archivo;
	
	public ParserJsonAObjetosJava(String nuevoArchivo){
		archivo=nuevoArchivo;
		}
	
	
	public List<Empresa> getEmpresasDelArchivo() {
			
		Type listType = new TypeToken <List<Empresa>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
		
		return new Gson().fromJson(stringParaGson(),listType);
		}
	
	public List<Indicador> getIndicadoresDelArchivo() {
		
		Type listType = new TypeToken <List<Indicador>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
		
		return new Gson().fromJson(stringParaGson(),listType);
		}
	
	public List<Metodologia> getMetodologiasDelArchivo() {
	
		Type listType = new TypeToken <List<Metodologia>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
		
		return new Gson().fromJson(stringParaGson(),listType);
		}
	
	public List<Condicion> getCondicionesDelArchivo() {
			
		Type listType = new TypeToken <List<Condicion>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
		
		return new Gson().fromJson(stringParaGson(),listType);
		}
	
		
	
	public Object definirObjetosDelArchivo() {
		
		try {
			return parserJsonAObjetos.parse(new FileReader(archivo));
			
		} catch (CSVInexistenteException ex) {

			throw new CSVInexistenteException();
		} catch (FileNotFoundException e) {
			throw new CSVInexistenteException();
			
		} catch (IOException e) {
			throw new CSVInexistenteException();
		} catch (ParseException e) {
			throw new CSVInexistenteException();
		}
		
	}

	public String stringParaGson(){
		JSONArray jsonArray=ParserJsonString.pasarDeObjetosAJSON(this.definirObjetosDelArchivo());
		return ParserJsonString.pasarDeJSONArrayAString(jsonArray);
	}
	
}