package io.github.marcondesnjr.sismovie.commands;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class LogOff implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("usrLog", null);
            request.getSession().setAttribute("adm", 0);
            response.sendRedirect("control?command=Index");
            return null;
        } catch (IOException ex) {
            Logger.getLogger(LogOff.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
