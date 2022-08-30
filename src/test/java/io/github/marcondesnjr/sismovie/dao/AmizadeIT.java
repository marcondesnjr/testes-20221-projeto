package io.github.marcondesnjr.sismovie.dao;

import io.github.marcondesnjr.sismovie.Estado;
import io.github.marcondesnjr.sismovie.Permissao;
import io.github.marcondesnjr.sismovie.Solicitacao;
import io.github.marcondesnjr.sismovie.Usuario;
import io.github.marcondesnjr.sismovie.dao.daobd.DAOBDAmizade;
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

@AllArgsConstructor
public class AmizadeIT extends DataSourceBasedDBTestCase {

    private DataSource dataSource;
    private ConnectionManager connectionManager;

    public AmizadeIT() {
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
                .getResourceAsStream("amizade_test_initial.xml"));
    }


    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    public void testAddSol() throws Exception {
        DAOBDAmizade daobdAmizade = new DAOBDAmizade(getDataSource().getConnection());

        Usuario user1 = new Usuario("nome1", "sobrenome1", "email1@email.com", "123",
                LocalDate.parse("2000-12-12"),"cidade1", Estado.AC, Permissao.USUARIO);
        Usuario user2 = new Usuario("nome2", "sobrenome2", "email2@email.com", "123",
                LocalDate.parse("2000-12-12"),"cidade1", Estado.AC, Permissao.USUARIO);

        Solicitacao solicitacao= new Solicitacao(user1,user2);

        daobdAmizade.persistir(solicitacao);

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("amizade_expected.xml"));
        ITable expectedTable = expectedDataSet.getTable("AMIZADE");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("AMIZADE");

        Assertion.assertEquals(expectedTable, actualTable);
    }

}
