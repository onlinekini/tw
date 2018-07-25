package tw.gltc.shpng.exception;

public class ItemException extends  Exception {
			
	private static final long serialVersionUID = -6674980959143400518L;

	public ItemException(String message) {
		super(message);
	}
	
	public ItemException(Exception e) {
		super(e);
	}
	
	public ItemException(String errorMsg, Exception e) {
		super(errorMsg, e);
	}

}
