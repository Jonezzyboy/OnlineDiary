/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.bus;

import alan.OnlineDiary.ents.User;
import alan.OnlineDiary.pers.UserFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alan
 */
@Stateless
public class UserService {
    
    @EJB
    private UserFacade uf;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public User createNewUser(User u){
        uf.create(u);
        return u;
    }
    
    public String validateLogin(String username, String password){
       String userExists = "index.html?faces-redirect=true";
       if(uf.findUsersByUsername(username) == null || uf.findUsersByPassword(password) == null) userExists = "login.xhtml?faces-redirect=true";
       return userExists;
    }
}
