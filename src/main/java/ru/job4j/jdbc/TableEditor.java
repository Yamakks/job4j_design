package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    private void doStatement(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            if (query.startsWith("CREATE")) {
                statement.execute(query);
            }
            statement.executeUpdate(query);
        }
        }

    public TableEditor(Properties properties) throws SQLException, IOException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, IOException {

        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }


    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (dummyColumn INT);",
                tableName
        );
            doStatement(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "DROP TABLE %s;",
                tableName
        );
            doStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s",
                tableName,
                columnName,
                type
        );
            doStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s",
                tableName,
                columnName
        );
            doStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName,
                columnName,
                newColumnName
        );
            doStatement(sql);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
        public static void main(String[] args) throws Exception {
        Properties pr = new Properties();
            TableEditor tb = new TableEditor(pr);
            tb.createTable("Java_Table");
            System.out.println(tb.getTableScheme("Java_Table"));
            tb.addColumn("Java_Table", "age", "INT");
            System.out.println(tb.getTableScheme("Java_Table"));
            tb.dropColumn("Java_Table", "age");
            System.out.println(tb.getTableScheme("Java_Table"));
            tb.renameColumn("Java_Table", "dummyColumn", "age");
            System.out.println(tb.getTableScheme("Java_Table"));
            tb.dropTable("Java_Table");
        }
}
