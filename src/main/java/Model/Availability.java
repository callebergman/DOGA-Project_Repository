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

/**
 *
 * @author User
 */
@Entity
public class Availability implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger availability_id;
    private String from_date;
    private String to_date;

    public Availability() {
    }

    public Availability(String from_date, String to_date) {
        this.from_date = from_date;
        this.to_date = to_date;
    }
    
    @ManyToOne
    @JoinColumn(name="person_id", insertable=false, updatable=false)
    private Person person;

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (availability_id != null ? availability_id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        if ((this.availability_id == null && other.availability_id != null) || (this.availability_id != null && !this.availability_id.equals(other.availability_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[[from_date] ~ [to_date]]";
    }
}
