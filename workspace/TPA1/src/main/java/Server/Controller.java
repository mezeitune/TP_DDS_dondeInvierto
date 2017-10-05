package Server;


import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Empresa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import repositorios.EmpresasRepository;

public class Controller {

public static String saludar(Request request, Response response) {
		
		String nombre = request.params("nombre");
		return "Hola" + nombre;
		
	}

public static ModelAndView consultarEmpresas(Request request, Response response) {
	List<Empresa> empresas = new LinkedList<Empresa>();
	empresas = EmpresasRepository.getEmpresas();
	HashMap<Object,Object> viewModel = new HashMap <>();
	empresas.forEach(empresa -> viewModel.put(empresa.getNombre(), Integer.toString(empresa.getCuentas().size())));

	return new ModelAndView(viewModel, "empresas.hbs");
}


}
