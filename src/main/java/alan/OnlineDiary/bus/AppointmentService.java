/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.bus;

import alan.OnlineDiary.ents.Appointment;
import alan.OnlineDiary.pers.AppointmentFacade;
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Boolean createNewAppointment(Appointment a) {
        if (true) {
            af.create(a);
            return true;
        }
        return false;
    }
}
