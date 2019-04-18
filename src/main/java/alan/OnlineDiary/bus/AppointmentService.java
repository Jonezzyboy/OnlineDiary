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
        User ownerID = uf.findUserIDByUsername(a.getOwner());
        if (true) {
            af.create(a);
            return true;
        }
        return false;
    }
}
