
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorFilme;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class BuscaFilme implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String titulo = request.getParameter("titulo");
            titulo = titulo.length() > 0? titulo:null;
            
            String gen = request.getParameter("genero");
            gen = gen.length() > 0? gen:null;
            
            String ordem = request.getParameter("ordem");
            
            String ator = request.getParameter("ator");
            ator = ator.length() > 0? ator:null;
            
            String diretor = request.getParameter("diretor");
            diretor = diretor.length() > 0? diretor:null;
            
            List<Filme> list = GerenciadorFilme.localizar(ordem, gen, ator, diretor, titulo, true);
            request.setAttribute("filmes", list);
            return "control?command=ExbBuscaFilme";
        } catch (PersistenceException ex) {
            Logger.getLogger(BuscaFilme.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        }
    }
    
}
