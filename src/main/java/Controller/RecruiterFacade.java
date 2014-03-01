/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ApplicationDTO;
import Model.Competence;
import Model.Person;
import Model.Roles;
import java.math.BigInteger;
import java.util.ArrayList;
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
public class RecruiterFacade {
    
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;
    
    /**
     *@return list 
     */
    public List<ApplicationDTO> getAllApplications ()
    {   
        List<ApplicationDTO>    list = new ArrayList<ApplicationDTO> ();
        Query   query = em.createQuery ("SELECT c FROM Person c WHERE c.role.name=:n");
        query.setParameter ("n", "Applicant");
        List<Person>    plist = query.getResultList ();
        
        Person  p;
        for (int i=0; i<plist.size(); i++)
        {
            ApplicationDTO  dto = new ApplicationDTO ();
            
            p = plist.get(i);
            dto.setPerson(p);
            
            query = em.createQuery ("SELECT c FROM Competence_profile c WHERE c.person.person_id=:n");
            query.setParameter ("n", p.getPerson_id());
            dto.setCompetences(query.getResultList());
            
            query = em.createQuery ("SELECT c FROM Availability c WHERE c.person.person_id=:n");
            query.setParameter ("n", p.getPerson_id());
            dto.setAvailabilitys(query.getResultList());
            
            list.add (dto);
        }
        
        return list;
    }
   
    public String getCompetenceName (BigInteger competence_id){
        String name;
        Competence  c = em.find (Competence.class, competence_id);
        return (c.getName());
    }
}
