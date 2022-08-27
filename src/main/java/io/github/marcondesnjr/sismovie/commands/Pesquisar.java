
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class Pesquisar implements Command{

    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        try {
            String nome = request.getParameter("nome");
            request.setAttribute("usuarios", SisMovie.pesquisarPorNome(nome));
            return "pages/usuarios.jsp";
        } catch (PersistenceException ex) {
            return ErrorPages.PERSISTENCE_ERROR.getPAGE();
        }
    }
}
