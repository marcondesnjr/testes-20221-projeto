package io.github.marcondesnjr.sismovie.commands;


import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.SisMovie;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.AlreadyExistsException;
import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.dao.PhotoUpload;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorUsuario;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;


@ExtendWith(MockitoExtension.class)
class SingInTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private HttpServletRequest mockReq;
    @Mock
    private HttpServletResponse mockResp;
    @Mock
    private PhotoUpload photoUpload;
    @Mock
    private GerenciadorUsuario mockGerenciadorUsuario;
    @Mock
    private SisMovie sisMovie;
    private Usuario expectedUser;


    @BeforeEach
    void setUp() throws AlreadyExistsException, PersistenceException {

        this.expectedUser = new Usuario("nomeTest","sobrenomeTest","email@test","senhaTest",
                LocalDate.parse("1111-11-11"), "cidadeTest",Estado.AC, Permissao.USUARIO);


    }

    @AfterEach
    void tearDown() {
    }

    @InjectMocks
    private SingIn singIn;

    @DisplayName("Cadastra um usuário válido")
    @Test
    void executeValido() throws IOException, AlreadyExistsException, PersistenceException {

        Mockito.when(mockReq.getParameter("nome")).thenReturn(this.expectedUser.getNome());
        Mockito.when(mockReq.getParameter("sobrenome")).thenReturn(this.expectedUser.getSobrenome());
        Mockito.when(mockReq.getParameter("email")).thenReturn(this.expectedUser.getEmail());
        Mockito.when(mockReq.getParameter("senha")).thenReturn(this.expectedUser.getSenha());
        Mockito.when(mockReq.getParameter("dataNasc")).thenReturn(this.expectedUser.getDataNasc().toString());
        Mockito.when(mockReq.getParameter("cidade")).thenReturn(this.expectedUser.getCidade());
        Mockito.when(mockReq.getParameter("apelido")).thenReturn(this.expectedUser.getApelido());
        Mockito.when(mockReq.getParameter("estado")).thenReturn(this.expectedUser.getEstado().toString());

        Mockito.when(photoUpload.upload(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(null);
        Mockito.when(sisMovie.getEstadoPelaSigla(Mockito.anyString())).thenReturn(Estado.AC);
        Mockito.when(mockReq.getSession().getAttribute("usrLog")).thenReturn(null);
        Mockito.doNothing().when(mockGerenciadorUsuario).salvar(Mockito.any());


        ArgumentCaptor<Usuario> ac = ArgumentCaptor.forClass(Usuario.class);
        singIn.execute(mockReq,mockResp);
        Mockito.verify(mockGerenciadorUsuario).salvar(ac.capture());
        Assertions.assertEquals(ac.getValue(),this.expectedUser);
        Mockito.verify(mockResp).sendRedirect(Mockito.anyString());
    }

    @DisplayName("Não deve cadastrar com email invalido")
    @Test
    void executeEmailInvalido() throws IOException, AlreadyExistsException, PersistenceException {

        Mockito.when(mockReq.getParameter("nome")).thenReturn(this.expectedUser.getNome());
        Mockito.when(mockReq.getParameter("sobrenome")).thenReturn(this.expectedUser.getSobrenome());
        Mockito.when(mockReq.getParameter("email")).thenReturn("email");

        singIn.execute(mockReq,mockResp);
        Mockito.verify(mockGerenciadorUsuario,Mockito.never()).salvar(Mockito.any());
    }

    @DisplayName("Não deve cadastrar com data invalida")
    @Test
    void executeDataInvalida() throws IOException, AlreadyExistsException, PersistenceException {

        Mockito.when(mockReq.getParameter("nome")).thenReturn(this.expectedUser.getNome());
        Mockito.when(mockReq.getParameter("sobrenome")).thenReturn(this.expectedUser.getSobrenome());
        Mockito.when(mockReq.getParameter("email")).thenReturn(this.expectedUser.getEmail());
        Mockito.when(mockReq.getParameter("senha")).thenReturn(this.expectedUser.getSenha());
        Mockito.when(mockReq.getParameter("dataNasc")).thenReturn("Invalido");
        Mockito.when(mockReq.getParameter("cidade")).thenReturn(this.expectedUser.getCidade());
        Mockito.when(mockReq.getParameter("apelido")).thenReturn(this.expectedUser.getApelido());
        Mockito.when(mockReq.getParameter("estado")).thenReturn(this.expectedUser.getEstado().toString());

        Mockito.when(photoUpload.upload(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(null);
        Mockito.when(sisMovie.getEstadoPelaSigla(Mockito.anyString())).thenReturn(Estado.AC);

        singIn.execute(mockReq,mockResp);
        Mockito.verify(mockGerenciadorUsuario,Mockito.never()).salvar(Mockito.any());
    }

    @DisplayName("Não deve cadastrar com senha vazia")
    @Test
    void executeSenhaInvalida() throws IOException, AlreadyExistsException, PersistenceException {

        Mockito.when(mockReq.getParameter("nome")).thenReturn(this.expectedUser.getNome());
        Mockito.when(mockReq.getParameter("sobrenome")).thenReturn(this.expectedUser.getSobrenome());
        Mockito.when(mockReq.getParameter("email")).thenReturn(this.expectedUser.getEmail());
        Mockito.when(mockReq.getParameter("senha")).thenReturn("");


        singIn.execute(mockReq,mockResp);
        Mockito.verify(mockGerenciadorUsuario,Mockito.never()).salvar(Mockito.any());
    }

}