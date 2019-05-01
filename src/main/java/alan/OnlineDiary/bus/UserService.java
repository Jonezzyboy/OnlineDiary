/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.bus;

import alan.OnlineDiary.ents.User;
import alan.OnlineDiary.pers.UserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alan
 */
@Stateless
public class UserService {

    @EJB
    private UserFacade uf;

    /**
     * Create user
     *
     * @param u The user to add
     * @param confirm The confirm password string
     *
     * @return Whether the user was created or not
     */
    public Boolean createNewUser(User u, String confirm) {
        Boolean passwordsMatch = checkPasswords(u, confirm);
        Boolean userExists = checkDuplicates(u);
        if (userExists == false && passwordsMatch == true) {
            uf.create(u);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check for duplicate users
     *
     * @param u The user being compared
     *
     * @return Whether the user exists already or not
     */
    public Boolean checkDuplicates(User u) {
        FacesContext context = FacesContext.getCurrentInstance();
        Boolean userExists = true;
        if (uf.findUsersByUsername(u.getUsername()) == null && uf.findUsersByEmail(u.getEmail()) == null) {
            userExists = false;
        }
        if (uf.findUsersByUsername(u.getUsername()) != null) {
            context.addMessage("createID:username", new FacesMessage("Username already exists"));
        }
        if (uf.findUsersByEmail(u.getEmail()) != null) {
            context.addMessage("createID:email", new FacesMessage("Email already exists"));
        }
        return userExists;
    }

    /**
     * Compare password strings
     *
     * @param u The user being compared
     * @param confirm The confirm password string
     *
     * @return Whether the password and confirm passwords match
     */
    public Boolean checkPasswords(User u, String confirm) {
        FacesContext context = FacesContext.getCurrentInstance();
        Boolean passwordsMatch = false;
        if (u.getPassword().equals(confirm)) {
            passwordsMatch = true;
        } else {
            context.addMessage("createID:confirm", new FacesMessage("Passwords do not match"));
        }
        return passwordsMatch;
    }

    /**
     * Validate a user's log in attempt
     *
     * @param username The username string
     * @param password The password string
     *
     * @return Whether the password and confirm passwords match
     */
    public User validateLogin(String username, String password) {
        User user = uf.findUserByCredentials(username, password);
        if (user == null) {
            return user;
        }
        return user;
    }

    /**
     * Get all users
     *
     * @return List of all user objects
     */
    public List<User> findAllUsers() {
        return uf.findAll();
    }
}
