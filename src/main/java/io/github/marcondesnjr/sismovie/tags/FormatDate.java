
package io.github.marcondesnjr.sismovie.tags;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class FormatDate extends SimpleTagSupport {

    public static final String TIPO_USUARIO = "usuario";
    public static final String TIPO_FILME = "filme";
    
    private LocalDate date;
    
    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().print(date.getDayOfMonth()+"/"+date.getMonth().getValue()+"/"+date.getYear());
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
      
    }
    
}
