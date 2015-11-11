package setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.Set;

public class ApplicationStartUpListener implements ServletContextListener {

    private ServletContext context = null;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        context = servletContextEvent.getServletContext();
        Set<User> userSet = new HashSet<User>();
        context.setAttribute("userSet", userSet);
        ObjectMapper objectMapper = new ObjectMapper();
        context.setAttribute("objectMapper", objectMapper);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        context = null;
    }
}