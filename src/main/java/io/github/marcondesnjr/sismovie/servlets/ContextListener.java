
package io.github.marcondesnjr.sismovie.servlets;

import io.github.marcondesnjr.sismovie.dao.daobd.ConfigBD;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConfigBD.destroyDataSource();
    }

    
    
}
