/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.ctrl;

import alan.OnlineDiary.bus.UserService;
import alan.OnlineDiary.ents.User;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

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
    private String username;
    private String password;
    private String confirm;

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public UserService getUs() {
        return us;
    }

    public void setUs(UserService us) {
        this.us = us;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public User getNewUser(){
        return newUser;
    }
    
    public void setNewUser(){
        this.newUser = newUser;
    }
    
    @EJB
    private UserService us;
    
    public String insertUser(){
        if (us.createNewUser(newUser, confirm) == true) {
             return "login.xhtml?faces-redirect=true";
        }else{
            return "user.xhtml?faces-redirect=true";
        }
    }
    
    public String loginUser(){
        if (us.validateLogin(username, password) == true) {
            return "index.xhtml?faces-redirect=true";
        }else{
            return "login.xhtml?faces-redirect=true";
        }
    }
}
