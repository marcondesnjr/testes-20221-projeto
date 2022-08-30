package io.github.marcondesnjr.sismovie.filters;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginFilterTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private HttpServletRequest mockReq;
    @Mock
    private HttpServletResponse mockResp;
    @Mock
    private FilterChain mockFilter;
    @InjectMocks
    private LoginFilter loginFilter;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Filtro deve permitir acesso a paginas publicas")
    @Test
    void doFilterPublic() throws ServletException, IOException {
        Mockito.when(mockReq.getParameter("command")).thenReturn("SingInPage");
        Mockito.when(mockReq.getSession().getAttribute("usrLog")).thenReturn(null);
        loginFilter.doFilter(mockReq,mockResp,mockFilter);
        Mockito.verify(mockFilter).doFilter(mockReq,mockResp);
        Mockito.when(mockReq.getParameter("command")).thenReturn("ExbBuscaFilme");
        Mockito.when(mockReq.getSession().getAttribute("usrLog")).thenReturn(null);
        loginFilter.doFilter(mockReq,mockResp,mockFilter);
        Mockito.verify(mockFilter,Mockito.atLeastOnce()).doFilter(mockReq,mockResp);
    }

    @DisplayName("Filtro deve bloquear acesso a paginas privadas sem login")
    @Test
    void doFilterPrivate() throws ServletException, IOException {
        Mockito.when(mockReq.getParameter("command")).thenReturn("AddFilme");
        Mockito.when(mockReq.getSession().getAttribute("usrLog")).thenReturn(null);
        loginFilter.doFilter(mockReq,mockResp,mockFilter);
        Mockito.verify(mockFilter,Mockito.never()).doFilter(mockReq,mockResp);
        Mockito.verify(mockResp).sendRedirect(Mockito.any());
    }

    @DisplayName("Filtro permitir paginas privadas com login")
    @Test
    void doFilterPrivateLogin() throws ServletException, IOException {
        Mockito.when(mockReq.getParameter("command")).thenReturn("AddFilme");
        Mockito.when(mockReq.getSession().getAttribute("usrLog")).thenReturn("UserID");
        loginFilter.doFilter(mockReq,mockResp,mockFilter);
        Mockito.verify(mockFilter).doFilter(mockReq,mockResp);
    }
}