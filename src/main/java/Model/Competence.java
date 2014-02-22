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
import java.util.List;
import java.util.Objects;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author User
 */
@Entity
public class Competence implements Serializable, CompetenceDTO {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger competence_id;
    private String name;
       
    @OneToMany(mappedBy="competence",cascade=ALL)
    private List<Competence_profile> competence_profiles;

    public BigInteger getCompetence_id() {
        return competence_id;
    }

    public void setCompetence_id(BigInteger competence_id) {
        this.competence_id = competence_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Competence_profile> getCompetence_profiles() {
        return competence_profiles;
    }

    public void setCompetence_profiles(List<Competence_profile> competence_profiles) {
        this.competence_profiles = competence_profiles;
    }
    
    public void addCompetence_profile (Competence_profile cp) {
        cp.setCompetence(this);
        competence_profiles.add (cp);
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.competence_id);
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
        final Competence other = (Competence) obj;
        if (!Objects.equals(this.competence_id, other.competence_id)) {
            return false;
        }
        return true;
    }
}
