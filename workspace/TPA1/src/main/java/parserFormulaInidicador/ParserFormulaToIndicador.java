package parserFormulaInidicador;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import parser.parserArchivos.CSVToEmpresas;
import parser.parserArchivos.ParserJsonAEmpresaAdapter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import repository.ArchivoEIndicadoresUsuarioRepository;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.Indicador;

public class ParserFormulaToIndicador {

	private static List<Cuenta> cuentas = new LinkedList<>(); 
	private static List<Empresa> empresas = new LinkedList<>(); 
	private static List<Indicador> indicadores = new LinkedList<>();
	private static List<Cuenta> cuentasPorPeriodo;
	private static Empresa empresa ;
	private static String periodo;
	
	public ParserFormulaToIndicador() throws IOException{
		ParserJsonAEmpresaAdapter parserEmpIndicador = new ParserJsonAEmpresaAdapter("indicadores.json");
		CSVToEmpresas parserCuentas = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		
		ArchivoEIndicadoresUsuarioRepository.setIndicadoresDefinidosPorElUsuario(parserEmpIndicador.getIndicadoresDelArchivo());
		ArchivoEIndicadoresUsuarioRepository.cargarIndicadoresPredefinidos();
		
		indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
		empresas=parserCuentas.csvFileToEmpresas();
		
		/*Obtengo todas las cuentas de las empresas del csv*/
		for(int i=0;i<empresas.size();i++){
			cuentas.addAll(empresas.get(i).getCuentas());
		}
	}
	
	public static void init(List<Indicador> indicadoresTest, List<Cuenta> cuentasTest) throws IOException{
		indicadores = indicadoresTest;
		cuentas = cuentasTest;
	}
	
	public static void setEmpresa(Empresa unaEmpresa){
		empresa= unaEmpresa;
	}
	
	public static void setPeriodo(String unPeriodo){
		periodo=unPeriodo;
		cuentasPorPeriodo = empresa.getCuentasPorPeriodo(periodo);
	}
	
	public static int getCalculoIndicador(String formula){
		return ParserFormulaToIndicador.construirArbolOperaciones(formula).calcular();
	}
	

	public static Operacion construirArbolOperaciones(String operandos){
		String operadorSuma = "(.*)[+](.*)";
		String operadorResta = "(.*)[-](.*)";
		String operadorMultiplicacion = "(.*)[*](.*)";
		String operadorDivision = "(.*)[/](.*)";
		if(operandos.matches(operadorSuma)) return ParserFormulaToIndicador.getSuma(operandos.split("[+]"));
		if(operandos.matches(operadorResta)) return ParserFormulaToIndicador.getResta(operandos.split("[-]"));
		if(operandos.matches(operadorMultiplicacion)) return ParserFormulaToIndicador.getMultiplicacion(operandos.split("[*]"));
		if (operandos.matches(operadorDivision)) return ParserFormulaToIndicador.getDivision(operandos.split("[/]"));
		else {
				return ParserFormulaToIndicador.getClaseOperador(operandos);
		}
	}
	
	/*
	public static Operacion construirArbolOperaciones(String formula){
		String operadorSuma = "[+]";
		String operadorResta = "[-]";
		String operadorMultiplicacion = "[*]" ; 
		String operadorDivision = "[/]";
		//Indicador indicador;
			if(formula.matches("(.*)[+](.*)")){
				return ParserFormulaToIndicador.getSuma(formula.split(operadorSuma));
			}
			if(formula.matches("(.*)[-](.*)")){
				return ParserFormulaToIndicador.getResta(formula.split(operadorResta));
				
			}
			if(formula.matches("(.*)[*](.*)")){
				return ParserFormulaToIndicador.getMultiplicacion(formula.split(operadorMultiplicacion));
	
			}
			if(formula.matches("(.*)[/](.*)")){
				return ParserFormulaToIndicador.getDivision(formula.split(operadorDivision));
			}
			
			/*Esta para que la ventana de evaluacion indicadoresTexto pueda cargar un unico indicador*/	
			/*if(!ParserFormulaToIndicador.indicador(formula).equals(0)){
				indicador = ParserFormulaToIndicador.indicador(formula);
				return indicador;
			}
			*/ /* Con el Else de abajo no haria falta esta entrada */ 
			//else return ParserFormulaToIndicador.getClaseOperador(formula);
	//}

	
	
	
	/*
	private static Indicador indicador(String formula) {
		int j;
		indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
		for (j = 0; j < indicadores.size(); j++) {
			if(indicadores.get(j).getNombre().equals(formula)){
				return indicadores.get(j);
			}
		}
		
		return null;
	}
	*/

	public static Division getDivision(String [] formula){
		System.out.println("Creando nodo Division");

		Division division = new Division();
		List <String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}
		System.out.println("Operador Izquierdo");
		System.out.println(operandos.get(0));
		
		division.setOperador1(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		
		System.out.println("HIjo izquierdo Division seteado");
		System.out.println(division.getOperador1().getClass().getName());
		
		String formulaString = "";
		for(i=0;i<operandos.size();i++){
			formulaString += operandos.get(i);
		}
		System.out.println("Operador Derecho");
		System.out.println(formulaString);

		division.setOperador2(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		System.out.println("HIjo derecho Division seteado");
		System.out.println(division.getOperador2().getClass().getName());
		return division;
		}

	public static Multiplicacion getMultiplicacion(String [] formula){
		System.out.println("Creando nodo Multiplicacion");
		Multiplicacion multiplicacion = new Multiplicacion();
		List <String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}
		System.out.println("Operador Izquierdo");
		System.out.println(operandos.get(0));
		multiplicacion.setOperador1(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		System.out.println("HIjo izquierdo Multiplicacion seteado");
		System.out.println(multiplicacion.getOperador1().getClass().getName());
		
		String formulaString = "";
		for(i=0;i<operandos.size();i++){
			formulaString += operandos.get(i);
		}
		System.out.println("Operador Derecho");
		System.out.println(formulaString);

		multiplicacion.setOperador2(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		System.out.println("HIjo derecho Multiplicacion seteado");
		System.out.println(multiplicacion.getOperador2().getClass().getName());
		return multiplicacion;
		
	}

	public static Resta getResta(String [] formula){
		System.out.println("Creando nodo Resta");
		
		Resta resta = new Resta();
		List <String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}
		System.out.println("Operador IZquierdo");
		System.out.println(operandos.get(0));
		resta.setOperador1(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		System.out.println("HIjo izquierdo Resta seteado");
		System.out.println(resta.getOperador1().getClass().getName());
		
		String formulaString = "";
		for(i=0;i<operandos.size();i++){
			formulaString += operandos.get(i);
		}
		System.out.println("Operador Derecho");
		System.out.println(formulaString);

		resta.setOperador2(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		System.out.println("HIjo derecho Resta seteado");
		System.out.println(resta.getOperador2().getClass().getName());
		return resta;
	
	}

	
	
	public static Suma getSuma(String [] formula){
		System.out.println("Creando nodo Suma");
		Suma suma = new Suma();
		List <String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}
		System.out.println("Operador IZquierdo");
		System.out.println(operandos.get(0));
		
		suma.setOperador1(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		
		System.out.println("HIjo izquierdo Suma seteado");
		System.out.println(suma.getOperador1().getClass().getName());
		
		String formulaString = "";
		for(i=0;i<operandos.size();i++){
			formulaString += operandos.get(i);
		}
		System.out.println("Operador Derecho");
		System.out.println(formulaString);
		
		suma.setOperador2(ParserFormulaToIndicador.construirArbolOperaciones(formulaString));
		
		System.out.println("HIjo derecho Suma seteado");
		System.out.println(suma.getOperador2().getClass().getName());
		return suma;
	}
	
	public static Operacion getClaseOperador(String operador){
		if (ParserFormulaToIndicador.esIndicador(operador)) return ParserFormulaToIndicador.buscarYObtenerIndicador(operador);
		if (ParserFormulaToIndicador.esCuenta(operador)) return ParserFormulaToIndicador.buscarYObtenerCuenta(operador);
		else return new Constante(Integer.parseInt(operador));
	}
	
	public static boolean esIndicador(String operador){
		return indicadores.stream().anyMatch(indicador -> indicador.getNombre().equals(operador));
	}
	
	public static boolean esCuenta(String operador){
		return cuentas.stream().anyMatch(cuenta -> cuenta.getNombre().equals(operador));
	}
	
	public static Indicador buscarYObtenerIndicador(String operador){
		return indicadores.stream().filter(indicador -> indicador.getNombre().equals(operador)).collect(Collectors.toList()).get(0);
	}
	
	public static Cuenta buscarYObtenerCuenta(String operador){
		return cuentas.stream().filter(cuenta -> cuenta.getNombre().equals(operador)).collect(Collectors.toList()).get(0);
	}/*TODO: Deberia buscar en cuentas por periodo. En Cuentas una cuenta puede estar repetida*/
	
	/*
	
	public static int[] parsearOperandosAInt(String[] operandos){
		int[] numeros = new int[operandos.length];
		
		for(int i = 0;i < operandos.length;i++){
		   numeros[i] = Integer.parseInt(operandos[i]);
		}
		return numeros;
	}
	*/
	
	/*
	private static Operacion matcheaOtroOperador(String operandos){
		String operadorSuma = "(.*)[+](.*)";
		String operadorResta = "(.*)[-](.*)";
		String operadorMultiplicacion = "(.*)[*](.*)";
		String operadorDivision = "(.*)[/](.*)";
		if(operandos.matches(operadorSuma)) return ParserFormulaToIndicador.getSuma(operandos.split("[+]"));
		if(operandos.matches(operadorResta)) return ParserFormulaToIndicador.getResta(operandos.split("[-]"));
		if(operandos.matches(operadorMultiplicacion)) return ParserFormulaToIndicador.getMultiplicacion(operandos.split("[*]"));
		if (operandos.matches(operadorDivision)) return ParserFormulaToIndicador.getDivision(operandos.split("[/]"));
		else {
				return ParserFormulaToIndicador.getClaseOperador(operandos);
		}
	}
	*/
	/*
	private static Operacion matcheaOtroOperadorADerecha(String operandos,Operacion operacion){
		String operadorSuma = "(.*)[+](.*)";
		String operadorResta = "(.*)[-](.*)";
		String operadorMultiplicacion = "(.*)[*](.*)";
		String operadorDivision = "(.*)[/](.*)";
		if(operandos.matches(operadorSuma)) operacion.setOperador2(ParserFormulaToIndicador.getSuma(operandos.split("[+]")));
		if(operandos.matches(operadorResta)) operacion.setOperador2(ParserFormulaToIndicador.getResta(operandos.split("[-]")));
		if(operandos.matches(operadorMultiplicacion)) operacion.setOperador2(ParserFormulaToIndicador.getMultiplicacion(operandos.split("[*]")));
		if (operandos.matches(operadorDivision)) operacion.setOperador2(ParserFormulaToIndicador.getDivision(operandos.split("[/]")));
		else {
				operacion.setOperador2(ParserFormulaToIndicador.getClaseOperador(operandos));
		}
	}*/
	
	
	/*
	public static String[] elementosToOperandos(String[] operandos) {
		int i,j;
		
		
		for (int j2 = 0; j2 < operandos.length; j2++) {
		}
		
		
		
		
		Si matchea con un indicador
		for (i = 0; i < operandos.length; i++) {
			for (j = 0; j < indicadores.size(); j++) {
				if(indicadores.get(j).getNombre().equals(operandos[i])){

						operandos[i] = String.valueOf(indicadores.get(j).calcular());
				
				}
			}
			Si matchea con una cuenta
			try{
				cuentasPorPeriodo=empresa.getCuentasPorPeriodo(periodo);
				for (j= 0; j<cuentasPorPeriodo.size();j++){
					if(cuentasPorPeriodo.get(j).getNombre().equals(operandos[i])){
						operandos[i]= String.valueOf(cuentasPorPeriodo.get(j).getValor());
					}
				}
			}catch(NullPointerException e){}
			
			Si no matchea ni cuenta ni indicador, entonces es un operador basico y no hay modificaciones en el buffer
		}
		return operandos;
	}
	*/
	public static boolean validarAntesDePrecargar(String formula) throws IOException{ 
		indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		List<Empresa> empresas = parser.csvFileToEmpresas();

		for(int i=0;i<empresas.size();i++){
				cuentas.addAll(empresas.get(i).getCuentas());
			}
	
		
		String[] result = formula.split("[-+*/]");
		String[] ver = new String[result.length];
		
		for(int j=0;j<ver.length;j++){
			ver[j]="no";
		}
		
		for (int i = 0; i < result.length; i++) {
			
			if( StringUtils.isNumeric(result[i])){
				ver[i]="si";
			}else{
				for(Indicador s : indicadores){
					  if(s.getNombre().equals(result[i])){
						  ver[i]="si";
					  }
				}
				
				for(Cuenta s : cuentas){
					  if(s.getNombre().equals(result[i])){
						  ver[i]="si";
					  }
				}
			}
		}
		
		for(int k=0;k<ver.length;k++){
			if(ver[k].equals("no")){
				return false;
			}
		}

		
		return true;
		
	}
	
}

	