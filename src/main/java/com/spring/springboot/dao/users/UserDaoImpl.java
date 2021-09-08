package com.spring.springboot.dao.users;


import com.spring.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        List<User> list = entityManager.createQuery("from User", User.class).getResultList();
        return list;
    }

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    public void deleteUserById(long id) {
        User delUser = getUserById(id);
        entityManager.remove(delUser);
    }

    @Override
    public User getUserFirstName(String firstName) {
        return entityManager.createQuery(
                        "from User u where u.firstName = :firstName", User.class)
                .setParameter("firstName", firstName)
                .getSingleResult();
    }

}

