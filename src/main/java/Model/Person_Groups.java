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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Hikari
 */
@Entity
public class Person_Groups implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String GroupID;
    @Id
    @OneToOne
    @JoinColumn(name = "username")
    private Person user;

    public String getGroupID() {
        return GroupID;
    }

    public void setGroupID(String GroupID) {
        this.GroupID = GroupID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String Username) {
        this.username = Username;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (GroupID != null ? GroupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person_Groups)) {
            return false;
        }
        Person_Groups other = (Person_Groups) object;
        if ((this.GroupID == null && other.GroupID != null) || (this.GroupID != null && !this.GroupID.equals(other.GroupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Person_Groups[ id=" + GroupID + " ]";
    }
    
}
