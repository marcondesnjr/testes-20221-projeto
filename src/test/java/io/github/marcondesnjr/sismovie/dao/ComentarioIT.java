package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.*;
import io.github.marcondesnjr.sismovie.dao.daobd.DAOBDComentario;
import lombok.AllArgsConstructor;
import org.dbunit.Assertion;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.Year;

@AllArgsConstructor
public class ComentarioIT extends DataSourceBasedDBTestCase {

    private DataSource dataSource;
    private ConnectionManager connectionManager;

    public ComentarioIT() {
        this.connectionManager = new ConnectionManager();
    }


    protected DataSource getDataSource() {
        if(!(this.dataSource == null)){
            return dataSource;
        }
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(connectionManager.URL);
        dataSource.setUser(connectionManager.NOME);
        dataSource.setPassword(connectionManager.SENHA);
        this.dataSource = dataSource;
        return dataSource;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("comentario_test_initial.xml"));
    }


    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    public void testAddComentario() throws Exception {
        DAOComentario daoComentario = new DAOBDComentario(getDataSource().getConnection());

        Usuario user1 = new Usuario("nome1", "sobrenome1", "email1@email.com", "123",
                LocalDate.parse("2000-12-12"),"cidade1", Estado.AC, Permissao.USUARIO);
        Filme filme1 = new Filme(null,"filme1","sinopse1", Year.parse("2000"));
        Topico topico1 = new Topico("titulo","comentario",user1,filme1);

        Comentario comentario = new Comentario("comentario",user1);
        daoComentario.persiste(comentario,topico1);

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("comentario_test_expected.xml"));
        ITable expectedTable = expectedDataSet.getTable("COMENTARIO");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("COMENTARIO");

        Assertion.assertEquals(expectedTable, actualTable);
    }

}
