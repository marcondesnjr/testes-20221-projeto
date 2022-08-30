/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class Index implements Command{

      @Override
      public String execute (HttpServletRequest request, HttpServletResponse response){
        try {
            request.setAttribute("filmes", SisMovie.lastFilmes());
            return "pages/index.jsp";
        } catch (PersistenceException | SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,null,ex);
            return ErrorPages.PERSISTENCE_ERROR.getPAGE();
        }
    }

}
