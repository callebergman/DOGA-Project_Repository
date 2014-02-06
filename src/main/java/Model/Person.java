/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author User
 */
@Entity
public class Person implements Serializable {
   
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private BigInteger person_id;
    private String name;
    private String surname;
    private int ssn;
    private String email;
    private String password;
    private BigInteger role_id;
    private String username;
    
    @ManyToOne
    @JoinColumn(name="role_id", insertable=false, updatable=false)
    private Roles role;

    @OneToMany(mappedBy="person",cascade=REMOVE)
    private Collection<Availability> availabilitys = new HashSet ();
    
    @OneToMany(mappedBy="person",cascade=REMOVE)
    private Collection<Competence_profile> competence_profiles = new HashSet ();

    public Person() {
    }
    
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person(String name, String surname, int ssn, String email, String password, String username) {
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.password = password;
        this.username = username;
    }
    
    public Collection<Competence_profile> getCompetence_profile() {
        return competence_profiles;
    }

    public void setCompetence_profile(Collection<Competence_profile> competence_profile) {
        this.competence_profiles = competence_profile;
    }
    
    public Collection<Availability> getAvailability() {
        return availabilitys;
    }

    public void setAvailability(Collection<Availability> availability) {
        this.availabilitys = availability;
    }
    
    public Roles getRole()
    {
        return role;
    }
    void setRole(Roles role)
    {
        this.role=role;
    }
    
    /**
     * @return the person_id
     */
    public BigInteger getPerson_id() {
        return person_id;
    }

    /**
     * @param person_id the person_id to set
     */
    public void setPerson_id(BigInteger person_id) {
        this.person_id = person_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the ssn
     */
    public int getSsn() {
        return ssn;
    }

    /**
     * @param ssn the ssn to set
     */
    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role_id
     */
    public BigInteger getRole_id() {
        return role_id;
    }

    /**
     * @param role_id the role_id to set
     */
    public void setRole_id(BigInteger role_id) {
        this.role_id = role_id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.person_id == null && other.person_id != null) || (this.person_id != null && !this.person_id.equals(other.person_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Person[ id=" + person_id + " ]";
    }
 */
}
