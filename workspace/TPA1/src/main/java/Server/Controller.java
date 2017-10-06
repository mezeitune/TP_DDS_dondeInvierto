package Server;


import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Cuenta;
import usuario.Empresa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import mocks.EmpresasMock;
import repositorios.EmpresasRepository;

public class Controller {

public static String saludar(Request request, Response response) {
		
		String nombre = request.params("nombre");
		return "Hola" + nombre;
		
	}

public static ModelAndView consultarEmpresas(Request request, Response response) {
	List<Empresa> empresas = new LinkedList<Empresa>();
	//List<String> periodos = new LinkedList<String>();
	//empresas = EmpresasRepository.getEmpresas();
	//HashMap<Object,Object> viewModel = new HashMap <>();
	//empresas.forEach(empresa -> viewModel.put(empresa.getNombre(), Integer.toString(empresa.getCuentas().size())));
	//List<List> viewmodel = new LinkedList<List>();
	empresas = EmpresasRepository.getEmpresas();
	//periodos = EmpresasRepository.
	//viewmodel.add(empresas);
	//viewmodel.add
	
	return new ModelAndView(empresas, "empresas.hbs");
}

public static ModelAndView mostrarCuentas(Request request,Response response) {
	List<Cuenta> cuentas = new LinkedList<Cuenta>();
	List<Empresa> empresas = new LinkedList<Empresa>();
	List<List> viewModel = new LinkedList<List>();
	
	String nombreEmpresa = request.queryParams("empresa");
	String periodo = request.queryParams("periodo");
	
	empresas = EmpresasRepository.getEmpresas();
	Empresa empresa = empresas.stream().filter(e -> e.getNombre().equals(nombreEmpresa)).collect(Collectors.toList()).get(0);
	
	if(periodo.isEmpty()) cuentas = empresa.getCuentas();
	else cuentas = empresa.getCuentasPorPeriodo(periodo);
	
	viewModel.add(empresas);
	viewModel.add(cuentas);
	return new ModelAndView(viewModel,"cuentas.hbs");
}


}
