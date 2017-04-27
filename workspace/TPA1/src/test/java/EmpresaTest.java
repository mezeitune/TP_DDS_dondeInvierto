import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


import parser.ParserJsonAEmpresaAdapter;
import usuario.Empresa;


import static org.mockito.Mockito.when;   // ...or...

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;      // ...with the caveat noted below.


public class EmpresaTest {

	
	
	@Test
	public void ListaAsignadaCorrectamenteConParser(){
		ParserJsonAEmpresaAdapter parserMock=Mockito.mock(ParserJsonAEmpresaAdapter.class);
		List<Empresa> someList = new ArrayList<Empresa>();

	    Mockito.doReturn(someList).when(parserMock).getEmpresasDelArchivo();

		Assert.assertEquals(someList, parserMock.getEmpresasDelArchivo());
		
		
	}
	
	
}
