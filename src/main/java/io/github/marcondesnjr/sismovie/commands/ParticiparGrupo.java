
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorGrupo;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorParticipantes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ParticiparGrupo implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String grupoid = request.getParameter("id");
            Grupo gp = GerenciadorGrupo.localizar(Integer.parseInt(grupoid));
            Usuario usr = (Usuario) request.getSession().getAttribute("usrLog");
            GerenciadorParticipantes.salvar(gp, usr);
            gp.adicionarParticipante(usr);
            usr.addGrupo(gp);
            response.sendRedirect(request.getContextPath()+"/grupo/"+gp.getId());
            return null;
        } catch (PersistenceException ex) {
            Logger.getLogger(ParticiparGrupo.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        } catch (IOException ex) {
            Logger.getLogger(ParticiparGrupo.class.getName()).log(Level.SEVERE, null, ex);
            return "ioError";
        }
    }
    
}
