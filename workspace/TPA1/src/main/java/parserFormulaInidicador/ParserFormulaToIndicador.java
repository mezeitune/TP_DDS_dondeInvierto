package parserFormulaInidicador;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import excepciones.AccountNotFoundException;
import parser.parserArchivos.CSVToEmpresas;
import parser.parserArchivos.ParserJsonAObjetosJava;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.EmpresasAEvaluarRepository;
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
	
	private static String operadorSuma = "(.*)[+](.*)";
	private static String operadorResta = "(.*)[-](.*)";
	private static String operadorMultiplicacion = "(.*)[*](.*)";
	private static String operadorDivision = "(.*)[/](.*)";
	
	
	/*TODO: Tratar de pasarle los indicadores y las cuentas por constructor, asi limpiamos el metodo INIT que es solo para testear*/
	public ParserFormulaToIndicador() {
		ParserJsonAObjetosJava parserEmpIndicador = new ParserJsonAObjetosJava("indicadores.json");
		CSVToEmpresas parserCuentas = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		
		setIndicadoresDefinidosPorUsuario();
		ArchivoEIndicadoresUsuarioRepository.setIndicadoresDefinidosPorElUsuario(parserEmpIndicador.getIndicadoresDelArchivo());
		ArchivoEIndicadoresUsuarioRepository.cargarIndicadoresPredefinidos();
		
		
		/*Obtengo todas las cuentas de las empresas del csv*/
		empresas=parserCuentas.csvFileToEmpresas();
		for(int i=0;i<empresas.size();i++){
			cuentas.addAll(empresas.get(i).getCuentas());
		}
	}


	private static void setIndicadoresDefinidosPorUsuario() {
		indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
	}
	
	
	/*Para testear*/
	public static void init(List<Indicador> indicadoresTest, List<Cuenta> cuentasTest){
		indicadores = indicadoresTest;
		cuentas = cuentasTest;
		cuentasPorPeriodo = cuentasTest; 
	}
	
	public static void init(List<Cuenta> cuentasTest){
		cuentas = cuentasTest;
	}
	
	public static void setEmpresa(Empresa unaEmpresa){
		empresa= unaEmpresa;
	}
	
	public static Empresa getEmpresa(){
		return ParserFormulaToIndicador.empresa;
	}
	
	public static void setPeriodo(String unPeriodo){
		periodo=unPeriodo;
		cuentasPorPeriodo = empresa.getCuentasPorPeriodo(periodo);
	}
	
	public static int getCalculoIndicador(String formula) throws AccountNotFoundException{
			return ParserFormulaToIndicador.construirArbolOperaciones(formula).calcular();
	}
	
	public static Operacion construirArbolOperaciones(String operandos) throws AccountNotFoundException{

				if(operandos.matches(operadorSuma)) return ParserFormulaToIndicador.getOperacion(operandos.split("[+]"),new Suma());
				if(operandos.matches(operadorResta)) return ParserFormulaToIndicador.getOperacion(operandos.split("[-]"),new Resta());
				if(operandos.matches(operadorMultiplicacion)) return ParserFormulaToIndicador.getOperacion(operandos.split("[*]"),new Multiplicacion());
				if(operandos.matches(operadorDivision)) return ParserFormulaToIndicador.getOperacion(operandos.split("[/]"),new Division());
				
				return ParserFormulaToIndicador.getClaseOperador(operandos);
				
		}
	
	public static Operacion getOperacion(String [] formula,Operacion nuevaOperacion) throws AccountNotFoundException{
		List<String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}
		nuevaOperacion.setOperador1(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		
		for(i=1;i<=operandos.size() && operandos.size()!= 1;i+=2){
			operandos.add(i,"/");
		}
		
		nuevaOperacion.setOperador2(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		
		return nuevaOperacion;
	}
	
	
	public static Operacion getClaseOperador(String operador) throws AccountNotFoundException{
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
	
	
	
	public static Cuenta buscarYObtenerCuenta(String operador) throws AccountNotFoundException{
		if(!cuentasPorPeriodo.stream().anyMatch(cuenta -> cuenta.getNombre().equals(operador))) throw new AccountNotFoundException();
		return cuentasPorPeriodo.stream().filter(cuenta -> cuenta.getNombre().equals(operador)).collect(Collectors.toList()).get(0);
	}
	
	public static boolean validarIndicadorRepetidoAntesDePrecargar(String nombre , String formula) throws IOException {
		List<Indicador> indicadoresRepetidos = indicadores.stream().filter(line -> line.getNombre().equals(nombre)).collect(Collectors.toList());
		
		if (indicadoresRepetidos.size() >= 1){
			return true;
		} else {
			return false;
		}
	}
	public static boolean validarEmpresaRepetidaAntesDePrecargar(Empresa unaEmpresa) throws IOException {
		
		List<Empresa> empresaRepetida = EmpresasAEvaluarRepository.empresasAEvaluar.stream().filter(line -> line.getNombre().equals(unaEmpresa.getNombre())).collect(Collectors.toList());
		
		if (empresaRepetida.size() >= 1){
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean validarAntesDePrecargar(String formula) throws IOException{ 
		setIndicadoresDefinidosPorUsuario();
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

	


/*
	public static Division getDivision(String [] formula){

		Division division = new Division();
		List <String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}

		division.setOperador1(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));

		for(i=1;i<=operandos.size() && operandos.size()!= 1;i+=2){
			operandos.add(i,"/");
		}
		
		String formulaString = "";
		for(i=0;i<operandos.size();i++){
			formulaString += operandos.get(i);
		}

		try{
			division.setOperador2(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		}catch(IndexOutOfBoundsException e){}

		return division;
		}

	public static Multiplicacion getMultiplicacion(String [] formula){

		Multiplicacion multiplicacion = new Multiplicacion();
		List <String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}

		multiplicacion.setOperador1(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));

		for(i=1;i<=operandos.size() && operandos.size()!= 1;i+=2){
			operandos.add(i,"*");
		}
		
		
		String formulaString = "";
		for(i=0;i<operandos.size();i++){
			formulaString += operandos.get(i);
		}
		try{
			multiplicacion.setOperador2(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		}catch(IndexOutOfBoundsException e){}
		
		return multiplicacion;
		
	}

	public static Resta getResta(String [] formula){

		Resta resta = new Resta();
		List <String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}

		resta.setOperador1(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));

		for(i=1;i<=operandos.size() && operandos.size()!= 1;i+=2){
			operandos.add(i,"-");
		}
		
		String formulaString = "";
		for(i=0;i<operandos.size();i++){
			formulaString += operandos.get(i);
		}

		try{
			resta.setOperador2(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		}catch(IndexOutOfBoundsException e){}
		
		return resta;
	
	}

	
	
	public static Suma getSuma(String [] formula){

		Suma suma = new Suma();
		List <String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}

		suma.setOperador1(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		
		for(i=1;i<=operandos.size() && operandos.size()!= 1;i+=2){
			operandos.add(i,"+");
		}
		
		
		String formulaString = "";
		for(i=0;i<operandos.size();i++){
			formulaString += operandos.get(i);
		}

		try{
			suma.setOperador2(ParserFormulaToIndicador.construirArbolOperaciones(formulaString));
		}catch(IndexOutOfBoundsException e){}
		
		return suma;
	}
	
 */
