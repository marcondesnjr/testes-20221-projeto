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

    private static String URL;
    private static String NOME;
    private static String SENHA;

    static{
        Properties properties = new Properties();
        try {
            properties.load(ConnectionManager.class.getResourceAsStream("/dbconfig.properties"));
            URL = properties.getProperty("db.url");
            NOME = properties.getProperty("db.user");
            SENHA = properties.getProperty("db.pass");
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
    
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("objeto", new Object());
        map.get("objeto");
    }
    
}
