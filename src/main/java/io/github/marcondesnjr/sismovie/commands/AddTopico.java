
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Topico;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorFilme;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorGrupo;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorTopico;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class AddTopico implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fmId = request.getParameter("filme-ref");
            Filme fm = GerenciadorFilme.localizar(Integer.parseInt(fmId));
            String gpId = request.getParameter("grupo");
            Grupo gp = GerenciadorGrupo.localizar(Integer.parseInt(gpId));
            Usuario usr = (Usuario) request.getSession().getAttribute("usrLog");
            String titulo = request.getParameter("titulo");
            String desc = request.getParameter("desc");
            Topico tp = new Topico(titulo, desc, usr, fm);
            GerenciadorTopico.salvar(tp, gp);
            gp.adicionarParticipante(usr);
            response.sendRedirect("control?command=ExbGrupo&id="+gp.getId());
            return null;
        } catch (PersistenceException ex) {
            Logger.getLogger(AddTopico.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        } catch (IOException ex) {
            Logger.getLogger(AddTopico.class.getName()).log(Level.SEVERE, null, ex);
            return "ioError";
        }
    }
    
}
