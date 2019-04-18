/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.pers;

import alan.OnlineDiary.ents.User_Appointment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alan
 */
@Stateless
public class User_AppointmentFacade extends AbstractFacade<User_Appointment> {

    @PersistenceContext(unitName = "com.mycompany_OnlineDiary_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public User_AppointmentFacade() {
        super(User_Appointment.class);
    }
    
}
