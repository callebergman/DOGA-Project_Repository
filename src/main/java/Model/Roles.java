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
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Hikari
 */
@Entity
public class Roles implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private BigInteger id;
    
    private Collection<Person> persons = new HashSet ();

    @OneToMany(mappedBy="role",cascade=REMOVE)
    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }
    
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Role[ id=" + id + " ]";
    }
    */
}