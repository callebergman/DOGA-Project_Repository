/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Availability;
import Model.Competence;
import Model.Competence_profile;
import Model.Person;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hikari
 */
@Stateful
public class ApplicantFacade {
    
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;
   
    private BigInteger person_id;
    private Person person;
    private List<Competence_profile> competences;
    private List<Availability> availabilitys;
    
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
    
    public void submitApplication () {
        em.persist(person);
        person_id = (BigInteger)em.createQuery("select max(u.id) from User u").getSingleResult();
        
        for(Competence_profile cp : competences){
            cp.setPerson_id(person_id);
            em.persist(cp);
        }
        
        for(Availability availability : availabilitys){
            availability.setPerson_id(person_id);
            em.persist(availability);
        }
    }
}