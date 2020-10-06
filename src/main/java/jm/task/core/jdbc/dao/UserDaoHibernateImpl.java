package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

   private static Session session;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try{
            session = Util.getSesF().openSession();
           session.beginTransaction();
           String str = "CREATE TABLE user(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(128), lastName VARCHAR(128), age TINYINT)";
           session.createSQLQuery(str).executeUpdate();
           session.getTransaction().commit();
       }catch (Exception e){
           System.out.println("Что-то пошло не так!");
           e.printStackTrace();
       }
    }
    @Override
    public void dropUsersTable() {
       try {
           session = Util.getSesF().openSession();
           session.beginTransaction();
           String str = "DROP TABLE IF EXISTS  user";
           session.createSQLQuery(str).executeUpdate();
           session.getTransaction().commit();
       }catch(Exception e){
           System.out.println("Что-то пошло не так!");
       }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
       try {
           session = Util.getSesF().openSession();
           session.beginTransaction();
           User user = new User(name, lastName, age);
           session.save(user);
           session.getTransaction().commit();
       }catch(Exception e){
           System.out.println("Что-то пошло не так!");
           e.printStackTrace();
       }
    }

    @Override
    public void removeUserById(long id) {
       try {
           session = Util.getSesF().openSession();
           session.beginTransaction();
           User user = session.get(User.class, id);
           session.delete(user);
           session.getTransaction().commit();
       }catch(Exception e){
           System.out.println("Что-то пошло не так!");
       }
    }

    @Override
    public List<User> getAllUsers() {
       List<User> users = new ArrayList<>();
       try {
           session = Util.getSesF().openSession();
           String sql = "From " + User.class.getSimpleName();
           session.beginTransaction();
           users = session.createQuery(sql).list();
           session.getTransaction().commit();
       }catch(Exception e){
               System.out.println("Что-то пошло не так!");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try{
            session = Util.getSesF().openSession();
            session.beginTransaction();
            String str = "TRUNCATE TABLE user";
            session.createSQLQuery(str).executeUpdate();
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println("Что-то пошло не так!");
            e.printStackTrace();
        }
    }
}
