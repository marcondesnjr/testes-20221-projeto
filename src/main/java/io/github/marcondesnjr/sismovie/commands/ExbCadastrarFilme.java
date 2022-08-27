package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Genero;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ExbCadastrarFilme implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("generos", Genero.values());
        return "pages/cadastrar-filme.jsp"; 
    
    }
    
    
    
}
