
package io.github.marcondesnjr.sismovie.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class SingInPageAdm implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
            new LoadEstados().execute(request, response);
            return "pages/cadastrar-adm.jsp";
    }
}
