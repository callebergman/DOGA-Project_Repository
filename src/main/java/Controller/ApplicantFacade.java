/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ApplicationDTO;
import Model.Availability;
import Model.Competence;
import Model.Competence_profile;
import Model.Person;
import Model.Authentication_Table;
import Model.Credential;
import Model.Roles;
import Model.SubmissionException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author Hikari
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApplicantFacade {
    
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    
    public void testMethod ()
    {
        String[] areas = new String[10];
        areas[0] = "Korvgrillning";
        areas[1] = "Karuselldrift";
        areas[2] = "Diskotering";
        areas[3] = "Programmering";
        areas[4] = "Guiding";
        areas[5] = "Maskin reparering";
        areas[6] = "Bakning";
        areas[7] = "Servering";
        areas[8] = "Taxering";
        areas[9] = "Servicing";
        
        for (int i=0; i<areas.length; i++)
        {
            Competence c = new Competence ();
            String s = Integer.toString(i);
            c.setCompetence_id(new BigInteger (s));
            c.setName(areas[i]);
            em.persist (c);
        }
        
        Roles   role = new Roles();
        role.setName("Recruiter");
        
        Roles   role2 = new Roles();
        role2.setName("Applicant");
      
        Person  person = new Person ();
        person.setName("Admin");
        person.setEmail("admin@kth.se");
        
        Credential  credential  = new Credential ();
        credential.setPassWord("1234");
        credential.setUserName("root");
        
        Authentication_Table    table = new Authentication_Table ();
        
        credential.setAuth_table(table);
        role.addAuthentication_Table(table);
        
        person.setCredential(credential);
        role.addPerson(person);
        
        em.persist(role);
        em.persist (role2);         
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void submitApplication (ApplicationDTO ADTO) throws SubmissionException 
    {
        Roles    role = em.find(Roles.class, "Applicant");
        Person  person = ADTO.getPerson();
        role.addPerson(person);
        
        List<Competence_profile>  competences = ADTO.getCompetences();
        
        Set<Integer>    tmp = new HashSet ();
        for (Competence_profile cp : competences) {
            BigInteger t = cp.getCompetence().getCompetence_id();
            if (!tmp.add (t.intValue()))
            {
                throw new SubmissionException("Duplicate competence submitted");
            }
            person.addCompetence_profiles(cp);
	}
        
        List<Availability>  availabilitys = ADTO.getAvailabilitys();
        for (Availability a : availabilitys) {
            person.addAvailability(a);
	}
    }
    
    public BigInteger getCompetenceID(String compName)
    {
        Query   query = em.createQuery ("SELECT c FROM Competence c WHERE c.name=:n");
        query.setParameter ("n", compName);
        Competence  tmp = (Competence) query.getSingleResult();
        return (tmp.getCompetence_id());
    }
    
    public List<Competence> getCompetences ()
    {
        Query   query = em.createQuery ("SELECT c FROM Competence c");
        return query.getResultList ();
    }
    
    public Competence   getCompetence (String comp){
        Query   query = em.createQuery ("SELECT c FROM Competence c WHERE c.name=:n");
        query.setParameter ("n", comp);
        Competence   c = (Competence) query.getSingleResult();
        return c;
    }
}
