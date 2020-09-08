package mx.com.gentera.ms.tokenproxy.exception;

public class SecurityAccesException extends Exception {

	private static final long serialVersionUID = 5429004045808710233L;
	private static final String message = "Security exception, you dont have permission to use this service";
	
	public SecurityAccesException() {
		super(message);
	}
}
