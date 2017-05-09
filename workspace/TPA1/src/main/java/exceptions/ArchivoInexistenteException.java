package exceptions;

public class ArchivoInexistenteException extends RuntimeException {
	
	  public ArchivoInexistenteException() { super(); }
	  public ArchivoInexistenteException(String message) { super(message); }
	  public ArchivoInexistenteException(String message, Throwable cause) { super(message); }
	  public ArchivoInexistenteException(Throwable cause) { super(); }
}