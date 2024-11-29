package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

	private Properties properties;

	public static AppConfig appConfig;

	private AppConfig() {
		initConfig();
	}

	public static AppConfig getInstance() {
		return (appConfig == null) ? appConfig = new AppConfig() : appConfig;
	}

	private void initConfig() {

		try (InputStream propertiesFile = AppConfig.class.getClassLoader().getResourceAsStream("config.properties");) {

			properties = new Properties();
			properties.load(propertiesFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
