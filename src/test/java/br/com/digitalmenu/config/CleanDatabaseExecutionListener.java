package br.com.digitalmenu.config;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CleanDatabaseExecutionListener implements TestExecutionListener {

    @Override
    public void beforeTestMethod(final TestContext testContext) throws Exception {
        try {
            final DataSource dataSource = testContext.getApplicationContext()
                    .getBean(DataSource.class);
            cleanDatabase(dataSource);
        } catch (final NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }
    }

    private void cleanDatabase(final DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            executeUpdate(connection, "SET REFERENTIAL_INTEGRITY = 0");

            executeQuery(connection, "SHOW TABLES", rs ->
                    executeUpdate(connection, "TRUNCATE TABLE " + rs.getString("TABLE_NAME"))
            );

            executeQuery(connection, "SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES", rs ->
                    executeUpdate(connection, "ALTER SEQUENCE " + rs.getString("SEQUENCE_NAME") + " RESTART WITH 1")
            );

            executeUpdate(connection, "SET REFERENTIAL_INTEGRITY = 1");
        }
    }

    private void executeQuery(final Connection connection,
                              final String query,
                              final QueryConsumer consumer) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(query)) {
                while (rs.next()) {
                    consumer.accept(rs);
                }
            }
        }
    }

    private void executeUpdate(final Connection connection, final String query) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        }
    }

    @FunctionalInterface
    private interface QueryConsumer {
        void accept(ResultSet rs) throws SQLException;
    }
}
