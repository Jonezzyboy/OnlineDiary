/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.bus;

import alan.OnlineDiary.ents.Appointment;
import alan.OnlineDiary.ents.User;
import alan.OnlineDiary.ents.User_Appointment;
import alan.OnlineDiary.pers.AppointmentFacade;
import alan.OnlineDiary.pers.UserFacade;
import alan.OnlineDiary.pers.User_AppointmentFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alan
 */
@Stateless
public class AppointmentService {
    
    @EJB
    private AppointmentFacade af;
    @EJB
    private UserFacade uf;
    @EJB
    private User_AppointmentFacade uaf;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Boolean createNewAppointment(Appointment a, List<User> users, User_Appointment userApps) {
        // Set the owner of appointment to current logged in user 
        FacesContext context = FacesContext.getCurrentInstance();
        User owner = (User) context.getExternalContext().getSessionMap().get("user");
        String username = owner.getUsername();
        a.setOwner(username);
        // User object for linking table
        // User ownerID = uf.findUserIDByUsername(a.getOwner());
        af.create(a);
//        for(User user: users){
//            userApps.setUser(user);
//            userApps.setAppointment(a);
//            uaf.create(userApps);
//        }
        return true;
    }
    
    public Boolean checkClash(Appointment a, User u) {
        Boolean appointmentClash = false;
        return appointmentClash;
    }
}
