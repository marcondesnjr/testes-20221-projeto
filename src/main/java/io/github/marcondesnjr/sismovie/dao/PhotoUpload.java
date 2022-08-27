package io.github.marcondesnjr.sismovie.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class PhotoUpload {

    public File upload(HttpServletRequest request, String diretory,
            String pre, String pos, Map param) {

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
            List<Object> list = null;
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String path = PhotoUpload.class.getClassLoader().getResource("").getPath();
                    String fullPath = URLDecoder.decode(path, "UTF-8");
                    String pathArr[] = fullPath.split("/WEB-INF/classes/");
                    fullPath = pathArr[0];
                    String imgPath = new File(fullPath).getPath() + File.separator
                            + diretory;
                    File uploadedDiretory = new File(imgPath);
                    uploadedFile = File.createTempFile(pre, pos, uploadedDiretory);
                    item.write(uploadedFile);
                } else {
                    String nome = item.getFieldName();
                    String val = item.getString();
                    param.put(item.getFieldName(), item.getString());
                }     
            }
            return uploadedFile;
        } catch (Exception ex) {
            Logger.getLogger(PhotoUpload.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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
