package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Grupo;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorGrupo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class AddGrupo implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String nome = request.getParameter("nome");
            String desc = request.getParameter("descricao");
            Usuario usr = (Usuario) request.getSession().getAttribute("usrLog");
            Grupo gp = new Grupo(nome, desc, usr);
            GerenciadorGrupo.salvar(gp);
            response.sendRedirect(request.getContextPath()+"/grupo/"+gp.getId());
            return null;
        } catch (PersistenceException ex) {
            Logger.getLogger(AddGrupo.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        } catch (IOException ex) {
            Logger.getLogger(AddGrupo.class.getName()).log(Level.SEVERE, null, ex);
            return "ioError";
        }
    }
    
}
