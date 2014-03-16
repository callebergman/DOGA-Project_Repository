/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Hikari
 * A Entity class
 */
@Entity
public class Authentication_Table implements Serializable {
    
    private static final long serialVersionUID = 1L;
        
    @ManyToOne
    @JoinColumn(name = "roleName")
    private Roles    role;
       
    @OneToOne
    @Id
    @JoinColumn(name = "userName")
    private Credential credential;

    /****
     *@return role 
     */
    public Roles getRole() {
        return role;
    }
    /****
     *@param role sets a new role 
     */
    public void setRole(Roles role) {
        this.role = role;
    }
    /***
     *@return credential 
     */
    public Credential getCredential() {
        return credential;
    }
    /***
     *@param credential sets a new credential 
     */
    public void setCredential(Credential credential) {
        this.credential = credential;
    }
  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.role);
        hash = 23 * hash + Objects.hashCode(this.credential);
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
        final Authentication_Table other = (Authentication_Table) obj;
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.credential, other.credential)) {
            return false;
        }
        return true;
    }
}