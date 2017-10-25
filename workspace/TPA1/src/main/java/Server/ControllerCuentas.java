package Server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Cuenta;
import model.Empresa;
import repositorios.RepositorioEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerCuentas {

	private static RepositorioEmpresas repositorio_empresas=new RepositorioEmpresas();

	
	public static ModelAndView consultarEmpresas(Request request, Response response) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Empresa> empresas = repositorio_empresas.getEmpresas();
		
		parametros.put("usuario", request.session().attribute("usuario"));
		parametros.put("empresas", empresas);
		
		return new ModelAndView(parametros, "empresas/empresas.hbs");

	}
	
	public static ModelAndView mostrarCuentas(Request request,Response response) {
		List<Cuenta> cuentas = new LinkedList<Cuenta>();
		List<Empresa> empresas = new LinkedList<Empresa>();
		Map<String,Object> parametros = new HashMap<String,Object>();
		
		String nombreEmpresa = request.queryParams("empresa");
		String periodo = request.queryParams("periodo");
		
		empresas = repositorio_empresas.getEmpresas();
		Empresa empresa = empresas.stream().filter(e -> e.getNombre().equals(nombreEmpresa)).collect(Collectors.toList()).get(0);
		
		if(ControllerCuentas.validarPeriodo(request)) {
			parametros.put("usuario", request.session().attribute("usuario"));
			parametros.put("empresaSeleccionada", nombreEmpresa);
			parametros.put("empresas", empresas);
			return new ModelAndView(parametros,"empresas/alerta-periodo.hbs");
	
		}
		if(periodo.contains("all")) {
			cuentas = empresa.getCuentas();
			periodo = "No especificado";
		}
		else cuentas = empresa.getCuentasPorPeriodo(periodo);
		
		parametros.put("usuario", request.session().attribute("usuario"));
		parametros.put("empresaSeleccionada", nombreEmpresa);
		parametros.put("periodoSeleccionado",periodo);
		parametros.put("empresas",empresas);
		parametros.put("cuentas",cuentas);
		return new ModelAndView(parametros,"empresas/cuentas.hbs");
	}
	
	public static boolean validarPeriodo(Request request)
	{
		return request.queryParams().stream().filter(query -> query == "periodo").collect(Collectors.toList()).isEmpty();
	}
	
}
