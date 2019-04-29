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
import java.util.ArrayList;
import java.util.List;
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

    public Boolean createNewAppointment(Appointment a, String[] userArray) {
        Boolean timeValid = checkTimes(a);
        if (timeValid) {
            // Set the owner of appointment to current logged in user 
            FacesContext context = FacesContext.getCurrentInstance();
            User owner = (User) context.getExternalContext().getSessionMap().get("user");
            a.setOwner(owner.getUsername());
            // Set all users associated with the appointment in joining table
            List<User> userList = new ArrayList<>();
            userList.add(owner);
            for (String user : userArray) {
                userList.add(uf.findUsersByUsername(user));
            }
            Boolean appointmentClash = checkClash(a, userList);
            if (!appointmentClash) {
                a.setUsers(userList);
                af.create(a);
                return true;
            } else {
                FacesContext.getCurrentInstance().addMessage("appointForm:userList", new FacesMessage("There is an appointment time clash"));
                return false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("appointForm:startTime", new FacesMessage("Start Time must be before End Time"));
            return false;
        }

    }

    public Boolean checkClash(Appointment a, List<User> users) {
        Boolean appointmentClash = false;
        List<Appointment> userApps;
        for (User user : users) {
            // Get all exisiting appointments for a each user
            userApps = user.getAppointments();
            if (!appointmentClash) {
                for (Appointment app : userApps) {
                    if (a.getStartDate().equals(app.getStartDate()) &&
                            (a.getStartTime().after(app.getStartTime()) || a.getStartTime().compareTo(app.getStartTime()) == 0)
                            && (a.getEndTime().before(app.getEndTime()) || a.getEndTime().compareTo(app.getEndTime()) == 0)) {
                        appointmentClash = true;
                    }
                }
            }
        }
        return appointmentClash;
    }

    public Boolean checkTimes(Appointment a) {
        Boolean timeValid = true;
        if (a.getStartTime().after(a.getEndTime()) || a.getStartTime().compareTo(a.getEndTime()) == 0) {
            timeValid = false;
        }
        return timeValid;
    }
}
