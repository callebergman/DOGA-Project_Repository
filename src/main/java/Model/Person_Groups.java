/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.math.BigInteger;
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
    
    private BigInteger role_id;
    private BigInteger person_id;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id", insertable=false, updatable=false)
    private Roles    role;
    
    @Id
    @OneToOne
    @JoinColumn(name = "person_id", insertable=false, updatable=false)
    private Person person;
}
