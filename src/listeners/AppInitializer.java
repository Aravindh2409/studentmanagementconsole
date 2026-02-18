package listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Student Management Web App Started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Student Management Web App Stopped");
    }
}
