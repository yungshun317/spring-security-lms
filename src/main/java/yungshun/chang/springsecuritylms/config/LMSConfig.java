package yungshun.chang.springsecuritylms.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="yungshun.chang.springsecuritylms")
@PropertySource("classpath:persistence-mysql.properties")
public class LMSConfig {

    // Setup variable to hold the properties
    private final Environment env;

    // Constructor injection
    public LMSConfig(Environment env) {
        this.env = env;
    }

    // Setup a logger for diagnostics
    private Logger logger = Logger.getLogger(getClass().getName());

    // Define a bean for ViewResolver
    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    // Define a bean for security datasource
    @Bean
    public DataSource securityDataSource() {

        // Create connection pool
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        // Set the JDBC driver class
        try {
            securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // Log the connection props for sanity's sake, log this info just to make sure we are
        // really reading data from properties file
        logger.info(">>> jdbc.url=" + env.getProperty("jdbc.url"));
        logger.info(">>> jdbc.user=" + env.getProperty("jdbc.user"));

        // Set database connection props
        securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDataSource.setUser(env.getProperty("jdbc.user"));
        securityDataSource.setPassword(env.getProperty("jdbc.password"));

        // Set connection pool props
        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.minPoolSize"));
        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    // Helper method for reading environment property and convert it to `int`
    private int getIntProperty(String propName) {

        String propVal = env.getProperty(propName);

        // Now convert to `int`
        int intPropVal = 0;
        if (propVal != null) {
            intPropVal = Integer.parseInt(propVal);
        }

        return intPropVal;
    }
}
