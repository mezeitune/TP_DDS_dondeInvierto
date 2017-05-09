package exceptions;

public class PathIncorrectoException extends RuntimeException {
	
	  public PathIncorrectoException() { super(); }
	  public PathIncorrectoException(String message) { super(message); }
	  public PathIncorrectoException(String message, Throwable cause) { super(message); }
	  public PathIncorrectoException(Throwable cause) { super(); }
}