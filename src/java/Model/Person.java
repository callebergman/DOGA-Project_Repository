/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
 * Entity class for person
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private BigInteger person_id;
    
    private String name;
    private String surname;
    private String ssn;
    @Column(unique=true)
    private String email;
    
    @OneToOne(mappedBy = "person", cascade = ALL)
    private Credential credential;
    
    @ManyToOne
    @JoinColumn(name = "roleName")
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

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        credential.setPerson(this);
        this.credential = credential;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Availability> getAvailabilitys() {
        return availabilitys;
    }

    public void setAvailabilitys(List<Availability> availabilitys) {
        this.availabilitys = availabilitys;
    }

    public List<Competence_profile> getCompetence_profiles() {
        return competence_profiles;
    }

    public void setCompetence_profiles(List<Competence_profile> competence_profiles) {
        this.competence_profiles = competence_profiles;
    }
    
    public void addAvailability (Availability   a) {
        a.setPerson(this);
        availabilitys.add (a);
    }
    
    public void addCompetence_profiles (Competence_profile   cp) {
        cp.setPerson(this);
        competence_profiles.add (cp);
    }
}