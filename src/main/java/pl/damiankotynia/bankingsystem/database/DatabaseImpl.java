package pl.damiankotynia.bankingsystem.database;


import pl.damiankotynia.bankingsystem.model.User;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
public class DatabaseImpl implements Database {
    private EntityManagerFactory entityManagerFactory;


    @Override
    public User findUserById(long userId) {
        EntityManager entityManager = createEntityManager();
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class).setParameter("id", userId).getSingleResult();
            return user;
        }catch (NoResultException e){
            return null;
        }finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public Set<User> findUserByName(String userName) {
        EntityManager entityManager = createEntityManager();
        try {
            List<User> userList = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class).setParameter("name", userName).getResultList();
            Set<User> userSet = new HashSet<>(userList);
            return userSet;
        }catch (NoResultException e){
            return null;
        }finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public Set<User> findUserBySurname(String surname) {
        EntityManager entityManager = createEntityManager();
        try {
            List<User> userList = entityManager.createQuery("SELECT u FROM User u WHERE u.surname = :surname", User.class).setParameter("surname", surname).getResultList();
            Set<User> userSet = new HashSet<>(userList);
            return userSet;
        }catch (NoResultException e){
            return null;
        }finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public User findUserByPesel(long pesel) {
        EntityManager entityManager = createEntityManager();
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.pesel = :pesel", User.class).setParameter("pesel", pesel).getSingleResult();
            return user;
        }catch (NoResultException e){
            return null;
        }finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public Set<User> getAllUsers() {
        EntityManager entityManager = createEntityManager();
        try {
            List<User> userList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
            Set<User> userSet = new HashSet<>(userList);
            return userSet;
        }catch (NoResultException e){
            return null;
        }finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public Set<User> findByAddress(String address) {
        EntityManager entityManager = createEntityManager();
        try {
            List<User> userList = entityManager.createQuery("SELECT u FROM User u WHERE u.address = :address", User.class).setParameter("address", address).getResultList();
            Set<User> userSet = new HashSet<>(userList);
            return userSet;
        }catch (NoResultException e){
            return null;
        }finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public boolean addUser(User user) {
        EntityManager entityManager = createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }
        catch(PersistenceException e){
            return false;
        }
        finally {
            closeEntityManager(entityManager);
        }
        return true;
    }



    @Override
    public boolean removeUser(Long id) {
        User user = findUserById(id);
        EntityManager entityManager = createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(user));
            entityManager.getTransaction().commit();

        }catch (PersistenceException e){
            return false;
        }finally {
            closeEntityManager(entityManager);
        }
        return true;
    }

    @Override
    public boolean dropDatabase() {
        return false;
    }

    @Override
    public boolean saveUserData(User user) {
        EntityManager entityManager = createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        }catch (PersistenceException e){
            return false;
        }finally {
            closeEntityManager(entityManager);
        }
        return true;
    }

    @Override
    public boolean saveUserAfterTransfer(User sourceUser, User targetUser) {
        EntityManager entityManager = createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(sourceUser);
            entityManager.merge(targetUser);
            entityManager.getTransaction().commit();
        }catch (PersistenceException e){
            return false;
        }finally {
            closeEntityManager(entityManager);
        }
        return true;
    }

    private EntityManager createEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("bankingSystem");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    private void closeEntityManager(EntityManager entityManager){
        entityManager.close();
        entityManagerFactory.close();
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
