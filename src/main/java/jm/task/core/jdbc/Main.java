package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

       UserServiceImpl uSI = new UserServiceImpl();

        User user1 = new User("Misha", "Ivanov", (byte)23);
        uSI.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User с именем " + user1.getName() + " добавлен в базу данных");

        User user2 = new User("Alexey", "Petrov", (byte)28);
        uSI.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User с именем " + user2.getName() + " добавлен в базу данных");

        User user3 = new User("Masha", "Sidorova", (byte)19);
        uSI.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User с именем " + user3.getName() + " добавлен в базу данных");

        User user4 = new User("Ira", "Smirnova", (byte)31);
        uSI.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User с именем " + user4.getName() + " добавлен в базу данных");

        List<User> users = new ArrayList<>();
        users = uSI.getAllUsers();
        for(User user: users){
            System.out.println(user.toString());
        }

        uSI.cleanUsersTable();

        uSI.dropUsersTable();

    }

}
