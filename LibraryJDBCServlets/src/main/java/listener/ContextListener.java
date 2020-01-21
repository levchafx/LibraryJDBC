package listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.flywaydb.core.Flyway;

@WebListener
public class ContextListener implements ServletContextListener {

	public ContextListener() {

	}

	public void contextInitialized(ServletContextEvent sce) {
		final Properties properties = new Properties();

		try (InputStream resourceAsStream = ContextListener.class.getResourceAsStream("/jdbc.properties")) {
			properties.load(resourceAsStream);
			final String url = properties.getProperty("url");
			final String userName = properties.getProperty("userName");
			final String password = properties.getProperty("password");

			Flyway flyway = Flyway.configure().dataSource(url, userName, password).load();

			flyway.migrate();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
