package metodologias;

import usuario.Empresa;

public class CondicionTaxativaAdapter implements EstadoCondicion{

	@Override
	public int evaluar(Empresa empresa1,Empresa empresa2,Condicion condicion) {
		
		CondicionTaxativa.getInstance().evaluar(empresa1,condicion.getParametroOperacionTaxativa());
		
		
		return 0;
	}

}
