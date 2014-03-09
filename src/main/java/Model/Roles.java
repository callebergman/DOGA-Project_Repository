/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Hikari
 */
@Entity
public class Roles implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String name;
    
    @OneToMany(mappedBy="role",cascade=ALL)
    private List<Authentication_Table> auth_tables = new ArrayList<Authentication_Table>();
    
    @OneToMany(mappedBy="role",cascade=ALL)
    private List<Person> persons = new ArrayList<Person>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Authentication_Table> getAuth_tables() {
        return auth_tables;
    }

    public void setAuth_tables(List<Authentication_Table> auth_tables) {
        this.auth_tables = auth_tables;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    
    public void addPerson (Person p){
        p.setRole(this);
        persons.add (p);
    }
    
    public void addAuthentication_Table (Authentication_Table at){
        at.setRole(this);
        auth_tables.add (at);
    }
}