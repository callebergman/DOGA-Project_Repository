/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Column;
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
public class Person implements PersonDTO, Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private BigInteger person_id;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    //@Column(length = 32, columnDefinition = "VARCHAR(32)")
    private String password;
    private BigInteger role_id;
    @Column(unique = true)
    private String username;
    @OneToOne(mappedBy = "person", cascade = REMOVE)
    private Person_Groups person_group;
    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Roles role;
    
    @OneToMany(mappedBy = "person", cascade = PERSIST)
    private Collection<Availability> availabilitys = new HashSet();
    
    @OneToMany(mappedBy = "person", cascade = REMOVE)
    private Collection<Competence_profile> competence_profiles = new HashSet();

    public Person() {
    }

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person(String name, String surname, String ssn, String email, String password, String username) {
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.password = sha256(password);
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

    public Roles getRole() {
        return role;
    }

    void setRole(Roles role) {
        this.role = role;
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
    public String getSsn() {
        return ssn;
    }

    /**
     * @param ssn the ssn to set
     */
    public void setSsn(String ssn) {
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
        this.password = sha256(password);
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.person_id);
        hash = 59 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.person_id, other.person_id)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    public static String sha256(String base) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(base.getBytes("UTF-8"));
            byte[] digest = md.digest();
            return (Base64.encode(digest)).toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}