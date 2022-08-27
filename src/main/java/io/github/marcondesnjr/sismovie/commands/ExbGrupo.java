
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorFilme;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorGrupo;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorParticipantes;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorTopico;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ExbGrupo implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Grupo gp = GerenciadorGrupo.localizar(id);
            gp = GerenciadorParticipantes.carregarParticipantes(gp);
            gp = GerenciadorTopico.carragarTopicos(gp);
            request.setAttribute("grupo", gp);
            request.setAttribute("filmes", GerenciadorFilme.lastFilmes(10));
            Usuario usr = (Usuario) request.getSession().getAttribute("usrLog");
            if(gp.getParticipantes().contains(usr)){
                request.setAttribute("participante", true);
            }
            return "pages/exbgrupo.jsp";
        } catch (PersistenceException ex) {
            Logger.getLogger(ExbGrupo.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        }
    }
    
}
