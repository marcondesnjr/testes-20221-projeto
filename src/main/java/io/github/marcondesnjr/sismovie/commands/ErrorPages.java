
package io.github.marcondesnjr.sismovie.commands;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public enum ErrorPages {
    PERSISTENCE_ERROR("pages/erro-per.jsp"), NOT_FOUND("pages/erro-not-found.jsp");
    
    private final String PAGE;

    private ErrorPages(String PAGE) {
        this.PAGE = PAGE;
    }

    public String getPAGE() {
        return PAGE;
    }
    
}
