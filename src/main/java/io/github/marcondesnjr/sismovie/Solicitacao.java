package io.github.marcondesnjr.sismovie;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class Solicitacao {
    private Usuario remetente;
    private Usuario destinatario;
    private int status;
    
    public static final int PENDENTE = 0;
    public static final int ACEITO = 1;

    public Solicitacao(Usuario remetente, Usuario destinatario) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.status = PENDENTE;
    }

    
    
    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
