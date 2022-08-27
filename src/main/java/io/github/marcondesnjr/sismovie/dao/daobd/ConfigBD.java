
package io.github.marcondesnjr.sismovie.dao.daobd;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ConfigBD {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/sismovie";
    private static final String NOME = "postgres";
    private static final String SENHA = "123";
    private static BasicDataSource ds;
    
    public static DataSource ConfigurarDataSource(){
        if(ds == null || ds.isClosed()){
            ds = new BasicDataSource();
            ds.setDriverClassName("org.postgresql.Driver");
            ds.setUrl(URL);
            ds.setUsername(NOME);
            ds.setPassword(SENHA);
            ds.setDefaultAutoCommit(false);
        }
        return ds;
    }
    
    public static void destroyDataSource(){
        if(ds != null){
            try {
                ds.close();
            } catch (SQLException ex) {}
        }
    }
}
