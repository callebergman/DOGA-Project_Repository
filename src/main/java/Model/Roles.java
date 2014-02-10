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
import java.util.Objects;
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
    private BigInteger role_id;
    private String name;
    
    @OneToMany(mappedBy="role",cascade=REMOVE)
    private Collection<Person_Groups> person_groups = new HashSet ();
    
    @OneToMany(mappedBy="role",cascade=REMOVE)
    private Collection<Person> persons = new HashSet ();

    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }
    
    public BigInteger getId() {
        return role_id;
    }

    public void setId(BigInteger role_id) {
        this.role_id = role_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.role_id);
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
        final Roles other = (Roles) obj;
        if (!Objects.equals(this.role_id, other.role_id)) {
            return false;
        }
        return true;
    }
    
}
