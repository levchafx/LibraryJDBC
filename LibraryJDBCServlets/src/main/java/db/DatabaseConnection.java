package db;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatabaseConnection {
	private static ComboPooledDataSource instance;

	private DatabaseConnection() {
	}

	public static DataSource getDataSource() {
		if (instance == null) {
			synchronized (DatabaseConnection.class) {
				final Properties properties = new Properties();
				try (InputStream resourceAsStream = DatabaseConnection.class.getResourceAsStream("/jdbc.properties")) {
					properties.load(resourceAsStream);
					final String url = properties.getProperty("url");
					final String userName = properties.getProperty("userName");
					final String password = properties.getProperty("password");
					final String driver = properties.getProperty("driver");
					instance = new ComboPooledDataSource();
					instance.setDriverClass(driver);
					instance.setJdbcUrl(url);
					instance.setUser(userName);
					instance.setPassword(password);
					instance.setMaxPoolSize(10);
				} catch (IOException | PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		}
		return instance;
	}

}
