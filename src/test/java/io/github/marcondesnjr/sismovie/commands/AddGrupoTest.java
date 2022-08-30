package io.github.marcondesnjr.sismovie.commands;

import io.github.marcondesnjr.sismovie.dao.PersistenceException;
import io.github.marcondesnjr.sismovie.gerenciadordados.GerenciadorGrupo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class AddGrupoTest {

    @Mock
    private GerenciadorGrupo mockGerenciadorGrupo;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private HttpServletRequest mockReq;
    @Mock
    private HttpServletResponse mockResp;
    @InjectMocks
    private AddGrupo addGrupo;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Não deve cadastrar Grupos com nome vazio")
    @Test
    void executeEmpty() throws PersistenceException {
        Mockito.when(mockReq.getParameter("nome")).thenReturn("");
        Assertions.assertEquals("persistenceError",addGrupo.execute(mockReq,mockResp));
        Mockito.verify(mockGerenciadorGrupo,Mockito.never()).salvar(Mockito.any());
    }

    @DisplayName("Não deve cadastrar Grupos com nome repetido")
    @Test
    void executeRepeted() throws PersistenceException {
        Mockito.when(mockReq.getParameter("nome")).thenReturn("nome");
        Mockito.when(mockReq.getParameter("descricao")).thenReturn("descricao");
        Mockito.doThrow(new PersistenceException("Repetido")).when(mockGerenciadorGrupo).salvar(Mockito.any());
        Assertions.assertEquals("persistenceError",addGrupo.execute(mockReq,mockResp));
    }
}