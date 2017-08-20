package ui.vm;

import org.uqbar.commons.utils.Observable;

import repositorios.EmpresasAEvaluarRepository;
@Observable
public class AgregarPeriodoViewModel {

	private String periodo;
	
	private static int codigoError = 0;
	private static String periodoIngresado;
	
	public static int getCodigoError(){
		return codigoError;
	}
	public AgregarPeriodoViewModel() {
	}

	public void setPeriodo(String periodoSeleccionado){
			if(periodoSeleccionado.length()==4)periodoIngresado = periodoSeleccionado;
	}
	
	public String getPeriodo(){
		return this.periodo;
	}
	
	public static void setPeriodoIngresado(){
		EmpresasAEvaluarRepository.agregarPeriodoAEvaluar(periodoIngresado);
		System.out.println("Agrego un periodo");
	}
	
	public static boolean esUnPeriodoYaIngresado(){
		return EmpresasAEvaluarRepository.esPeriodoRepetido(periodoIngresado);
	}
	
}
