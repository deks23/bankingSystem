package pl.damiankotynia.bankingsystem.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.damiankotynia.bankingsystem.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
public class DatabaseImpl implements Database {
    private EntityManagerFactory entityManagerFactory;


    @Override
    public User findUserById(long userId) {
        EntityManager entityManager = startTransaction();
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class).setParameter("id", userId).getSingleResult();
        commitTransaction(entityManager);
        return user;
    }

    @Override
    public Set<User> findUserByName(String userName) {
        EntityManager entityManager = startTransaction();
        List<User> userList = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class).setParameter("name", userName).getResultList();
        Set<User> userSet = new HashSet<>(userList);
        commitTransaction(entityManager);
        return userSet;
    }

    @Override
    public Set<User> findUserBySurname(String surname) {
        EntityManager entityManager = startTransaction();
        List<User> userList = entityManager.createQuery("SELECT u FROM User u WHERE u.surname = :surname", User.class).setParameter("surname", surname).getResultList();
        Set<User> userSet = new HashSet<>(userList);
        commitTransaction(entityManager);
        return userSet;
    }

    @Override
    public User findUserByPesel(long pesel) {
        EntityManager entityManager = startTransaction();
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.pesel = :pesel", User.class).setParameter("pesel", pesel).getSingleResult();
        commitTransaction(entityManager);
        return user;
    }

    @Override
    public Set<User> getAllUsers() {
        EntityManager entityManager = startTransaction();
        List<User> userList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        Set<User> userSet = new HashSet<>(userList);
        commitTransaction(entityManager);
        return userSet;
    }

    @Override
    public Set<User> findByAddress(String address) {
        EntityManager entityManager = startTransaction();
        List<User> userList = entityManager.createQuery("SELECT u FROM User u WHERE u.address = :address", User.class).setParameter("address", address).getResultList();
        Set<User> userSet = new HashSet<>(userList);
        commitTransaction(entityManager);
        return userSet;
    }

    @Override
    public boolean addUser(User user) {
        EntityManager entityManager = startTransaction();
        entityManager.persist(user);
        commitTransaction(entityManager);
        return true;
    }



    @Override
    public boolean removeUser(Long id) {
        return false;
    }

    @Override
    public boolean dropDatabase() {
        return false;
    }

    private void commitTransaction(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    private EntityManager startTransaction() {
        entityManagerFactory = Persistence.createEntityManagerFactory("bankingSystem");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }


    /*@Override
    public void saveDatabase() {

    }

    @Override
    public boolean createDatabase() {
        return false;
    }

    @Override
    public void loadDatabase() {
    }*/
}
