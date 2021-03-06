/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.ents;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    private java.util.Date startDate;
    @Temporal(TemporalType.TIME)
    private java.util.Date startTime;
    @Temporal(TemporalType.TIME)
    private java.util.Date endTime;
    @ManyToMany
    private List<User> users;
    private String description;
    private String owner;

    /**
     * @return List of all users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users List of all users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return Description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description Description string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Owner string
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner Owner string
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return Start Date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate Start Date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return Start Time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime Start Time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return End Time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime End Time
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return ID
     */
    public Long getId() {
        return appointment_id;
    }

    /**
     * @param appointment_id ID
     */
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
