
package io.github.marcondesnjr.sismovie;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public enum Genero {
    ACAO {}, ANIMACAO {}, AVENTURA {}, COMEDIA {}, DRAMA {}, FICCAO_CIENTIFICA {}, MUSICAL {}, TERROR {};
    
    public String getName(){
        return name();
    };
}
