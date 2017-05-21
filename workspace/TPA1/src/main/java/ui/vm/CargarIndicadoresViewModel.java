package ui.vm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

import parser.ParserFormulaToIndicador;
import parser.ParserJsonString;
import repository.ArchivoEIndicadoresUsuarioRepository;
import ui.windows.Custom;
import usuario.Indicador;
@Observable
public class CargarIndicadoresViewModel {

	private List<Indicador> indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();;
	
	private static String nombreIndicador;
	private static String formulaIndicador;
	
	public String getNombreIndicador() {
		return nombreIndicador;
	}
	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}
	public String getFormulaIndicador() {
		return formulaIndicador;
	}
	public void setFormulaIndicador(String formulaIndicador) {

		this.formulaIndicador = formulaIndicador;
	}

	
	
	public static void generarIndicador(){
		
		Custom obj = new Custom(nombreIndicador,formulaIndicador);
		
		
		Gson gson= new Gson();
		String jsonElement = gson.toJson(obj);
		
		new ParserFormulaToIndicador();
		ParserFormulaToIndicador.getCalculoIndicador(formulaIndicador);

		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnFile("indicadores",jsonElement );	
		
	}
	
	public List<Indicador> getIndicadores(){
		return indicadores;
		
	}
	
	
	
}
