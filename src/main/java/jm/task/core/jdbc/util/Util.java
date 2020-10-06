package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final static String url = "jdbc:mysql://localhost:3306?serverTimezone=UTC";
    private final static String user = "root";
    private final static String password = "89205392733mmm";

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static SessionFactory getSesF(){
        return new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
    }

}
