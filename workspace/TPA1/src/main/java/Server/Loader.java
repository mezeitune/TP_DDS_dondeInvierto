package Server;

import java.util.List;

import javax.persistence.EntityManager;

import mocks.EmpresasMock;
import mocks.IndicadoresMock;
import model.Empresa;
import model.Indicador;
import model.Usuario;
import repositorios.DBRelacionalRepository;
import repositorios.EmpresasRepository;
import repositorios.IndicadoresRepository;
import repositorios.UsuariosRepository;

public class Loader {

	private static DBRelacionalRepository<EntityManager> repositorio_global = new DBRelacionalRepository<>();
	private static EmpresasRepository repositorio_empresas=new EmpresasRepository();
	private static IndicadoresRepository repositorio_indicadores=new IndicadoresRepository();
	private static UsuariosRepository repositorio_usuarios=new UsuariosRepository();
	
	public static void main(String[] args) {
		init();
	}
	
	public static void init(){
		List<Empresa> empresas = new EmpresasMock().getEmpresasMockeadas();
		List<Indicador> indicadores_root = new IndicadoresMock().getIndicadoresMockeados();
		Usuario root = new Usuario("root","root");
		
		indicadores_root.stream().forEach(indicador -> indicador.setUsuario(root));
		
		repositorio_global.begin();
		empresas.stream().forEach(empresa -> repositorio_empresas.agregar(empresa));
		indicadores_root.stream().forEach(indicador -> repositorio_indicadores.agregar(indicador));
		
		//usuarios.forEach(usuario -> repositorio_usuarios.agregar(usuario));
		
		repositorio_global.commit();
	}
	
	
}
