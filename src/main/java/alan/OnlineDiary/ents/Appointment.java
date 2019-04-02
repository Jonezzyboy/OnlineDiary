/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.ents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alan
 */
@Entity
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointment_id;
    @Temporal(TemporalType.DATE)
    private java.util.Date creationDate;
    @Temporal(TemporalType.TIME)
    private java.util.Date startTime;
    @Temporal(TemporalType.TIME)
    private java.util.Date endTime;
    @ManyToMany(mappedBy="appointments")
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return appointment_id;
    }

    public void setId(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointment_id != null ? appointment_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.appointment_id == null && other.appointment_id != null) || (this.appointment_id != null && !this.appointment_id.equals(other.appointment_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alan.OnlineDiary.ents.Appointment[ id=" + appointment_id + " ]";
    }
    
}
