package io.github.marcondesnjr.sismovie;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.Part;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ArquivoManager {
    public static final String DEFAUT_FOTO_PERFIL = "img\nemsei.jpg";

    public static String salvarFotoPerfil(Part foto, String email) throws IOException {
        Path p = Paths.get("img", email);
        try (FileOutputStream fOut = new FileOutputStream(p.toAbsolutePath().toFile())) {
            try (InputStream ins = foto.getInputStream()) {
                int nextBit = ins.read();
                while (nextBit != -1) {
                    fOut.write(nextBit);
                    nextBit = ins.read();
                }
            }
        }
        return p.toFile().getPath();
    }

    public static String salvarFotoFilme(Part fotoPart, String titulo) {
        return "Still";
    }
}
