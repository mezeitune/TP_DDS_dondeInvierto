package ui.vm;

import java.util.Collections;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import excepciones.FormulaIndicadorNotFound;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.IndicadorRepetidoException;
import excepciones.NombreIndicadorNotFound;
import parserIndicadores.ParserFormulaIndicador;
import repository.IndicadoresRepository;
import usuario.Indicador;
@Observable
public class CargarIndicadoresViewModel {

	private static List<Indicador> indicadores = IndicadoresRepository.getIndicadoresDefinidosPorElUsuario();
	
	private static String nombreIndicador;
	private static String formulaIndicador;

	public CargarIndicadoresViewModel(){
		nombreIndicador=null;
		formulaIndicador=null;
	}
	private static int codigoDeError;
	
	public String getNombreIndicador() {
		return nombreIndicador;
	}
	public void setNombreIndicador(String nombreIndicador) {
		CargarIndicadoresViewModel.nombreIndicador = nombreIndicador;
	}
	public String getFormulaIndicador() {
		return formulaIndicador;
	}
	public void setFormulaIndicador(String formulaIndicador) {

		CargarIndicadoresViewModel.formulaIndicador = formulaIndicador;
	}
	
	public static int getCodigoDeError() {
		return codigoDeError;
	}
	
	public static void setCodigoDeError(int codigoDeError) {
		CargarIndicadoresViewModel.codigoDeError = codigoDeError;
	}
	
	public void generarIndicador() throws NombreIndicadorNotFound, FormulaIndicadorNotFound, IndicadorRepetidoException, FormulaIndicadorNotValidException {
		
		if(nombreIndicador == null) throw new NombreIndicadorNotFound();
		if(formulaIndicador == null) throw new FormulaIndicadorNotFound();
		
		Indicador nuevoIndicador = new Indicador(nombreIndicador,formulaIndicador);
		
		if(this.esUnIndicadorYaIngresado(nuevoIndicador)) throw new IndicadorRepetidoException();
		
		if(ParserFormulaIndicador.formulaIndicadorValida(formulaIndicador)) throw new FormulaIndicadorNotValidException();

		IndicadoresRepository.addIndicador(nuevoIndicador);
		
		ObservableUtils.firePropertyChanged(this, "indicadores");
	}
	public boolean esUnIndicadorYaIngresado (Indicador nuevoIndicador) {
		return ParserFormulaIndicador.validarIndicadorRepetidoAntesDePrecargar(nuevoIndicador.getNombre(),nuevoIndicador.getFormula());
	
	}
	public List<Indicador> getIndicadores(){
		Collections.sort(CargarIndicadoresViewModel.indicadores);
		return CargarIndicadoresViewModel.indicadores;
	}
	
	
	
}
