package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connect;

    static {
        try{
            connect = Util.getConnect();
            System.out.println("Все хорошо!");
        }catch (SQLException e){
            System.out.println("Что-то ни так!");
        }
    }

    public UserDaoJDBCImpl() { };

    public void createUsersTable(){
        try {
            Statement statement = connect.createStatement();
            String str = "CREATE TABLE usser.all(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(128), lastName VARCHAR(128), age TINYINT)";
            statement.executeUpdate(str);
        }catch (SQLException e){
            System.out.println("Ошибка при создании таблицы!");
        }
    }

    public void dropUsersTable(){
        try {
            Statement statement = connect.createStatement();
            String str = "DROP TABLE usser.all";
            statement.execute(str);
        }catch (SQLException e){
            System.out.println("Ошибка при удалении таблицы!");
        }
    }

    public void saveUser(String name, String lastName, byte age){
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(String.format("INSERT INTO usser.all (name, lastName, age) VALUES ('%s', '%s', %s)", name, lastName, age));
        }catch (SQLException e){
            System.out.println("Ошибка при создании user!");
        }
    }

    public void removeUserById(long id){
        try {
            String str = "DELETE FROM usser.all WHERE ID = ?";
            PreparedStatement ps = connect.prepareStatement(str);
            ps.setLong(1, id);
        }catch (SQLException e){
            System.out.println("Ошибка при удалении user!");
        }
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connect.createStatement();
            ResultSet resSet = statement.executeQuery("SELECT * FROM usser.all");
            while (resSet.next()) {
                Long id = resSet.getLong("id");
                String name = resSet.getString("name");
                String lastName = resSet.getString("lastName");
                Byte age = resSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        }catch (SQLException e){
            System.out.println("Ошибка при получении всех user!");
        }
        return users;
    }

    public void cleanUsersTable(){
        try {
            Statement statement = connect.createStatement();
            String str = "TRUNCATE TABLE usser.all";
            statement.execute(str);
        }catch (SQLException e){
            System.out.println("Ошибка при очистке таблицы!");
        }
    }
}

