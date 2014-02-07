/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Person;
import Model.PersonDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hikari
 */
@Stateless
public class LoginFacade {
    
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;
    
    private PersonDTO findUser (String username, String passwd){
        Query   query = em.createQuery ("SELECT c FROM Person c WHERE c.username=:un AND c.password=:pw");
        query.setParameter ("un", username);
        query.setParameter ("pw", passwd);
        List    list = query.getResultList ();
        PersonDTO   person = (Person)list.get(0);
        return person;
    }
}
