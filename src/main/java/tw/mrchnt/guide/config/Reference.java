package tw.mrchnt.guide.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Reference {

	private Properties referenceProps;
	private static Reference ref;

	public static Reference getReference() {
		if (ref == null) {
			ref = new Reference();
		}
		return ref;
	}

	private Reference() {
		Properties props = new Properties();
		try {
			props.load(new FileReader(new File("setup.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		referenceProps = props;
	}
	
	public String getProperty(String propName) {
		return referenceProps.getProperty(propName);
	}
}
