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
    public String createNewUser(User u) {
        // Fix duplicate entry validation
        Boolean userExists = checkDuplicates(u);
        if (userExists == false) {
            uf.create(u);
            return "login.xhtml?faces-redirect=true";
        } else {
            return "user.xhtml?faces-redirect=true";
        }
    }

    public Boolean checkDuplicates(User u) {
        Boolean userExists = true;
        if (uf.findUsersByUsername(u.getUsername()) == null && uf.findUsersByEmail(u.getEmail()) == null) {
            userExists = false;
        }
        return userExists;
    }

    public String validateLogin(String username, String password) {
        String userExists = "index.xhtml?faces-redirect=true";
        if (uf.findUserByCredentials(username, password) == null) {
            userExists = "login.xhtml?faces-redirect=true";
        }
        return userExists;
    }
}
