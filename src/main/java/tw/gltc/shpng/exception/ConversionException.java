package tw.gltc.shpng.exception;

public class ConversionException extends  RuntimeException {
			
	private static final long serialVersionUID = -6674980959143400518L;

	public ConversionException(String message) {
		super(message);
	}
	
	public ConversionException(Exception e) {
		super(e);
	}
	
	public ConversionException(String errorMsg, Exception e) {
		super(errorMsg, e);
	}

}
