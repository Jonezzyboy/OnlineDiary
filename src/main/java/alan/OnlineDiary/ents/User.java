/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alan.OnlineDiary.ents;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "diary_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String postcode;
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    /**
     * @return List of appointments
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * @param appointments List of appointments
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * @return Address string
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address Address string
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return Postcode string
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode Postcode string
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return Username string
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username Username string
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Password string
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password Password string
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Email string
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Email string
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Phone string
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone Phone string
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return First Name string
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName First Name string
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return Last Name string
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName Last Name string
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return ID
     */
    public Long getId() {
        return user_id;
    }

    /**
     * @param user_id ID
     */
    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (user_id != null ? user_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.user_id == null && other.user_id != null) || (this.user_id != null && !this.user_id.equals(other.user_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alan.OnlineDiary.ents.User[ id=" + user_id + " ]";
    }

}
