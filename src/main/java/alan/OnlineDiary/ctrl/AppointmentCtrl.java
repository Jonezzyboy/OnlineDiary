/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.ctrl;

import alan.OnlineDiary.bus.AppointmentService;
import alan.OnlineDiary.ents.Appointment;
import alan.OnlineDiary.ents.User;
import alan.OnlineDiary.ents.User_Appointment;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Alan
 */
@Named(value = "appointmentCtrl")
@RequestScoped
public class AppointmentCtrl {

    /**
     * Creates a new instance of AppointmentCtrl
     */
    public AppointmentCtrl() {
    }
    
    private Appointment newAppointment = new Appointment();
    private List<User> users;
    private User_Appointment userApps = new User_Appointment();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Appointment getNewAppointment() {
        return newAppointment;
    }

    public void setNewAppointment(Appointment newAppointment) {
        this.newAppointment = newAppointment;
    }
    
    @EJB
    private AppointmentService as;
    
    public String insertAppointment() {
        if (as.createNewAppointment(newAppointment, users, userApps) == true) {
            return "index.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    } 
}
