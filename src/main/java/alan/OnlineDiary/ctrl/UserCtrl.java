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
    private String confirm;

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    @EJB
    private UserService us;

    public String insertUser() {
        if (us.createNewUser(newUser, confirm) == true) {
            return "login.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

    public String loginUser() {
        User user = us.validateLogin(newUser.getUsername(), newUser.getPassword());
        FacesContext context = FacesContext.getCurrentInstance();
        if (user != null) {
            context.getExternalContext().getSessionMap().put("user", user);
            return "index.xhtml?faces-redirect=true";
        } else {
            context.addMessage("loginID:logbttn", new FacesMessage("Wrong username or password"));
            return null;
        }
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

}
