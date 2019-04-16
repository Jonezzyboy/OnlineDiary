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
    private String confirm;

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
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
            return null;
        }
    }
    
    public String loginUser(){
        if (us.validateLogin(newUser.getUsername(), newUser.getPassword()) == true) {
            return "index.xhtml?faces-redirect=true";
        }else{
            return null;
        }
    }
}
