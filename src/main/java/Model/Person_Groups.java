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
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Hikari
 */
@Entity
@IdClass(Person_Groups_CompKey.class)
public class Person_Groups implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static enum ROLE {
 
        ADMINISTRATOR, USER, GUEST
    }
    
    @Id
    private String rolename;
    @Id
    private String username;

    @ManyToOne
    @JoinColumn(name = "rolename", insertable=false, updatable=false, referencedColumnName = "name")
    private Roles    role;
        
    @OneToOne
    @JoinColumn(name = "username", insertable=false, updatable=false, referencedColumnName = "username")
    private Person person;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.rolename);
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
        final Person_Groups other = (Person_Groups) obj;
        if (!Objects.equals(this.rolename, other.rolename)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
    
    
}