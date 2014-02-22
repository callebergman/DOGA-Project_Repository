/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author User
 */
@Entity
public class Competence_profile implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger competence_profile_id;
    private int years_of_experience;
    
    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;
    
    @ManyToOne
    @JoinColumn(name="competence_id")
    private Competence competence;

    public Competence_profile() {
    }

    public Competence_profile(int years_of_experience, Competence competence) {
        this.years_of_experience = years_of_experience;
        this.competence = competence;
    }

    public BigInteger getCompetence_profile_id() {
        return competence_profile_id;
    }

    public void setCompetence_profile_id(BigInteger competence_profile_id) {
        this.competence_profile_id = competence_profile_id;
    }

    public int getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(int years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.competence_profile_id);
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
        final Competence_profile other = (Competence_profile) obj;
        if (!Objects.equals(this.competence_profile_id, other.competence_profile_id)) {
            return false;
        }
        return true;
    }
}
