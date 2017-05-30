package parser;

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

import exceptions.CSVInexistenteException;
import usuario.Empresa;
import usuario.Indicador;

public class ParserJsonAEmpresaAdapter {

	private List <Empresa> empresasObtenidasDelArchivo = new ArrayList <Empresa> ();
	private List <Indicador> indicadoresObtenidasDelArchivo = new ArrayList <Indicador> ();
	private JSONParser parserJsonAObjetos = new JSONParser();
	private Object objetoAOtrosObjetos;
	private static String archivo;
	
	public ParserJsonAEmpresaAdapter(String archivo){
		this.archivo=archivo;
	}
	
	public List<Empresa> getEmpresasDelArchivo() {
			
		Type listType = new TypeToken <List<Empresa>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
		
		this.empresasObtenidasDelArchivo = new Gson().fromJson(stringParaGson(),listType);
	
	    return this.empresasObtenidasDelArchivo;
	}
	
	public List<Indicador> getIndicadoresDelArchivo() {
		
		Type listType = new TypeToken <List<Indicador>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
		
		this.indicadoresObtenidasDelArchivo = new Gson().fromJson(stringParaGson(),listType);
	
	    return this.indicadoresObtenidasDelArchivo;
	}
	
	
	
	public Object definirObjetosDelArchivo() {
		
		try {
			return parserJsonAObjetos.parse(new FileReader(this.archivo));
			
			
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