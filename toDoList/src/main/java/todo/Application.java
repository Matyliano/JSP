package todo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Application implements ServletContextListener {

    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        factory = Persistence.createEntityManagerFactory("sda");
        entityManager = factory.createEntityManager();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        entityManager.close();
        factory.close();
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
