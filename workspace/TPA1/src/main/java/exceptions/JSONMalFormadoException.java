package exceptions;

public class JSONMalFormadoException extends RuntimeException{

	
	
	  public JSONMalFormadoException() { super(); }
	  public JSONMalFormadoException(String message) { super(message); }
	  public JSONMalFormadoException(String message, Throwable cause) { super(message, cause); }
	  public JSONMalFormadoException(Throwable cause) { super(cause); }
	
}
