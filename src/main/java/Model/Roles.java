/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
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
    @Column(unique=true)
    private String name;
    
    @OneToMany(mappedBy="role",cascade=ALL)
    private List<Person_Groups> person_groups = new ArrayList<Person_Groups>();
    
    @OneToMany(mappedBy="role",cascade=ALL)
    private List<Person> persons = new ArrayList<Person>();

    public Roles() {
    }

    public BigInteger getRole_id() {
        return role_id;
    }

    public void setRole_id(BigInteger role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Person_Groups> getPerson_groups() {
        return person_groups;
    }

    public void setPerson_groups(List<Person_Groups> person_groups) {
        this.person_groups = person_groups;
    }

    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson (Person p){
        persons.add (p);
    }
    
    public void addPerson_Group (Person_Groups pg){
        pg.setRole(this);
        person_groups.add (pg);
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.role_id);
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