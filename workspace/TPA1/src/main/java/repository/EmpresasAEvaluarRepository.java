package repository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import parser.parserArchivos.CSVToEmpresas;
import parser.parserArchivos.ParserJsonAEmpresaAdapter;
import parserFormulaInidicador.ParserFormulaToIndicador;
import usuario.Empresa;
import usuario.Indicador;

public class EmpresasAEvaluarRepository {
	public static List<Empresa> empresasAEvaluar = new LinkedList<>(); 
	
	public static List<Empresa> getEmpresasAEvaluar() {
		return empresasAEvaluar;
	}

	public static void agregarEmpresaAEvaluar(Empresa empresaAEvaluar) {
			EmpresasAEvaluarRepository.empresasAEvaluar.add(empresaAEvaluar);
	}
	public static void vaciarListaDeEmpresasAEvaluar() {
		EmpresasAEvaluarRepository.empresasAEvaluar.removeAll(empresasAEvaluar);
	}

	public static void eliminarEmpresaAEvaluar(Empresa unaEmpresa) {
		EmpresasAEvaluarRepository.empresasAEvaluar.remove(unaEmpresa);
		
	}

	public static void llenarListaDeEmpresasAEvaluar() {
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		try {
			EmpresasAEvaluarRepository.empresasAEvaluar.removeAll(empresasAEvaluar);
			empresasAEvaluar=parser.csvFileToEmpresas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
