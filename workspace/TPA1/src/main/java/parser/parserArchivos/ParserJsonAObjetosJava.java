package parser.parserArchivos;

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

import excepciones.CSVInexistenteException;
import parser.ParserJsonString;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;

public class ParserJsonAObjetosJava {

	private List <Empresa> empresasObtenidasDelArchivo = new ArrayList <Empresa> ();
	private List <Indicador> indicadoresObtenidasDelArchivo = new ArrayList <Indicador> ();
	private List <Metodologia> metodologiaObtenidasDelArchivo = new ArrayList <Metodologia> ();
	private JSONParser parserJsonAObjetos = new JSONParser();
	private Object objetoAOtrosObjetos;
	private static String archivo;
	
	public ParserJsonAObjetosJava(String archivo){
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
		public List<Metodologia> getMetodologiasDelArchivo() {
		
		Type listType = new TypeToken <List<Indicador>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
		
		this.metodologiaObtenidasDelArchivo = new Gson().fromJson(stringParaGson(),listType);
	
	    return this.metodologiaObtenidasDelArchivo;
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

	public void setMetodologias() {
		// TODO Auto-generated method stub
		
	}

	
	

	

	
	
	
}