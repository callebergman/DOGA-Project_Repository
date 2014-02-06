/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
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
    private Date from_date;
    private Date to_date;
    private BigInteger person_id;

    public Availability() {
    }

    public Availability(Date from_date, Date to_date) {
        this.from_date = from_date;
        this.to_date = to_date;
    }
    
    @ManyToOne
    @JoinColumn(name="person_id", insertable=false, updatable=false)
    private Person person;

        public BigInteger getId() {
        return availability_id;
    }

    public void setId(BigInteger id) {
        this.availability_id = availability_id;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public BigInteger getPerson_id() {
        return person_id;
    }

    public void setPerson_id(BigInteger person_id) {
        this.person_id = person_id;
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
        return "Model.Availability[ id=" + availability_id + " ]";
    }
    
}
