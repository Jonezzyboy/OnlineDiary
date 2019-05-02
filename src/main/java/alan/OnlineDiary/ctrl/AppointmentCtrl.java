/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.ctrl;

import alan.OnlineDiary.bus.AppointmentService;
import alan.OnlineDiary.ents.Appointment;
import alan.OnlineDiary.ents.User;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
    private User userCredentials = new User();

    /**
     * Get user credentials
     *
     * @return User object
     */
    public User getUserCredentials() {
        return userCredentials;
    }

    /**
     * Set user credentials
     *
     * @param userCredentials User object used for storing username and password
     */
    public void setUserCredentials(User userCredentials) {
        this.userCredentials = userCredentials;
    }

    /**
     * Get all users
     *
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return allUsers;
    }

    /**
     * Set all users
     *
     * @param allUsers List of user objects
     */
    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    /**
     * Get all non-owner users
     *
     * @param a The appointment being compared
     *
     * @return A list of users excluding an appointment's owner
     */
    public List<User> getNonOwners(Appointment a) {
        allUsers = a.getUsers();
        User owner = as.getOwnerObj(a.getOwner());
        allUsers.remove(owner);
        return allUsers;
    }

    /**
     * Get all appointments
     *
     * @return List of all appointments
     */
    public List<Appointment> getAllApps() {
        allApps = as.findAllApps();
        return allApps;
    }

    /**
     * Set all appointments
     *
     * @param allApps List of all appointments
     */
    public void setAllApps(List<Appointment> allApps) {
        this.allApps = allApps;
    }

    /**
     * Get all usernames in an array
     *
     * @return Array of username strings
     */
    public String[] getUserArray() {
        return userArray;
    }

    /**
     * Set all usernames in an array
     *
     * @param userArray Array of username strings
     */
    public void setUserArray(String[] userArray) {
        this.userArray = userArray;
    }

    /**
     * Get all attributes of an appointment
     *
     * @return Appointment object
     */
    public Appointment getNewAppointment() {
        return newAppointment;
    }

    /**
     * Set all attributes of an appointment
     *
     * @param newAppointment Appointment object
     */
    public void setNewAppointment(Appointment newAppointment) {
        this.newAppointment = newAppointment;
    }

    @EJB
    private AppointmentService as;

    /**
     * Redirects after creation of an appointment
     *
     *
     * @return The path of the index page
     */
    public String insertAppointment() {
        if (as.createNewAppointment(newAppointment, userArray) == true) {
            return "index.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

    /**
     * Redirects after deletion of an appointment
     *
     * @param a The appointment being compared
     *
     * @return The path of the reports page
     */
    public String deleteAppointment(Appointment a) {
        if (as.deleteAppointment(a, userCredentials) == true) {
            return "reports.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    }

    public Date sortByStartDate(List<Appointment> a) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getExternalContext().getSessionMap().get("sorting") == "true" || context.getExternalContext().getSessionMap().get("sorting") == null) {
            // Ascending Dates
            Collections.sort(a, new Comparator<Appointment>() {
                @Override
                public int compare(Appointment a1, Appointment a2) {
                    return a1.getStartDate().compareTo(a2.getStartDate());
                }
            });
            context.getExternalContext().getSessionMap().put("sorting", "false");
        } else {
            // Descending Dates
            Collections.sort(a, new Comparator<Appointment>() {
                @Override
                public int compare(Appointment a1, Appointment a2) {
                    return a2.getStartDate().compareTo(a1.getStartDate());
                }
            });
            context.getExternalContext().getSessionMap().put("sorting", "true");
        }
        return null;
    }
}
