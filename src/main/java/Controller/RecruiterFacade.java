/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ApplicationDTO;
import Model.Competence;
import Model.Person;
import Model.SubmissionException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hikari
 *The RecruiterFacade class Handles the retrieval of Applications
 *from the database
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RecruiterFacade {
    
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;
    
    /**
     *@return list with all the applicants 
     */
    public List<ApplicationDTO> getAllApplications ()
    {   
        List<ApplicationDTO>    list = new ArrayList<ApplicationDTO> ();
        try{
            Query   query = em.createQuery ("SELECT c FROM Person c WHERE c.role.name=:n");
            query.setParameter ("n", "Applicant");
            List<Person>    plist = query.getResultList ();

            Person  p;
            for (int i=0; i<plist.size(); i++)
            {
                ApplicationDTO  dto = new ApplicationDTO ();

                p = plist.get(i);
                dto.setPerson(p);

                dto.setCompetences(p.getCompetence_profiles());
                
                dto.setAvailabilitys(p.getAvailabilitys());

                list.add (dto);
            }
        }
        catch (Exception    e){
            throw new SubmissionException(getRootMsg (e));  
        }
        return list;
    }
   
    public String getCompetenceName (BigInteger competence_id){
        Competence  c;
        try{
            Query   query = em.createQuery ("SELECT c FROM Competence c WHERE c.competence_id=:i");
            query.setParameter ("i", competence_id);
            c = (Competence) query.getSingleResult();
        }
        catch (Exception    e){
            throw new SubmissionException(getRootMsg (e));  
        }
        return (c.getEng_name());
    }
    
    /**
     * 
     * @param e
     * @return message
     * Returns the most inner internal exceptions message
     */
    private String getRootMsg (Exception e){
        if(e.getClass().isInstance(new SubmissionException ()))
            return e.getMessage();
        
        Throwable t = e.getCause();
        if (t != null){
            while (t.getCause() != null)
                t = t.getCause();
            return t.getClass().getName() + " "+ t.getMessage();
        }
        else
            return e.getClass().getName() + " "+  e.getMessage();
        }
    }
