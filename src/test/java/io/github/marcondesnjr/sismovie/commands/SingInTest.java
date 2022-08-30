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
import java.util.Map;


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
        loadUserParams();
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

        this.expectedUser.setEmail("email");
        loadUserParams();
        singIn.execute(mockReq,mockResp);
        Mockito.verify(mockGerenciadorUsuario,Mockito.never()).salvar(Mockito.any());
    }

    @DisplayName("Não deve cadastrar com data invalida")
    @Test
    void executeDataInvalida() throws IOException, AlreadyExistsException, PersistenceException {

        expectedUser.setDataNasc(LocalDate.now().plusDays(1));
        loadUserParams();
        singIn.execute(mockReq,mockResp);
        Mockito.verify(mockGerenciadorUsuario,Mockito.never()).salvar(Mockito.any());
    }

    @DisplayName("Não deve cadastrar com senha vazia")
    @Test
    void executeSenhaInvalida() throws IOException, AlreadyExistsException, PersistenceException {

        this.expectedUser.setSenha("");
        loadUserParams();
        singIn.execute(mockReq,mockResp);
        Mockito.verify(mockGerenciadorUsuario,Mockito.never()).salvar(Mockito.any());
    }

    @DisplayName("Não deve cadastrar com email repetido")
    @Test
    void executeEmailRepetido() throws IOException, AlreadyExistsException, PersistenceException {
        Mockito.doThrow(new AlreadyExistsException("Usuario já cadastrado")).when(mockGerenciadorUsuario).salvar(Mockito.any());;
        loadUserParams();
        Assertions.assertThrows(RuntimeException.class, () -> singIn.execute(mockReq,mockResp));
    }

    private void loadUserParams() {
        Mockito.when(photoUpload.upload(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(Map.class)))
                .then(invocationOnMock -> {
                    Map <String,String> map = invocationOnMock.getArgument(4);
                    map.put("nome",this.expectedUser.getNome());
                    map.put("sobrenome",this.expectedUser.getSobrenome());
                    map.put("email",this.expectedUser.getEmail());
                    map.put("senha",this.expectedUser.getSenha());
                    map.put("dataNasc",this.expectedUser.getDataNasc().toString());
                    map.put("cidade",this.expectedUser.getCidade());
                    map.put("apelido",this.expectedUser.getApelido());
                    map.put("estado",this.expectedUser.getEstado().toString());
                    return null;
                });
    }


}