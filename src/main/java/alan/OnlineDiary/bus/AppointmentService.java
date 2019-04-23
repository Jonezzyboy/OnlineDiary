/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.bus;

import alan.OnlineDiary.ents.Appointment;
import alan.OnlineDiary.ents.User;
import alan.OnlineDiary.pers.AppointmentFacade;
import alan.OnlineDiary.pers.UserFacade;
import alan.OnlineDiary.pers.User_AppointmentFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
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
    
    public Boolean createNewAppointment(Appointment a) {
        // Set the owner of appointment to current logged in user 
        FacesContext context = FacesContext.getCurrentInstance();
        User user = (User) context.getExternalContext().getSessionMap().get("user");
        String username = user.getUsername();
        a.setOwner(username);
        // User object for linking table
        User ownerID = uf.findUserIDByUsername(a.getOwner());
        Boolean appointmentClash = checkClash(a, ownerID);
        if (appointmentClash == false) {
            af.create(a);
            return true;
        }
        return false;
    }
    
    public Boolean checkClash(Appointment a, User u) {
        Boolean appointmentClash = true;
        if (uf.findUsersByUsername(u.getUsername()) == null && uf.findUsersByEmail(u.getEmail()) == null) {
            appointmentClash = false;
        }
        if (uf.findUsersByUsername(u.getUsername()) != null) {
            FacesContext.getCurrentInstance().addMessage("createID:username", new FacesMessage("Username already exists"));
        }
        if (uf.findUsersByEmail(u.getEmail()) != null) {
            FacesContext.getCurrentInstance().addMessage("createID:email", new FacesMessage("Email already exists"));
        }
        return appointmentClash;
    }
}
