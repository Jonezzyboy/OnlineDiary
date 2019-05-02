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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    /**
     * Find all appointments
     *
     * @return List of all appointment objects
     */
    public List<Appointment> findAllApps() {
        return af.findAll();
    }

    /**
     * Create appointment
     *
     * @param a The appointment to add
     * @param userArray All users involved with the appointment
     *
     * @return Whether the appointment was created or not
     */
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
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Checks for clashes of all associated user's appointments
     *
     * @param a The appointment being compared
     * @param users The list of user objects
     *
     * @return appointmentClash
     */
    public Boolean checkClash(Appointment a, List<User> users) {
        FacesContext context = FacesContext.getCurrentInstance();
        Boolean appointmentClash = false;
        List<Appointment> userApps;
        for (User user : users) {
            // Get all exisiting appointments for a each user
            userApps = user.getAppointments();
            if (!appointmentClash) {
                for (Appointment app : userApps) {
                    if (a.getStartDate().equals(app.getStartDate())
                            && (a.getStartTime().after(app.getStartTime()) || a.getStartTime().compareTo(app.getStartTime()) == 0)
                            && (a.getEndTime().before(app.getEndTime()) || a.getEndTime().compareTo(app.getEndTime()) == 0)) {
                        appointmentClash = true;
                        context.addMessage("appointForm:userList", new FacesMessage("There is an appointment time clash"));
                        return appointmentClash;
                    }
                }
            }
        }
        return appointmentClash;
    }

    /**
     * Checks if the times provided are valid
     *
     * @param a The appointment being compared
     *
     * @return Whether the start time is before the end time
     */
    public Boolean checkTimes(Appointment a) {
        FacesContext context = FacesContext.getCurrentInstance();
        Boolean timeValid = true;
        Date currentDate = new Date();
        Date appStartDate = joinStartTimes(a.getStartDate(), a.getStartTime());
        if (appStartDate.before(currentDate)) {
            timeValid = false;
            context.addMessage("appointForm:startDate", new FacesMessage("Start Date must not be set in the past"));
            if (a.getStartTime().after(a.getEndTime()) || a.getStartTime().compareTo(a.getEndTime()) == 0) {
                context.addMessage("appointForm:startTime", new FacesMessage("Start Time must be before End Time"));
            }
        }
        return timeValid;
    }

    /**
     * Joins the date with time
     *
     * @param startDate The day/month/year of the appointment
     * @param time The Hours:Minutes of the appointment
     *
     * @return The combined date of DD/MM/YYYY HH:MM
     */
    public Date joinStartTimes(Date startDate, Date time) {
        // Joins the start date and start time
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String times = dateFormat.format(time);
        String[] hourMin = times.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, mins);
        Date appStartDate = cal.getTime();
        return appStartDate;
    }

    /**
     * Gets the owner's User object
     *
     * @param username The owner's username
     *
     * @return The User object for the owner
     */
    public User getOwnerObj(String username) {
        return uf.findUsersByUsername(username);
    }

    /**
     * Delete an appointment
     *
     * @param a The appointment being compared
     * @param credentials The username and password of a non logged in user
     *
     * @return Whether the deletion was completed or not
     */
    public Boolean deleteAppointment(Appointment a, User credentials) {
        FacesContext context = FacesContext.getCurrentInstance();
        User sessionOwner = (User) context.getExternalContext().getSessionMap().get("user");
        User userCreds = uf.findUserByCredentials(credentials.getUsername(), credentials.getPassword());
        User appOwner = uf.findUsersByUsername(a.getOwner());
        if (sessionOwner.getUsername().equals(a.getOwner()) || appOwner == userCreds) {
            af.remove(a);
            return true;
        }
        context.addMessage("deleteError:deleteAppMsg", new FacesMessage("You do not have permission to remove this appointment"));
        return false;
    }
}
