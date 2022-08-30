package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.AlreadyExistsException;
import io.github.marcondesnjr.sismovie.dao.PhotoUpload;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorUsuario;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.UploadContext;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@MultipartConfig
@Getter @Setter
public class SingIn implements Command{

    private PhotoUpload uploader;
    private GerenciadorUsuario gerenciadorUsuario;
    private SisMovie sisMovie;

    public SingIn() {
        this.uploader = new PhotoUpload();
        this.gerenciadorUsuario = new GerenciadorUsuario();
        this.sisMovie = new SisMovie();
    }

    public static final String DIRETORY_PERFIL = "img_perfil";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        try {
            
            Map<String,String> param = new HashMap<>();
            
            File uploadedFile = uploader.upload(request, DIRETORY_PERFIL, "perfil", ".jpg", param);
            
            String nome = param.get("nome");
            String sobrenome = param.get("sobrenome");
            String email =  param.get("email");
            if(!email.contains("@")){
                throw new Exception("Email Invalido");
            }
            String senha =  param.get("senha");
            if(senha.isEmpty()){
                throw new Exception("Senha Invalida");
            }
            String data =  param.get("dataNasc");
            String cidade = param.get("cidade");
            String apelido = param.get("apelido");
            Estado est = sisMovie.getEstadoPelaSigla(param.get("estado"));
            LocalDate dataNasc = LocalDate.parse(data);
            if (dataNasc.isAfter(LocalDate.now())){
                throw new Exception("Data de nascimento Invalida");
            }
            String foto = uploadedFile != null? DIRETORY_PERFIL +"/"+ uploadedFile.getName(): null;
            Usuario usr = null;
            Usuario usrlog = (Usuario) request.getSession().getAttribute("usrLog");
            if(request.getParameter("adm") != null &&  param.get("adm").equals("1")
                    && usrlog != null && usrlog.getPermissao() == Permissao.ADMINISTRADOR){
                usr = new Usuario(nome, sobrenome, email, senha, dataNasc, cidade, est, Permissao.ADMINISTRADOR);
            }
            else{
                usr = new Usuario(nome, sobrenome, email, senha, dataNasc, cidade, est, Permissao.USUARIO);
            }
            usr.setFoto(foto);
            usr.setApelido(apelido);
            gerenciadorUsuario.salvar(usr);
            
            response.sendRedirect(request.getContextPath()+"/index/");
            return null;
        } catch (AlreadyExistsException ex) {
            throw new RuntimeException(ex);
        }catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,null,ex);
            return null;
        }

    }

    private RequestContext createRequestContext(HttpServletRequest req) {
        return new UploadContext() {
            @Override
            public String getCharacterEncoding() {
                return req.getCharacterEncoding();
            }

            @Override
            public String getContentType() {
                return req.getContentType();
            }

            @Override
            public InputStream getInputStream() throws IOException {
                InputStream in = req.getInputStream();
                if (in == null) {
                    throw new IOException("Missing content in the request");
                }
                return req.getInputStream();
            }

            @Override
            public long contentLength() {
                return req.getContentLength();
            }
        };
    }
}
