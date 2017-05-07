package exceptions;

import java.io.IOException;

public class CSVMalFormadoException extends IOException {
	
	
	  public CSVMalFormadoException() { super(); }
	  public CSVMalFormadoException(String message) { super(message); }
	  public CSVMalFormadoException(String message, Throwable cause) { super(message); }
	  public CSVMalFormadoException(Throwable cause) { super(); }
}
