/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Amizade;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorGrupo;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorParticipantes;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorRecomandacao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class Home implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        try {
            new LoadEstados().execute(request, response);
            Usuario usr = (Usuario) request.getSession().getAttribute("usrLog");
            usr = GerenciadorParticipantes.carregarGrupos(usr);
            usr = GerenciadorRecomandacao.carregarRecomendacoes(usr);
            request.setAttribute("solicitacoes", Amizade.getSolicitacoesRecebidas(usr));
            request.setAttribute("usuarios", Amizade.getAmigos(usr));
            request.setAttribute("recomendacoes", usr.getRecomendacoes());
            return "pages/home.jsp";
        } catch (PersistenceException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,null,ex);
            return "persistenceError";
        }
    }
}
