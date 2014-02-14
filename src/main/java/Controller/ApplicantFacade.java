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
import Model.Person_Groups;
import Model.Roles;
import View.FrontManager;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
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
   
    private BigInteger person_id; 
    private Person person;
    private List<Competence_profile> competences;
    private List<Availability> availabilitys;
    private FrontManager f;
    
    /*
    public void addApplicant (String name, String surname, String email){
        person = new Person (name, surname, email);
    }
    
    public void addCompetenceProfile (String competenceName, int years_of_experience){
        Competence c = em.find (Competence.class, competenceName);
        BigInteger  competence_id = c.getCompetence_id();
        competences.add (new Competence_profile (competence_id, years_of_experience));
    }
    
    public void addAvailability (Date from_date, Date to_date){
        availabilitys.add (new Availability (from_date, to_date));
    }
    
    public List<Availability> getAvailabilities() {
        return availabilitys;
    }
    
    public void testMethod (){
        Person  p = new Person ();
        p.setPassword("1234");
        p.setUsername("root");
        p.setPerson_id(BigInteger.valueOf (1));
        p.setRole_id(BigInteger.valueOf (1));
        em.persist(p);
        Person_Groups   pg = new Person_Groups ();
        pg.setRolename("Recruiter");
        pg.setUsername("root");
        em.persist(pg);
    }
    */
    public void submitApplication (ApplicationDTO ADTO) {
        person = ADTO.getPerson();
        person.setRole_id(BigInteger.valueOf (2));
        em.persist(person);
        
        person_id = (BigInteger)em.createQuery("select max(u.person_id) from Person u").getSingleResult();
        
        competences = ADTO.getCompetences();
        for(Competence_profile cp : competences){
            cp.setPerson_id(person_id);
            em.persist(cp);
        }
        
        availabilitys = ADTO.getAvailabilitys();
        for(Availability availability : availabilitys){
            availability.setPerson_id(person_id);
            em.persist(availability);
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
     /*   
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
        
        for (int i=0; i<areas.length; i++){
            Competence c = new Competence ();
            String s = Integer.toString(i);
            c.setCompetence_id(new BigInteger (s));
            c.setName(areas[i]);
            em.persist (c);
        }*/
        
       */ 
        Query   query = em.createQuery ("SELECT c FROM Competence c");
        return query.getResultList ();
    }
}
