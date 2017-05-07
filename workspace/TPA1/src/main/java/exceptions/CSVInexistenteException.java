package exceptions;

public class CSVInexistenteException extends RuntimeException{

	
	
	  public CSVInexistenteException() { super(); }
	  public CSVInexistenteException(String message) { super(message); }
	  public CSVInexistenteException(String message, Throwable cause) { super(message, cause); }
	  public CSVInexistenteException(Throwable cause) { super(cause); }
	
}
