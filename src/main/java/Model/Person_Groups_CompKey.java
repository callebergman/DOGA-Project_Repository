/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Hikari
 */
    @Embeddable
    public class Person_Groups_CompKey implements Serializable{
        
        private String rolename;
        private String username; 

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 83 * hash + Objects.hashCode(this.rolename);
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
            final Person_Groups_CompKey other = (Person_Groups_CompKey) obj;
            if (!Objects.equals(this.rolename, other.rolename)) {
                return false;
            }
            if (!Objects.equals(this.username, other.username)) {
                return false;
            }
            return true;
        } 
}