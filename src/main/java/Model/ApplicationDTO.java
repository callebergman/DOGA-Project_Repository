/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Fredrik
 */
public class ApplicationDTO
{
    private Person person;
    private Competence_profile competence_profile;
    private Availability availability;
    public ApplicationDTO(Person person,Competence_profile competence_profile,Availability availability)
    {
        this.person=person;
        this.competence_profile=competence_profile;
        this.availability=availability;
    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return the competence_profile
     */
    public Competence_profile getCompetence_profile() {
        return competence_profile;
    }

    /**
     * @param competence_profile the competence_profile to set
     */
    public void setCompetence_profile(Competence_profile competence_profile) {
        this.competence_profile = competence_profile;
    }

    /**
     * @return the availability
     */
    public Availability getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
