package parser;

import java.util.stream.IntStream;

import repository.ArchivoEIndicadoresUsuarioRepository;

public class ParserFormulaToIndicador {

	
	public ParserFormulaToIndicador(){
		ParserJsonAEmpresaAdapter parserEmpIndicador = new ParserJsonAEmpresaAdapter("indicadores.json");
		ArchivoEIndicadoresUsuarioRepository.setIndicadoresDefinidosPorElUsuario(parserEmpIndicador.getIndicadoresDelArchivo());
		
	}
	
	public static float getCalculoIndicador(String formula){
		
		String delims = "[+]";
		String[] tokens = formula.split(delims);
		
		int[] numbers = new int[tokens.length];
		for(int i = 0;i < tokens.length;i++)
		{
		   // Note that this is assuming valid input
		   // If you want to check then add a try/catch 
		   // and another index for the numbers if to continue adding the others
		   numbers[i] = Integer.parseInt(tokens[i]);
		}
		
		
		int sum = IntStream.of(numbers).sum();
		
		
		return sum;
	}
	
	
}
