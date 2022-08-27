/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.commands.Command;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class LoadEstados implements Command{

    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setAttribute("estados", SisMovie.todosNomesEstados());
            return null;
        } catch (PersistenceException ex) {
            return "/pages/erro-per.jsp";
        }
    }
}
