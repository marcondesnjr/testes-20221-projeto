package io.github.marcondesnjr.sismovie.dao.daobd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ConnectionManager {

    private static final String URL;
    private static final String NOME;
    private static final String SENHA;

    static{
        Properties properties = new Properties();
        try {
            properties.load(ConnectionManager.class.getResourceAsStream("/dbconfig.properties"));
            String systemUrl = System.getenv("DB_URL");
            URL = systemUrl == null? properties.getProperty("db.url"): systemUrl;

            String systemNome = System.getenv("DB_NOME");
            NOME = systemNome == null? properties.getProperty("db.user"): systemNome;

            String systemSenha = System.getenv("DB_SENHA");
            SENHA = systemSenha == null? properties.getProperty("db.pass"): systemSenha;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = DriverManager.getConnection(URL,NOME,SENHA);
        conn.setAutoCommit(false);
        return conn;
    }
    
}
