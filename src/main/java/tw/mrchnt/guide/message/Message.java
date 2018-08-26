package tw.mrchnt.guide.message;

public class Message implements MessageIfc {
	
	private final String originalRequestMessage;
	private final String preppedMessage;
	private final String[] decomposedMessage;
	private DecomposerIfc decomposer;
	private PrepperIfc messagePrepper;
	private String[] sourceStrs;
	private String destinationStr;
	 
	
	public Message(String originalReqMessage, final DecomposerIfc inDecomposer, final PrepperIfc messagePrepper) {
		originalRequestMessage = originalReqMessage;
		this.messagePrepper = messagePrepper;
		if (this.messagePrepper != null) {
			preppedMessage = this.messagePrepper.prepMessage(originalReqMessage);
		} else {
			preppedMessage = originalReqMessage;
		}
		decomposer = inDecomposer;
		decomposedMessage = decomposer.decomposeMessage(preppedMessage);
	}
	
	@Override
	public String getMessageString() {
		return preppedMessage;
	}

	@Override
	public String[] getDecomposedMessage() {
		return decomposedMessage;
	}
	
	
	public String getOriginalRequestMessage() {
		return originalRequestMessage;
	}

	public String[] getSourceStrs() {
		return sourceStrs;
	}

	public void setSourceStrs(String[] sourceStrs) {
		this.sourceStrs = sourceStrs;
	}

	public String getDestinationStr() {
		return destinationStr;
	}

	public void setDestinationStr(String destinationStr) {
		this.destinationStr = destinationStr;
	}
	
}
