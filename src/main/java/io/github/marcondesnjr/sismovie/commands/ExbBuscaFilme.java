package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Genero;
import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorFilme;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ExbBuscaFilme implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getAttribute("filmes") == null){
                List<Filme> listFilme = GerenciadorFilme.localizar(GerenciadorFilme.ORDER_BY_TITULO, null, null, null,null,true);
                request.setAttribute("filmes", listFilme);
            }
            request.setAttribute("generos", Genero.values());
            return "pages/buscaFilme.jsp";
        } catch (PersistenceException ex) {
            Logger.getLogger(ExbBuscaFilme.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        }
    }
}
