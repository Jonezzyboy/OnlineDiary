/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.ctrl;

import alan.OnlineDiary.bus.UserService;
import alan.OnlineDiary.ents.User;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alan
 */
@Named(value = "userCtrl")
@RequestScoped
public class UserCtrl {

    /**
     * Creates a new instance of UserCtrl
     */
    public UserCtrl() {
    }

    private User newUser = new User();
    private List<User> allUsers;
    private String confirm;

    /**
     * Get the confirm password
     *
     * @return Confirm password string
     */
    public String getConfirm() {
        return confirm;
    }

    /**
     * Set the confirm password
     *
     * @param confirm Confirm password string
     */
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    /**
     * Get a User object
     *
     * @return User object
     */
    public User getNewUser() {
        return newUser;
    }

    /**
     * Set all the attributes for a User object
     *
     * @param newUser User object
     */
    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    @EJB
    private UserService us;

    /**
     * Get all users
     *
     * @return List of all users
     */
    public List<User> getAllUsers() {
        allUsers = us.findAllUsers();
        return allUsers;
    }

    /**
     * Get all non-owner users
     *
     * @return A list of users excluding an appointment's owner
     */
    public List<User> getNonOwners() {
        allUsers = us.findAllUsers();
        FacesContext context = FacesContext.getCurrentInstance();
        User owner = (User) context.getExternalContext().getSessionMap().get("user");
        allUsers.remove(owner);
        return allUsers;
    }

    /**
     * Redirects after creation of a user
     *
     * @return The path of the login page
     */
    public String insertUser() {
        if (us.createNewUser(newUser, confirm) == true) {
            return "login.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

    /**
     * Redirects after a user has logged in
     *
     * @return The path of the index page
     */
    public String loginUser() {
        User user = us.validateLogin(newUser.getUsername(), newUser.getPassword());
        FacesContext context = FacesContext.getCurrentInstance();
        if (user != null) {
            // Set session variable to current user obj
            context.getExternalContext().getSessionMap().put("user", user);
            return "index.xhtml?faces-redirect=true";
        } else {
            context.addMessage("loginID:logbttn", new FacesMessage("Wrong username or password"));
            return null;
        }
    }

    /**
     * Redirects after a user has logged out
     *
     * @return The path of the login page
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

}
