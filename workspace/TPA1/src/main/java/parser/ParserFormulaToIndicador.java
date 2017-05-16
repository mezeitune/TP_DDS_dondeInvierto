package parser;

import java.util.stream.IntStream;
import repository.ArchivoEIndicadoresUsuarioRepository;

public class ParserFormulaToIndicador {

	
	public ParserFormulaToIndicador(){
		ParserJsonAEmpresaAdapter parserEmpIndicador = new ParserJsonAEmpresaAdapter("indicadores.json");
		ArchivoEIndicadoresUsuarioRepository.setIndicadoresDefinidosPorElUsuario(parserEmpIndicador.getIndicadoresDelArchivo());
		
	}
	
	public static float getCalculoIndicador(String formula){
		if(formula.matches("(.*)[+](.*)")){
			return getSuma(formula);
	
		}else 
		if(formula.matches("(.*)[-](.*)")){
			return getResta(formula);
	
		}else
		if(formula.matches("(.*)[*](.*)")){
			return getMultiplicacion(formula);
	
		}else
		if(formula.matches("(.*)[/](.*)")){
			return getDivision(formula);
	
		}
		return 0;
		
		
	}
	
	private static float getDivision(String formula) {
		String operador = "[/]";
		String[] operandos = formula.split(operador);
	
		int[] numerosAOperar= parsearOperandosAInt(operandos);
		float div= numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			div = div / numerosAOperar[i];
		}
		return div;
	}

	private static float getMultiplicacion(String formula) {
		String operador = "[*]";
		String[] operandos = formula.split(operador);
	
		int[] numerosAOperar= parsearOperandosAInt(operandos);
		float mult= numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			mult = mult * numerosAOperar[i];
		}
		
		return mult;
	}

	private static float getResta(String formula) {
		String operador = "[-]";
		String[] operandos = formula.split(operador);
		
		int[] numerosAOperar= parsearOperandosAInt(operandos);
		float resta = numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			resta = resta - numerosAOperar[i];
		}
	
		return resta;
	}

	public static float getSuma(String formula){
		String operador = "[+]";
		String[] operandos = formula.split(operador);
	
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
}
