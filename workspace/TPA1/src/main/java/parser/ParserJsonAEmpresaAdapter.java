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

import Class.Main;
import exceptions.CSVMalFormadoException;
import usuario.Empresa;

public class ParserJsonAEmpresaAdapter {
	//private String archivoParaJson;
	private List <Empresa> empresasObtenidasDelArchivo = new ArrayList <Empresa> ();
	private JSONParser parserJsonAObjetos = new JSONParser();
	private Object objetoAOtrosObjetos;
	private static String archivo;
	
	public ParserJsonAEmpresaAdapter(String archivo){
		this.archivo=archivo;
	}
	
	public List<Empresa> getEmpresasDelArchivo() {
			
		Type listType = new TypeToken <List<Empresa>>() {}.getType(); // Para paramtrizar en fromJson(2) y poder castear.
		
		this.empresasObtenidasDelArchivo = new Gson().fromJson(stringEmpresasParaGson(),listType);
	
	    return this.empresasObtenidasDelArchivo;
	}
	
	
	public Object definirObjetosDelArchivo() {
		
		try {
			return parserJsonAObjetos.parse(new FileReader(this.archivo));
			
			
		} catch (CSVMalFormadoException ex) {

			throw new CSVMalFormadoException();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new CSVMalFormadoException();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CSVMalFormadoException();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new CSVMalFormadoException();
		}
		
	}

	public String stringEmpresasParaGson(){
		JSONArray jsonArray=ParserJsonString.pasarDeObjetosAJSON(this.definirObjetosDelArchivo());
		return ParserJsonString.pasarDeJSONArrayAString(jsonArray);
	}

	
	

	

	
	
	
}