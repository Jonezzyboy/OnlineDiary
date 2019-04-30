/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.ctrl;

import alan.OnlineDiary.bus.AppointmentService;
import alan.OnlineDiary.ents.Appointment;
import alan.OnlineDiary.ents.User;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

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
    private String[] userArray;
    private List<Appointment> allApps;
    private List<User> allUsers;

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
    
    public List<User> getNonOwners(Appointment a) {
        allUsers = a.getUsers();
        User owner = as.getOwnerObj(a.getOwner());
        allUsers.remove(owner);
        return allUsers;
    }

    public List<Appointment> getAllApps() {
        allApps = as.findAllApps();
        return allApps;
    }

    public void setAllApps(List<Appointment> allApps) {
        this.allApps = allApps;
    }

    public String[] getUserArray() {
        return userArray;
    }

    public void setUserArray(String[] userArray) {
        this.userArray = userArray;
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
        if (as.createNewAppointment(newAppointment, userArray) == true) {
            return "index.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }
    
    public String deleteAppointment(Appointment a) {
        if (as.deleteAppointment(a) == true) {
            return "reports.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }
}
