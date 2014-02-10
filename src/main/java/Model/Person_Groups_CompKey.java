/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author Hikari
 */
    public class Person_Groups_CompKey implements Serializable{
        private BigInteger role_id;
        private BigInteger person_id;

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + Objects.hashCode(this.role_id);
            hash = 37 * hash + Objects.hashCode(this.person_id);
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
            if (!Objects.equals(this.role_id, other.role_id)) {
                return false;
            }
            if (!Objects.equals(this.person_id, other.person_id)) {
                return false;
            }
            return true;
        }  
    }