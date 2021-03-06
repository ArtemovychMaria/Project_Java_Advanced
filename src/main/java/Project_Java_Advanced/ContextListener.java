package Project_Java_Advanced;

import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener("application context listener to init log4j")
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // initialize log4j here
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        DOMConfigurator.configure(fullPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // do nothing
    }
}
