package tw.gltc.shpng.exception;

public class SetupException extends Exception {
	
	private static final long serialVersionUID = -6674980959143400518L;

	public SetupException(String message) {
		super(message);
	}
	
	public SetupException(Exception e) {
		super(e);
	}
	
	public SetupException(String errorMsg, Exception e) {
		super(errorMsg, e);
	}
}
