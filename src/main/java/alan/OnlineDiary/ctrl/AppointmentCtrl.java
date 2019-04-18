/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.ctrl;

import alan.OnlineDiary.bus.AppointmentService;
import alan.OnlineDiary.ents.Appointment;
import alan.OnlineDiary.ents.User;
import static alan.OnlineDiary.ents.User_Appointment_.appointment;
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

    public Appointment getNewAppointment() {
        return newAppointment;
    }

    public void setNewAppointment(Appointment newAppointment) {
        this.newAppointment = newAppointment;
    }
    
    @EJB
    private AppointmentService as;
    
    public String insertAppointment() {
        FacesContext context = FacesContext.getCurrentInstance();
        User user = (User) context.getExternalContext().getSessionMap().get("user");
        String username = user.getUsername();
        newAppointment.setOwner(username);
        if (as.createNewAppointment(newAppointment) == true) {
            return "index.xhtml?faces-redirect=true";
        } else {
            return null;
        }
    } 
}
