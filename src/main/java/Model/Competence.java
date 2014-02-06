/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.math.BigInteger;
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
public class Competence implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger competence_id;
    private String name;
    
    @ManyToOne
    @JoinColumn(name="competence_id", insertable=false, updatable=false)
    private Competence_profile competence_profile;

    
    
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

    public Competence_profile getCompetence_profile() {
        return competence_profile;
    }

    public void setCompetence_profile(Competence_profile competence_profile) {
        this.competence_profile = competence_profile;
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
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Competence[ id=" + id + " ]";
    }
    */
}
