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
    @JoinColumn(name="person_id", insertable=false, updatable=false)
    private Person person;
   
   @ManyToOne
    @JoinColumn(name="competence_id", insertable=false, updatable=false)
    private Competence competence;

    public Competence_profile() {
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
        if (!(object instanceof Competence_profile)) {
            return false;
        }
        Competence_profile other = (Competence_profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Competence_profile[ id=" + id + " ]";
    }
    */

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

   
}
