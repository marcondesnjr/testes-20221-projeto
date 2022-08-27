package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.Filme;
import io.github.marcondesnjr.sismovie.Genero;
import io.github.marcondesnjr.sismovie.dao.PhotoUpload;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorFilme;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class AddFilme implements Command {

    private final String DIRETORY = "img_filme";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = request.getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(1000 * 1024);
            upload.setFileSizeMax(1000 * 1024);

            // Parse the request
            List<FileItem> items = upload.parseRequest(createRequestContext(request));
            File uploadedFile = null;
            Map<String,String> param = new HashMap<>();
            List<Genero> generos = new ArrayList<>();
            List<String> atores = new ArrayList<>();
            List<String> diretores = new ArrayList<>();
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String path = PhotoUpload.class.getClassLoader().getResource("").getPath();
                    String fullPath = URLDecoder.decode(path, "UTF-8");
                    String pathArr[] = fullPath.split("/WEB-INF/classes/");
                    fullPath = pathArr[0];
                    String imgPath = new File(fullPath).getPath() + File.separator
                            + DIRETORY;
                    File uploadedDiretory = new File(imgPath);
                    uploadedFile = File.createTempFile("filme", ".jpg", uploadedDiretory);
                    item.write(uploadedFile);
                } else {
                    String nome = item.getFieldName();
                    String val = item.getString();
                    if (nome.equals("genero"))
                        generos.add(Genero.valueOf(val));
                    else if(nome.equals("ator"))
                        atores.add(val);
                    else if(nome.equals("diretor"))
                        diretores.add(val);
                    else
                        param.put(item.getFieldName(), item.getString());
                }
            }
            
            String titulo = param.get("titulo");
            String sinopse = param.get("sinopse");
            String ano = param.get("ano");
            String foto = uploadedFile != null? DIRETORY + "/" + uploadedFile.getName(): null;
            Filme filme = new Filme(foto, titulo, sinopse, Year.parse(ano));
            filme.setAtores(atores);
            filme.setDiretores(diretores);
            filme.setGeneros(generos);
            GerenciadorFilme.salvar(filme);
            response.sendRedirect(request.getContextPath()+"/filme/"+filme.getId());
            return null;
        } catch (Exception ex) {
            Logger.getLogger(PhotoUpload.class.getName()).log(Level.SEVERE, null, ex);
            return "persistenceError";
        }
    }

    private static RequestContext createRequestContext(HttpServletRequest req) {
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
