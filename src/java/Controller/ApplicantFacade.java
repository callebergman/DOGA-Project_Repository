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
import Model.CompetenceDTO;
import Model.Credential;
import Model.Roles;
import Model.SubmissionException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author Hikari
 * The ApplicantFacade class handles the creation of competences, 
 * a recruiter account and to submit a application
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApplicantFacade {
    
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;
    private Log log;
    /***
     *testMethod is used to insert the competence data in the database
     * and making a recruiter account with a password and username
     */
    public void testMethod ()
    {
        String[] areas_eng = new String[10];
        areas_eng[0] = "Sausage";
        areas_eng[1] = "Carousel Operation";
        areas_eng[2] = "Disco";
        areas_eng[3] = "Programming";
        areas_eng[4] = "Guiding";
        areas_eng[5] = "Maskin reparation";
        areas_eng[6] = "Baking";
        areas_eng[7] = "Serving";
        areas_eng[8] = "Taxation";
        areas_eng[9] = "Servicing";
        
        String[] areas_fr = new String[10];
        areas_fr[0] = "Saucisse à griller";
        areas_fr[1] = "Opération carrousel";
        areas_fr[2] = "Le rabais";
        areas_fr[3] = "Programmation";
        areas_fr[4] = "Guidage";
        areas_fr[5] = "La réparation de la machine";
        areas_fr[6] = "Cuisson";
        areas_fr[7] = "Service";
        areas_fr[8] = "Le évalué";
        areas_fr[9] = "Entretien";
        
        for (int i=0; i<areas_fr.length; i++)
        {
            Competence c = new Competence ();
            String s = Integer.toString(i);
            c.setCompetence_id(new BigInteger (s));
            c.setName (areas_eng[i]);
            c.setFr_name(areas_fr[i]);
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
    
    /**
     *@param ADTO holds a DTO object with information regarding
     * the applicant that is submiting a application
     * submits a application towards the database
     * @throws java.text.ParseException
     * 
     */
    public void submitApplication (ApplicationDTO ADTO) throws ParseException 
    {
        try {
            Roles    role = em.find(Roles.class, "Applicant");
            Person  person = ADTO.getPerson();

            if ((person.getName()== null || person.getName().trim().length() == 0)|| (person.getSurname()== null || person.getSurname().trim().length() == 0) || (person.getEmail()== null || person.getEmail().trim().length() == 0))
                throw new SubmissionException("Name and email is mandatory || Namn och email är obligatoriskt");
            
            List<Competence_profile>  competences = ADTO.getCompetences();
            if (competences.isEmpty())
                throw new SubmissionException("No competence submitted || Ingen kvalifikation tillgat" );

            Set<Integer>    tmp = new HashSet ();
            for (Competence_profile cp : competences) 
            {
                BigInteger t = cp.getCompetence().getCompetence_id();
                if (!tmp.add (t.intValue()))
                {
                    throw new SubmissionException("Duplicate competence submitted || Fler av samma kvalifikation tillgat");
                }
                person.addCompetence_profiles(cp);
            }

            DateFormat formatter = new SimpleDateFormat("MM-dd-yy");
            Date fromDate;
            Date toDate;
            List<Availability>  availabilitys = ADTO.getAvailabilitys();

            if (availabilitys.isEmpty())
                throw new SubmissionException("No availability submitted || Ingen tillgänglighet tillgat");

            for (Availability a : availabilitys) 
            {
                fromDate = (formatter.parse(a.getFrom_date()));
                toDate = (formatter.parse(a.getTo_date()));

                if (fromDate.equals(toDate))
                    throw new SubmissionException("Start date cannot be same as end date || Start datum kan inte vara samma som slutdatum");
                if (!fromDate.before(toDate))
                    throw new SubmissionException("End date cannot be earlier thant start date || Slut datum kan inte vara innan start datum");
                person.addAvailability(a);
            }
            role.addPerson(person);
        }
        catch (Exception   e){
            throw new SubmissionException(getRootMsg (e));  
        }
    }
    
    /**
     *@return competence list
     */
    public List<CompetenceDTO> getCompetences ()
    {
        try{
            Query   query = em.createQuery ("SELECT c FROM Competence c");
            return query.getResultList ();
        }
        catch (Exception    e){
            throw new SubmissionException(getRootMsg (e));  
        }
    }
    
    /**
     *@param name of the competence you want
     *@return The found Comepetence
     */
    public CompetenceDTO    getCompetence (String name){
        try{
            Query   query = em.createQuery ("SELECT c FROM Competence c WHERE c.name=:n");
            query.setParameter ("n", name);
            Competence   c = (Competence) query.getSingleResult();
            return c;
        }
        catch (RuntimeException    e){
            throw new SubmissionException(getRootMsg (e));  
        }
    }
    /**
     * 
     * @param email 
     * Just throw submissionException if there is a Person with 
     * given email 
     */
    public void validateEmail (String email)
    {
        try{
            Query   query = em.createQuery ("SELECT c FROM Person c WHERE c.email=:n");
            query.setParameter ("n", email);
            Person   tmp = (Person) query.getSingleResult();
            if (tmp !=null)
                throw new SubmissionException("Email is already in use || Email används redan"); 
        }
        catch (NoResultException  e){ }
        catch (RuntimeException    e){
            throw new SubmissionException(getRootMsg (e));  
        }
    }
    /**
     * 
     * @param Exception e
     * @return Message of the most inner cause
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

