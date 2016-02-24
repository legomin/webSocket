package com.legomin.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class Dao {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public void saveUser(User user) {
        getSession().save(user);
    }

    public void deleteUser(User user) {
        getSession().delete(user);
    }

    @SuppressWarnings("unchecked")
    public List getAllUsers() {
        return getSession().createQuery("from User").list();
    }

    public User getByEmailAndPassword(String email, String password) {
        return (User) getSession().createQuery(
                "from User where email = :email and password = :password")
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();

    }

    public User getUserById(long id) {
        return (User) getSession().load(User.class, id);
    }

    public void updateUser(User user) {
        getSession().update(user);
    }



    public void saveToken(Token token) {
        getSession().save(token);
    }

    public void deleteToken(Token token) {
        getSession().delete(token);
    }

    @SuppressWarnings("unchecked")
    public List getAllTokens() {
        return getSession().createQuery("from Token").list();
    }

    public Token getTokenById(Integer id) {
        return (Token) getSession().load(Token.class, id);
    }

    public void updateToken(Token token) {
        getSession().update(token);
    }

    public String getNewUUID() {
        return UUID.randomUUID().toString();
    }

}