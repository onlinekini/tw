package tw.mrchnt.guide.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

	private Properties referenceProps;
	private static Config config;

	public static Config getReference() {
		if (config == null) {
			config = new Config();
		}
		return config;
	}

	private Config() {
		Properties props = new Properties();
		try {
			props.load(new FileReader(new File("setup.properties")));
		} catch (FileNotFoundException e) {
			throw new ComputeException("Cannot process Config properties, please put setup.properties in classpath");
		} catch (IOException e) {
			throw new ComputeException("Cannot read setup properties, does it a valid file ?");
		}
		referenceProps = props;
	}
	
	public String getProperty(String propName) {
		return referenceProps.getProperty(propName);
	}
}
