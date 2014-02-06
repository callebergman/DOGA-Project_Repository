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
    
    public void addCompetenceProfile (int years_of_experience){
        competences.add (new Competence_profile (years_of_experience));
    }
    
    public void addAvailability (Date from_date, Date to_date){
        availabilitys.add (new Availability (from_date, to_date));
    }
    /*
    public void submitApplication () {
        em.persist(person);
        person_id = (BigInteger)em.createQuery("select max(u.id) from User u").getSingleResult();
        
        for(Competence_profile cp : competences){
            BigInteger competence_id = em.find (Competence.class, cp.getCompetence_id());
        }
    }
    */
}