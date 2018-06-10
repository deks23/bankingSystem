package pl.damiankotynia.bankingsystem.database;

import pl.damiankotynia.bankingsystem.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.Set;

@Transactional
public class DatabaseImpl implements Database {
    private EntityManagerFactory entityManagerFactory;


    @Override
    public User findUserById(long id) {
        return null;
    }

    @Override
    public Set<User> findUserByName(String name) {
        return null;
    }

    @Override
    public Set<User> findUserBySurname(String surname) {
        return null;
    }

    @Override
    public User findUserByPesel(long pesel) {
        return null;
    }

    @Override
    public Set<User> getAllUsers() {

        return null;
    }

    @Override
    public Set<User> findByAddress(String address) {
        return null;
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
