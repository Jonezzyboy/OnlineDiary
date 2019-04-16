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
    public Boolean createNewUser(User u, String confirm) {
        // Fix duplicate entry validation
        Boolean passwordsMatch = checkPasswords(u, confirm);
        Boolean userExists = checkDuplicates(u);
        if (userExists == false && passwordsMatch == true) {
            uf.create(u);
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkDuplicates(User u) {
        Boolean userExists = true;
        if (uf.findUsersByUsername(u.getUsername()) == null && uf.findUsersByEmail(u.getEmail()) == null) {
            userExists = false;
        }
        return userExists;
    }
    
    public Boolean checkPasswords(User u, String confirm){
        Boolean passwordsMatch = false;
        if (u.getPassword().equals(confirm)) {
            passwordsMatch = true;
        }
        return passwordsMatch;
    }

    public Boolean validateLogin(String username, String password) {
        Boolean userExists = true;
        if (uf.findUserByCredentials(username, password) == null) {
            userExists = false;
        }
        return userExists;
    }
}
