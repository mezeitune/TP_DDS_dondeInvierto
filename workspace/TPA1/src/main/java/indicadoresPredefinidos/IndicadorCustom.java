package indicadoresPredefinidos;

import org.uqbar.commons.utils.Observable;

import usuario.Indicador;
@Observable
public class IndicadorCustom extends Indicador  {

	public IndicadorCustom(String nombre,String formula)  {
		super(nombre,formula);
	}
	
}
