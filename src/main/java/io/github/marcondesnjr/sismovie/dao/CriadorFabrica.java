package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.dao.daobd.ConfigBD;
import io.github.marcondesnjr.sismovie.dao.daobd.ConnectionManager;
import io.github.marcondesnjr.sismovie.dao.daobd.DAOBDFabrica;
import java.sql.SQLException;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class CriadorFabrica {

    public static final int BANCO_DE_DADOS = 0;

    public static FabricaDAO criarFabrica(int tipo) throws PersistenceException {
        switch (tipo) {
            case 0: {
                try {
                    return new DAOBDFabrica(ConnectionManager.getConnection());
                } catch (SQLException ex) {
                    throw new PersistenceException(ex); 
                }
            }
            default:
                return null;
        }
    }
}
