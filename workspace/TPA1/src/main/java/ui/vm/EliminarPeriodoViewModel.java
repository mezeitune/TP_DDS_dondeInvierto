package ui.vm;

import java.util.List;

import repository.EmpresasAEvaluarRepository;

public class EliminarPeriodoViewModel {

	private String periodo;
	private static String periodoIngresado;
	
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodoSeleccionado){
		if(periodoSeleccionado.length()==4) periodoIngresado = periodoSeleccionado;
	}
	public List<String> getPeriodos() {
		return EmpresasAEvaluarRepository.getPeriodosAEvaluar();
	}
	
	public static boolean esUnPeriodoNoExistente(){
		return EmpresasAEvaluarRepository.esPeriodoInexistente(periodoIngresado);
	}
	
	public static void eliminarPeriodoIngresado(){
		EmpresasAEvaluarRepository.eliminarPeriodoaAEvaluar(periodoIngresado);
	}
	
	
}
