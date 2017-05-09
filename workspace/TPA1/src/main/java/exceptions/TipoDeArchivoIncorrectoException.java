package exceptions;

public class TipoDeArchivoIncorrectoException extends RuntimeException {
	
	  public TipoDeArchivoIncorrectoException() { super(); }
	  public TipoDeArchivoIncorrectoException(String message) { super(message); }
	  public TipoDeArchivoIncorrectoException(String message, Throwable cause) { super(message); }
	  public TipoDeArchivoIncorrectoException(Throwable cause) { super(); }
}