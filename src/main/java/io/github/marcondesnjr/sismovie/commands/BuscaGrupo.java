package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorGrupo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class BuscaGrupo implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nome = request.getParameter("nome");
            List<Grupo> grupos = GerenciadorGrupo.localizarPorNome(nome);
            request.setAttribute("grupos", grupos);
            return "control?command=ExbBuscaGrupo";
        } catch (PersistenceException ex) {
            Logger.getLogger(BuscaGrupo.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        }
    }
    
}
