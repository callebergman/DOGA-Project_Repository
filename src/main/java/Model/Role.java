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
import javax.persistence.OneToMany;

/**
 *
 * @author Calle
 */
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private BigInteger role_id;
    private String name;
    private Person person;

   
/*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }*/

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.role_id == null && other.role_id != null) || (this.role_id != null && !this.role_id.equals(other.role_id))) {
            return false;
        }
        return true;
    
    }
    @OneToMany
    public Person
            getPerson()
            {
                return person;
            }
            void setPerson(Person person)
            {
                this.person=person;
            }

    @Override
    public String toString() {
        return "Model.Role[ id=" + role_id + " ]";
    }

    /**
     * @return the role_id
     */
    public BigInteger getRole_id() {
        return role_id;
    }

    /**
     * @param role_id the role_id to set
     */
    public void setRole_id(BigInteger role_id) {
        this.role_id = role_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
