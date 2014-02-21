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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.persistence.CascadeType.ALL;
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
    private String password;
    
    @Column(unique = true)
    private String username;
    
    @OneToOne(mappedBy = "person", cascade = ALL)
    private Person_Groups person_group;
    
    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Roles role;
    
    @OneToMany(mappedBy = "person", cascade = ALL)
    private List<Availability> availabilitys = new ArrayList<Availability>();
    
    @OneToMany(mappedBy = "person", cascade = ALL)
    private List<Competence_profile> competence_profiles = new ArrayList<Competence_profile>();
    
    public Person() {
    }

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    
    public BigInteger getPerson_id() {
        return person_id;
    }

    public void setPerson_id(BigInteger person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = sha256 (password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Person_Groups getPerson_group() {
        return person_group;
    }

    public void setPerson_group(Person_Groups person_group) {
        person_group.setPerson(this);
        this.person_group = person_group;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Collection<Availability> getAvailabilitys() {
        return availabilitys;
    }

    public void setAvailabilitys(List<Availability> availabilitys) {
        this.availabilitys = availabilitys;
    }

    public Collection<Competence_profile> getCompetence_profiles() {
        return competence_profiles;
    }

    public void setCompetence_profiles(List<Competence_profile> competence_profiles) {
        this.competence_profiles = competence_profiles;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.person_id);
        hash = 53 * hash + Objects.hashCode(this.username);
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