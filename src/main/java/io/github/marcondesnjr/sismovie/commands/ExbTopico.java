package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Topico;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorComentario;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorTopico;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ExbTopico implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("tpid");
            request.setAttribute("tpid", id);
            Topico tp = GerenciadorTopico.localizar(Integer.parseInt(id));
            tp = GerenciadorComentario.carregarComentarios(tp);
            request.setAttribute("topico", tp);
            return "control?command=ExbGrupo&id=5&tpid="+id;
        } catch (PersistenceException ex) {
            Logger.getLogger(ExbTopico.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        }
    }
    
}
