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

    public Person getPerson() {
        return person;
    }

    public List<Competence_profile> getCompetences() {
        return competences;
    }

    public List<Availability> getAvailabilitys() {
        return availabilitys;
    }
}
