package Logger;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.io.IOException;

public class LogPrecalculoIndicadores {
	
	private static Logger logger;
	private static FileHandler fileHandler;
	
	public LogPrecalculoIndicadores() throws SecurityException, IOException {
		logger = Logger.getLogger(WriteLock.class.getName());
		fileHandler =  new FileHandler("indicadores_precalculados.log", true);
		logger.addHandler(fileHandler);
	}
	
	public void loguearPrecalculoInvalido(String indicador,String empresa,String periodo,String cuenta){
		logger.warning("El indicador "+indicador+" no pudo ser calculado en la empresa "+empresa+
						" para el periodo "+periodo+" porque no tiene la cuenta "+cuenta);

	    }

	}
