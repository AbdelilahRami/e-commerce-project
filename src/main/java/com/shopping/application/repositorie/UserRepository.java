package com.shopping.application.repositorie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.shopping.application.models.User;

@Repository
public class UserRepository {

    private static final String HQL_ALL = "FROM User";
    private static final String HQL_GET_BY_ID = "FROM User WHERE id = :id";
    
    @PersistenceContext
    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<User> getAll(){
        Session session = entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery(HQL_ALL, User.class);
        return query.getResultList();
    }
    
    public User getById(String id){
        Session session = entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery(HQL_GET_BY_ID, User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
        
    }
    
    public User saveOrUpdate(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
        return user;
    }
    
    public void delete(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.remove(user);
    }
}
