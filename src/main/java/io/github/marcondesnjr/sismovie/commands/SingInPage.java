/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.sismovie.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class SingInPage implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
            new LoadEstados().execute(request, response);
            return "pages/singin.jsp";
    }
}
