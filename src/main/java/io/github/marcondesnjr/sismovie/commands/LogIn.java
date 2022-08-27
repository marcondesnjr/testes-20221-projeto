package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class LogIn implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        try {
            Usuario usrLog = SisMovie.realizarLogin(login, senha);
            request.getSession().setAttribute("usrLog", usrLog);
            if(usrLog.getPermissao() == Permissao.ADMINISTRADOR)
                request.getSession().setAttribute("adm", 1);
            response.sendRedirect(request.getContextPath()+"/index/");
            return null;
        }
        catch (IOException | SQLException | PersistenceException ex) {
            return ErrorPages.PERSISTENCE_ERROR.getPAGE();
        }
    }
}
