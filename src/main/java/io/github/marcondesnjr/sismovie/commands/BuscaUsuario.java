package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class BuscaUsuario implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setAttribute("usuarios", SisMovie.todosUsuarios());
            return "pages/usuarios.jsp";
        } catch (PersistenceException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,null, ex);
            return ErrorPages.PERSISTENCE_ERROR.getPAGE();
        }
    }
}
