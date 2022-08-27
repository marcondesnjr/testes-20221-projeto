package io.github.marcondesnjr.sismovie.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String commandName = request.getParameter("command");
            if(request.getSession().getAttribute("usrLog") == null){
                if(!(commandName.matches("^Index$") || commandName.matches("^SingInPage") || commandName.matches("^SingIn") || commandName.matches("^LogIn")
                        || commandName.matches("^ExbFilme") || commandName.matches("^ExbBuscaFilme")
                        || commandName.matches("^BuscaFilme"))){
                    response.sendRedirect(request.getContextPath()+"/index/");
                    return;
                }
            }
            chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
    
}
