package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.dao.daobd.ConfigBD;
import io.github.marcondesnjr.sismovie.dao.daobd.ConnectionManager;
import io.github.marcondesnjr.sismovie.dao.daobd.DAOBDFabrica;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                    Logger.getLogger(FabricaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw new PersistenceException(ex); 
                }
            }
            default:
                return null;
        }
    }
}
