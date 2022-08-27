package io.github.marcondesnjr.sismovie.commands;



import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerencidadorAmizade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ExbUsuario implements Command{

 
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        Usuario usrLog = (Usuario) request.getSession().getAttribute("usrLog");
        try{
            Usuario usr = SisMovie.localizarUsuario(email);
            if(usr != null){
                request.setAttribute("usuario", usr);
                boolean hasSol = GerencidadorAmizade.exists(usr, usrLog);
                request.setAttribute("hasSolicitacao", hasSol);
                return "pages/usr.jsp";         
            }
            else
                response.sendError(404);
            return null;
        }catch(PersistenceException ex){
            Logger.getLogger(ExbUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        } catch (IOException ex) {
            Logger.getLogger(ExbUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return "ioError";
        }
            
    }
}
