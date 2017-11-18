package Server;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import metodologiasPredefinidas.WarrenBuffet;
import mocks.EmpresasMock;
import mocks.IndicadoresMock;
import model.Empresa;
import model.Indicador;
import model.Metodologia;
import model.Usuario;
import repositorios.RepositorioDBRelational;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioIndicadores;
import repositorios.RepositorioMetodologias;
import repositorios.RepositorioUsuarios;

public class Loader {

	private static RepositorioDBRelational<EntityManager> repositorio_global = new RepositorioDBRelational<>();
	private static RepositorioEmpresas repositorio_empresas=new RepositorioEmpresas();
	private static RepositorioIndicadores repositorio_indicadores=new RepositorioIndicadores();
	private static RepositorioMetodologias repositorio_metodologias = new RepositorioMetodologias();
	private static RepositorioUsuarios repositorio_usuarios=new RepositorioUsuarios();
	
	public static void main(String[] args) {
		init();
	}
	
	public static void init(){
		List<Empresa> empresas = new EmpresasMock().getEmpresasMockeadas();
		
		List<Indicador> indicadores_root = new IndicadoresMock().getIndicadoresMockeados();
		
		List<Metodologia> metodologias_root = new LinkedList<Metodologia>();
		metodologias_root.add(WarrenBuffet.getInstance());
		
		List<Usuario> usuarios = new LinkedList<Usuario>();
		
		Usuario root = new Usuario("root","root");
		usuarios.add(root);
		
		indicadores_root.stream().forEach(indicador -> indicador.setUsuario(root));
		metodologias_root.stream().forEach(metodologia -> metodologia.setUsuario(root));
		
		repositorio_global.begin();
		usuarios.forEach(usuario -> repositorio_usuarios.agregar(usuario));
		empresas.stream().forEach(empresa -> repositorio_empresas.agregar(empresa));
		indicadores_root.stream().forEach(indicador -> repositorio_indicadores.agregar(indicador));
		metodologias_root.stream().forEach(metodologia-> repositorio_metodologias.agregar(metodologia));
		
		repositorio_global.commit();
		
	}	
	
}
