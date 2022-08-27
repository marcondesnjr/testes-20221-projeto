package io.github.marcondesnjr.sismovie.gerenciadordados;

import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.AlreadyExistsException;
import io.github.marcondesnjr.sismovie.dao.CriadorFabrica;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.dao.DAOUsuario;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class GerenciadorUsuario {

    public void salvar(Usuario usr) throws PersistenceException, AlreadyExistsException {
        try (DAOUsuario dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDaoUsuario();) {
            dao.persistir(usr);
        }
    }

    public static Usuario localizar(String login, String senha) throws PersistenceException {
        try (DAOUsuario dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDaoUsuario();) {
            Usuario usr = dao.localizar(login, senha);
            return usr;
        }
    }

    public static Usuario localizar(String login) throws PersistenceException {
        DAOUsuario dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDaoUsuario();
        Usuario usr = dao.localizar(login);
        try {
            dao.close();
        } catch (Exception ex) {
        }
        return usr;
    }

    public static List<Usuario> localizar() throws PersistenceException {
        try (DAOUsuario dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDaoUsuario();) {
            List<Usuario> usrs = dao.localizar();
            try {
                dao.close();
            } catch (Exception ex) {
            }
            return usrs;
        }
    }

    public static List<Usuario> perquisarNome(String nome) throws PersistenceException {
        String[] nomes = nome.split(" ");
        Set<Usuario> usr = new HashSet<>();
        try (DAOUsuario dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS)
                .criarDaoUsuario();) {
            for (String nome1 : nomes) {
                usr.addAll(dao.perquisarNome(nome1));
            }
            return Arrays.asList(usr.toArray(new Usuario[0]));
        }
    }
    
    public static void atualizar(Usuario usr) throws PersistenceException{
        try (DAOUsuario dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDaoUsuario()){
            dao.editar(usr);
        }
    }
    
    public static void excluir(String email) throws PersistenceException{
       try (DAOUsuario dao = CriadorFabrica.criarFabrica(CriadorFabrica.BANCO_DE_DADOS).criarDaoUsuario()){
            dao.excluir(email);
        } 
    }

}
