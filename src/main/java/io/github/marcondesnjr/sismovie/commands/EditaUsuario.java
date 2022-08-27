/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.AlreadyExistsException;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.dao.PhotoUpload;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorUsuario;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@MultipartConfig
@Getter @Setter
public class EditaUsuario implements Command{

    private PhotoUpload uploader;
    private SisMovie sisMovie;

    public EditaUsuario(){
        this.uploader = new PhotoUpload();
        this.sisMovie = new SisMovie();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String,String> param = new HashMap<>();
            
            File uploadedFile = uploader.upload(request, SingIn.DIRETORY_PERFIL, "perfil", ".jpg", param);
            
            String nome = param.get("nome");
            String sobrenome = param.get("sobrenome");
            String senha =  param.get("senha");
            String senhaOld = param.get("senhaOld");
            String data =  param.get("dataNasc");
            String cidade = param.get("cidade");
            String apelido = param.get("apelido");
            Estado est = sisMovie.getEstadoPelaSigla(param.get("estado"));
            LocalDate dataNasc = null;
            if(!data.equals(""))
                dataNasc = LocalDate.parse(data);
            String foto = uploadedFile != null? SingIn.DIRETORY_PERFIL +"/"+ uploadedFile.getName(): null;
            
            Usuario usrLog = (Usuario) request.getSession().getAttribute("usrLog");
            Usuario usr = GerenciadorUsuario.localizar(usrLog.getEmail(), senhaOld);
            if(usr != null){
                if(!foto.equals(""))
                    usr.setFoto(foto);
                if(!nome.equals(""))
                    usr.setNome(nome);
                if(!sobrenome.equals(""))
                    usr.setSobrenome(sobrenome);
                if(senha.equals(""))
                    usr.setSenha(senhaOld);
                else
                    usr.setSenha(senha);
                if(!data.equals(""))
                    usr.setDataNasc(dataNasc);
                if(!cidade.equals(""))
                    usr.setCidade(cidade);
                if(est != null)
                    usr.setEstado(est);
                usr.setApelido(apelido);
                GerenciadorUsuario.atualizar(usr);
                request.getSession().setAttribute("usrLog", usr);
            }
            
            response.sendRedirect(request.getContextPath()+"/home/");
            return null;
        } catch (PersistenceException | IOException ex) {
            Logger.getLogger(EditaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            try{response.sendError(600);}catch(Exception e){}
            return null;
        }
    }
    
    
    
}
