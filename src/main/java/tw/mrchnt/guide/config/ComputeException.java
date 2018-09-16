package tw.mrchnt.guide.config;

public class ComputeException extends  RuntimeException {
			
	private static final long serialVersionUID = -6674980959143400518L;

	public ComputeException(String message) {
		super(message);
	}
	
	public ComputeException(Exception e) {
		super(e);
	}
	
	public ComputeException(String errorMsg, Exception e) {
		super(errorMsg, e);
	}

}
