package Server;

import java.util.List;

import mocks.EmpresasMock;
import repositorios.EmpresasRepository;
import usuario.Empresa;

public class Loader {

	private static EmpresasRepository repositorio_empresas=new EmpresasRepository();
	
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
