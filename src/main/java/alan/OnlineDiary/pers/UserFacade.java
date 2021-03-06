/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.pers;

import alan.OnlineDiary.ents.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Alan
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "com.mycompany_OnlineDiary_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    /**
     * Find a user using their username
     *
     * @param username The username string
     *
     * @return Query result as a user object
     */
    public User findUsersByUsername(String username) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class);
            return query.setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Find a user using their username and password
     *
     * @param username The username string
     * @param password The password string
     *
     * @return Query result as a user object
     */
    public User findUserByCredentials(String username, String password) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE (u.username = :username AND u.password = :password)"
                    + "OR(u.email = :username AND u.password = :password)", User.class);
            return (User) query.setParameter("username", username).setParameter("password", password).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Find a user using their email address
     *
     * @param email The email string
     *
     * @return Query result as a user object
     */
    public User findUsersByEmail(String email) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email", User.class);
            return query.setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Find a user's ID using their username
     *
     * @param username The username string
     *
     * @return Query result as a user object
     */
    public User findUserIDByUsername(String username) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u.user_id FROM User u WHERE u.username = :username", User.class);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
