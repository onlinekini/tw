package tw.mrchnt.guide.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ItemConfig {

	private Properties referenceProps;
	private static ItemConfig config;

	public static ItemConfig getReference() {
		if (config == null) {
			config = new ItemConfig();
		}
		return config;
	}

	private ItemConfig() {
		Properties props = new Properties();
		try {
			props.load(new FileReader(new File("items.properties")));
		} catch (FileNotFoundException e) {
			throw new ComputeException("Cannot process Config properties, please put items.properties in classpath");
		} catch (IOException e) {
			throw new ComputeException("Cannot read setup properties, does it a valid file ?");
		}
		referenceProps = props;
	}
	
	public String getProperty(String propName) {
		return referenceProps.getProperty(propName);
	}
}
