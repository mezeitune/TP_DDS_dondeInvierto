package ui.vm;

import java.io.IOException;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import parser.parserArchivos.CSVToEmpresas;
import parserFormulaInidicador.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.EmpresasAEvaluarRepository;
import usuario.Cuenta;
import usuario.Empresa;
@Observable
public class AgregarPeriodoViewModel {

	private String periodo;
	private List<String> periodos;
	private static int codigoError;
	
	
	public static int getCodigoError(){
		return codigoError;
	}
	public AgregarPeriodoViewModel() {

		
	
	}


	
	public void setPeriodo(String periodoSeleccionada) throws IOException{
			EmpresasAEvaluarRepository.agregarPeriodoAEvaluar(periodoSeleccionada);
			AgregarPeriodoViewModel.codigoError=0;
		
		
	}
	
	public String getPeriodo(){
		return this.periodo;
	}

}
