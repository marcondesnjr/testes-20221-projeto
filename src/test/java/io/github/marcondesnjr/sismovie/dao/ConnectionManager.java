package io.github.marcondesnjr.sismovie.dao;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {

    public final String URL;
    public final String NOME;
    public final String SENHA;
    public ConnectionManager() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/dbconfig.properties"));
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,null,e);
            throw new RuntimeException(e);
        }

        URL = properties.getProperty("db.url");
        NOME = properties.getProperty("db.user");
        SENHA = properties.getProperty("db.pass");
    }
}
