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
import java.math.BigInteger;
import java.util.Collection;
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
    
    public void submitApplication (ApplicationDTO ADTO) 
    {
        Roles    role = em.find(Roles.class, BigInteger.valueOf (2));
        Person  person = ADTO.getPerson();
        Collection<Person>   list = role.getPersons();
        list.add(person);
        
        List<Competence_profile>  competences = ADTO.getCompetences();
        person.setCompetence_profiles(competences);
        
        List<Availability>  availabilitys = ADTO.getAvailabilitys();
        person.setAvailabilitys(availabilitys);
        
        em.persist(person);
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
