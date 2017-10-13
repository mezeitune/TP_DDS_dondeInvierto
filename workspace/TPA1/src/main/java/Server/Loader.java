package Server;

import java.util.List;

import mocks.EmpresasMock;
import repositorios.EmpresasRepository;
import usuario.Empresa;
import utilities.JPAUtility;

public class Loader {

	private static EmpresasRepository repositorio_empresas=new EmpresasRepository(JPAUtility.getInstance().getEntityManager());
	
	public static void main(String[] args) {
		new Loader().init();
	}
	
	public void init(){
		
		List<Empresa> empresas = new EmpresasMock().getEmpresasMockeadas();
		
		repositorio_empresas.begin();
		empresas.forEach(empresa -> repositorio_empresas.agregar(empresa));
		repositorio_empresas.commit();
	}
	
	
}
