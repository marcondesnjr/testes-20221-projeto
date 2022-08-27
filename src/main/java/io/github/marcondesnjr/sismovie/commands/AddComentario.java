
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Comentario;
import io.github.marcondesnjr.sismovie.Topico;
import io.github.marcondesnjr.sismovie.Usuario;
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
public class AddComentario implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String texto = request.getParameter("texto");
            Usuario usr = (Usuario) request.getSession().getAttribute("usrLog");
            String tid = request.getParameter("tid");
            Topico tp = GerenciadorTopico.localizar(Integer.parseInt(tid));
            Comentario com = new Comentario(texto, usr);
            com = GerenciadorComentario.salvar(com, tp);
            tp.addComentario(com);
            return "control?command=ExbTopico&tpid="+tp.getId();
        } catch (PersistenceException ex) {
            Logger.getLogger(AddComentario.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        }
    }
    
    
}
