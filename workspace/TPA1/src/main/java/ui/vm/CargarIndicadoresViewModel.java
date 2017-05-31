package ui.vm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.omg.CORBA.UserException;
import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

import parser.ParserFormulaToIndicador;
import parser.ParserJsonString;
import repository.ArchivoEIndicadoresUsuarioRepository;
import usuario.Indicador;
import usuario.IndicadorCustom;
import usuario.Indicador;
@Observable
public class CargarIndicadoresViewModel {

	private List<Indicador> indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();;
	
	private static String nombreIndicador;
	private static String formulaIndicador;


	private static int codigoDeError;
	
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
	
	public static int getCodigoDeError() {
		return codigoDeError;
	}
	
	public static void setCodigoDeError(int codigoDeError) {
		CargarIndicadoresViewModel.codigoDeError = codigoDeError;
	}
	
	public static void generarIndicador(){
		
		Indicador nuevoIndicador = new IndicadorCustom(nombreIndicador,formulaIndicador);
		String jsonElement = new Gson().toJson(nuevoIndicador); 
		
		
		catcheoYAnidadoAJSON(formulaIndicador,jsonElement);

		
	}
	
	public List<Indicador> getIndicadores(){
		Collections.sort(this.indicadores);
		return this.indicadores;
	}
	
	public static void catcheoYAnidadoAJSON(String formulaIndicador, String jsonElement){
		if(formulaIndicador != null && !formulaIndicador.trim().isEmpty()){

			try{
				new ParserFormulaToIndicador();
				ParserFormulaToIndicador.getCalculoIndicador(formulaIndicador);
	
				ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("indicadores",jsonElement );	
				setCodigoDeError(0);//devolviendo al estado original , ya que es correcto
			}catch(Exception e){
				setCodigoDeError(2);//codigo de error 2 , significa que se genero indicadores erroneos
			}
		
		}else{
			setCodigoDeError(1);//Codigo de error 1 , significa que se genero un indicador vacio
		}
	}
	
	
	
}
