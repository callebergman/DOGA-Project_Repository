/*
 *  This DTO is used to represent a application submitted by a user 
 */

package Model;

import java.util.List;

/**
 *
 * @author Fredrik
 */

public class ApplicationDTO
{
    private Person person;
    private List<Competence_profile> competences;
    private List<Availability> availabilitys;

    public ApplicationDTO(Person person, List<Competence_profile> competences, List<Availability> availabilitys) {
        this.person = person;
        this.competences = competences;
        this.availabilitys = availabilitys;
    }

    public ApplicationDTO() {

    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Competence_profile> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence_profile> competences) {
        this.competences = competences;
    }

    public List<Availability> getAvailabilitys() {
        return availabilitys;
    }

    public void setAvailabilitys(List<Availability> availabilitys) {
        this.availabilitys = availabilitys;
    }
}
