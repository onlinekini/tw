package tw.mrchnt.guide.message;

import java.util.List;

import tw.mrchnt.guide.config.ComputeException;

public class Message implements MessageIfc {
	
	private final String originalRequestMessage;
	private final String preppedMessage;
	private final String[] decomposedMessage;
	private String[] sourceStrs;
	private String destinationStr;
	 
	
	public Message(String originalReqMessage, final DecomposerIfc messageDecomposer, final List<PrepperIfc> optionalMessagePreppers) {
		originalRequestMessage = originalReqMessage;
		
		// Do you need to prep the message ? use message preppers
		if (optionalMessagePreppers != null && !optionalMessagePreppers.isEmpty()) {
			String tempPreppedMessage = originalReqMessage;
			for (PrepperIfc prepper : optionalMessagePreppers) {
				tempPreppedMessage = prepper.prepMessage(tempPreppedMessage);
			}
			preppedMessage = tempPreppedMessage;
		} else {
			preppedMessage = originalReqMessage;
		}
		
		// decomposer is mandatory
		if (messageDecomposer == null) {
			throw new ComputeException("Message Decomposer needed to process the message");
		}
		decomposedMessage = messageDecomposer.decomposeMessage(preppedMessage);
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
