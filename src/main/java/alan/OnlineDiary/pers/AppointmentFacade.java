/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.pers;

import alan.OnlineDiary.ents.Appointment;
import alan.OnlineDiary.ents.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Alan
 */
@Stateless
public class AppointmentFacade extends AbstractFacade<Appointment> {

    @PersistenceContext(unitName = "com.mycompany_OnlineDiary_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentFacade() {
        super(Appointment.class);
    }
    
    public Appointment findAppointmentByUser(User user) {
        try {
            TypedQuery<Appointment> query = em.createQuery(
                    "SELECT DISTINCT a FROM Appointment a WHERE a.users = :user", Appointment.class);
            return (Appointment)query.setParameter("user", user).setMaxResults(1).getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
    
}
