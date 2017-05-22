package parser;

import java.io.IOException;
import java.util.stream.IntStream;

import javax.print.DocFlavor.CHAR_ARRAY;

import org.mockito.internal.matchers.Equals;

import java.util.List;

import repository.ArchivoEIndicadoresUsuarioRepository;
import usuario.Indicador;
import usuario.IndicadorCustom;

public class ParserFormulaToIndicador {

	private static List<IndicadorCustom> indicadores;
	
	
	public ParserFormulaToIndicador(){
		ParserJsonAEmpresaAdapter parserEmpIndicador = new ParserJsonAEmpresaAdapter("indicadores.json");
		ArchivoEIndicadoresUsuarioRepository.setIndicadoresDefinidosPorElUsuario(parserEmpIndicador.getIndicadoresDelArchivo());
		
	}
	
	public static int getCalculoIndicador(String formula){
		
		if(formula.matches("(.*)[+](.*)")){
			return getSuma(formula);
		}else if(formula.matches("(.*)[-](.*)")){
			return getResta(formula);
	
		}else if(formula.matches("(.*)[*](.*)")){
			return getMultiplicacion(formula);
	
		}else if(formula.matches("(.*)[/](.*)")){
			return getDivision(formula);
	
		}
		return 0;
		
		
	}
	
	
	
	private static int getDivision(String formula) {
		String operador = "[/]";
		String[] operandos = formula.split(operador);
	
		int[] numerosAOperar= parsearOperandosAInt(operandos);
		int div = numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			div = div / numerosAOperar[i];
		}
		return div;
	}

	private static int getMultiplicacion(String formula) {
		String operador = "[*]";
		String[] operandos = formula.split(operador);
	
		int[] numerosAOperar= parsearOperandosAInt(operandos);
		int mult= numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			mult = mult * numerosAOperar[i];
		}
		
		return mult;
	}

	private static int getResta(String formula) {
		String operador = "[-]";
		String[] operandos = formula.split(operador);
		
		int[] numerosAOperar= parsearOperandosAInt(operandos);
		int resta = numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			resta = resta - numerosAOperar[i];
		}
	
		return resta;
	}

	public static int getSuma(String formula){
		
		String operador = "[+]";
		String[] operandos = formula.split(operador);

		operandos = ParserFormulaToIndicador.mapIndicadoresAFormatoValido(operandos);
	
		int sum = IntStream.of(parsearOperandosAInt(operandos)).sum();
		
		return sum;
	}
	
	public static int[] parsearOperandosAInt(String[] operandosString){
		int[] numeros = new int[operandosString.length];
		for(int i = 0;i < operandosString.length;i++)
		{
		   // Note that this is assuming valid input
		   // If you want to check then add a try/catch 
		   // and another index for the numbers if to continue adding the others
		   numeros[i] = Integer.parseInt(operandosString[i]);
		}
		return numeros;
	}
	
	public static String[] mapIndicadoresAFormatoValido(String[] operandos){
		
		indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
		
		for (int i = 0; i < operandos.length; i++) {
			for (int k = 0; k < indicadores.size(); k++) {
				if(indicadores.get(k).getNombre().equals(operandos[i])){
					operandos[i] = String.valueOf(indicadores.get(k).calcular());
				}
			}
		}
		return operandos;
	}
	
}
