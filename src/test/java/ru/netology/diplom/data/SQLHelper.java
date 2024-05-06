package ru.netology.diplom.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class SQLHelper {
    private static final QueryRunner queryRunner = new QueryRunner();
    private SQLHelper() {
    }
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"),"app", "pass");
    }
    @SneakyThrows
    public static String getStatusPayment() {

        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return queryRunner.query(conn, codeSQL, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static int getAmountPayment() {
        var codeSQL = "SELECT amount FROM payment_entity WHERE status = 'APPROVED' ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return queryRunner.query(conn, codeSQL, new ScalarHandler<Integer>());
    }
    @SneakyThrows
    public static String getStatusCredit() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return queryRunner.query(conn, codeSQL, new ScalarHandler<String>());
    }
}
