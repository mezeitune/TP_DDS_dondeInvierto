package metodologias;

import java.util.Calendar;

import parserFormulaInidicador.ParserFormulaToIndicador;
import usuario.Empresa;
import usuario.Indicador;

public class CondicionComparativa implements EstadoCondicion{

	
	private static CondicionComparativa instance ;




	public static CondicionComparativa getInstance( ) {
        if(instance == null){
            instance = new CondicionComparativa();
        }
        return instance;

	}	
	
	@Override
	public int evaluar(Empresa empresa1, Empresa empresa2, Condicion condicion) {
		ParametroOperacion parametroOperacion=condicion.getParametroOperacionComparativa();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		Indicador indicadorAEvaluar=parametroOperacion.getParametro();
		ParserFormulaToIndicador.setEmpresa(empresa2);
		ParserFormulaToIndicador.setPeriodo(String.valueOf(year));
		int aux=indicadorAEvaluar.calcular();
		int ver=1;
		ParserFormulaToIndicador.setEmpresa(empresa1);
		for(int i=0;i<parametroOperacion.getLonngevidadAEvaluar();i++){
			ParserFormulaToIndicador.setPeriodo(String.valueOf(year-i));
			ParserFormulaToIndicador.setEmpresa(empresa1);
			if(indicadorAEvaluar.calcular()>aux){	//En vez del > habria que ver la forma der poner el parametroOperacion.getOperacion()
				ParserFormulaToIndicador.setEmpresa(empresa2);
				aux=indicadorAEvaluar.calcular();
				
			}else{
				ver=0;
			}
			
		}
		
		
		
		return ver;
		
	}

}
