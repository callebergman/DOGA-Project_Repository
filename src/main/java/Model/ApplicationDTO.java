/*
 *  This DTO is used to represent a application submitted by a user 
 */

package Model;

import java.util.List;

/**
 *
 * @author Fredrik
 * DTO class which handles a application
 */

public class ApplicationDTO
{
    private Person person;
    private List<Competence_profile> competences;
    private List<Availability> availabilitys;
    
    /**
     * Default constructor
     */
    public ApplicationDTO() {

    }
    
    /***
     * Constructor
     * @param person DTO
     * @param competences List with all the competences
     * @param availabilitys List with all the availabilitys
     */
    public ApplicationDTO(Person person, List<Competence_profile> competences, List<Availability> availabilitys) {
        this.person = person;
        this.competences = competences;
        this.availabilitys = availabilitys;
    }
    
    /***
     * @return person DTO
     */
    public Person getPerson() {
        return person;
    }
    
    /***
     *@param person sets a new value to person
     */
    public void setPerson(Person person) {
        this.person = person;
    }
    
    /****
     *
     *@return competences 
     */
    public List<Competence_profile> getCompetences() {
        return competences;
    }
    
    /***
     *@param competences sets a new value to competences
     */
    public void setCompetences(List<Competence_profile> competences) {
        this.competences = competences;
    }
    
    /***
     *@return availability
     */
    public List<Availability> getAvailabilitys() {
        return availabilitys;
    }
    
    /****
     *@param availabilitys sets a new value to availabilitys
     */
    public void setAvailabilitys(List<Availability> availabilitys) {
        this.availabilitys = availabilitys;
    }
}
