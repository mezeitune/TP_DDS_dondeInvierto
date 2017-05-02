package exceptions;

public class CSVMalFormadoException extends RuntimeException{

	
	
	  public CSVMalFormadoException() { super(); }
	  public CSVMalFormadoException(String message) { super(message); }
	  public CSVMalFormadoException(String message, Throwable cause) { super(message, cause); }
	  public CSVMalFormadoException(Throwable cause) { super(cause); }
	
}
