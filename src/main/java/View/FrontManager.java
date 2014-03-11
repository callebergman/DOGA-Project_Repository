/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicantFacade;
import Controller.Log;
import Model.ApplicationDTO;
import Model.Availability;
import Model.Competence;
import Model.CompetenceDTO;
import Model.Competence_profile;
import Model.Person;
import Model.SubmissionException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.*;

/**
 *
 * @author Calle
 * The FrontManager Class handles the exception messages that are being showed
 * when no input is made into the application field.
 */
@ManagedBean(name = "frontManager")
@SessionScoped
public class FrontManager implements Serializable {

    @EJB
    private ApplicantFacade applicantFacade;
    
    @Size(min=1, message="Please enter your name!")
    private String name;
    @Size(min=1, message="Please enter your last name!")
    private String lastName;    
    @NotNull(message="Please enter your email")
    @Pattern(regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Invalid e-mail")
    private String email;
    
    @NotNull(message="Please insert a year")
    @Min(value=1, message="Please insert a year value over 0")
    @Max(value=100, message="Please insert a year value below 100")    
    private int years;
    
    @NotNull(message="Please select at least one area of expertise")
    private String currentArea;
    private List<String>    competence_names;
    
    private List<Availability>  availabilities = new ArrayList<>();
    private List<Competence_profile>    competence_profiles = new ArrayList<>();
    private List<CompetenceDTO>    competences = new ArrayList<>();
    
    private String fromDate;
    private String toDate;
    private int toYear;
    private int toMonth;
    private int toDay;
    private int fromYear;
    private int fromMonth;
    private int fromDay;
    private SubmissionException transactionFailure;
    private SubmissionException OldTransactionFailure;
    
    public Log log = new Log();
    
    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        OldTransactionFailure = transactionFailure;
        transactionFailure = null;
        return OldTransactionFailure == null;
    }

    /**
     * @return latest thrown exception
     */
    public SubmissionException getException() {
        return OldTransactionFailure;
    }
    
    /**
     * @return empty string
     */
    private String jsf22Bugfix() {
        return "";
    }
    
    /***
     * adds a competence_profile to the current list
     *@return jsf22Bugfix  
     */
    public String addCompetence() 
    {
        try{
            competence_profiles.add(new Competence_profile(this.years, (Competence) competences.get(competence_names.indexOf(currentArea))));
        }
        catch (SubmissionException e) {
            transactionFailure = e;
        }
        return jsf22Bugfix();
    }

    /**
     * Adds an availability date
     *
     * @return jsf22Bugfix();
     */
    public String addAvailability() {
        if (fromMonth < 10 && fromDay < 10) {
            setFromDate(Integer.toString(fromYear) + "-0" + Integer.toString(fromMonth) + "-0" + Integer.toString(fromDay));
        } else if (fromMonth < 10 && fromDay > 9) {
            setFromDate(Integer.toString(fromYear) + "-0" + Integer.toString(fromMonth) + "-" + Integer.toString(fromDay));
        } else if (fromMonth > 9 && fromDay < 10) {
            setFromDate(Integer.toString(fromYear) + "-" + Integer.toString(fromMonth) + "-0" + Integer.toString(fromDay));
        } else {
            setFromDate(Integer.toString(fromYear) + "-" + Integer.toString(fromMonth) + "-" + Integer.toString(fromDay));
        }

        if (toMonth < 10 && toDay < 10) {
            setToDate(toYear + "-0" + Integer.toString(toMonth) + "-0" + Integer.toString(toDay));
        } else if (toMonth < 10 && toDay > 9) {
            setToDate(toYear + "-0" + Integer.toString(toMonth) + "-" + Integer.toString(toDay));
        } else if (toMonth > 9 && toDay < 10) {
            setToDate(toYear + "-" + Integer.toString(toMonth) + "-0" + Integer.toString(toDay));
        } else {
            setToDate(toYear + "-" + Integer.toString(toMonth) + "-" + Integer.toString(toDay));
        }

        availabilities.add(new Availability (this.fromDate, this.toDate));

        return jsf22Bugfix();
    }

    /***
     *sends a application 
     * @return "complete"
     * @throws java.text.ParseException
     * @throws java.io.IOException
     */
    public String sendApp() throws ParseException, IOException {
       try{
           applicantFacade.validateEmail(this.email);

           applicantFacade.submitApplication(new ApplicationDTO(new Person(this.name, this.lastName, this.email), 
                   competence_profiles, availabilities));
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } 
        catch (SubmissionException e) {
            transactionFailure = e;
        }
        log.writetofile(this.name,"submits application");
        return "complete";
    }

    public void login () throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/DOGA-Project_Repository/faces/restricted/recruiterPage.xhtml");
    }
    
    /**
     *@return name 
     */
    public String getName() {
        return name;
    }
    /**
     *@param newName sets a new name
     */
    public void setName(String newName) {
        this.name = newName;
    }
    /****
     *@return years 
     */
    public int getYears() {
        return years;
    }
    /****
     *@param newYears sets a new year
     */
    public void setYears(int newYears) {
        years = newYears;
    }
    
    /**
     *@return lastName 
     */
    public String getLastName() {
        return lastName;
    }
    /**
     *@param newLast sets a new value to newLAst
     */
    public void setLastName(String newLast) {
        this.lastName = newLast;
    }
    /**
     *@return email 
     */
    public String getEmail() {
        return email;
    }
    /**
     *@param newEmail sets a new Email 
     */
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    /**
     * Returns the current availabilities
     *
     * @return the availability
     */
    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    /**
     * Returns the current competence_profiles
     *
     * @return the competence_profiles
     */
    public List<Competence_profile> getCompetence_profiles() {
        return competence_profiles;
    }

    /**
     * @return the toYear
     */
    public int getToYear() {
        return toYear;
    }

    /**
     * @param toYear the toYear to set
     */
    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    /**
     * @return the toMonth
     */
    public int getToMonth() {
        return toMonth;
    }

    /**
     * @param toMonth the toMonth to set
     */
    public void setToMonth(int toMonth) {
        this.toMonth = toMonth;
    }

    /**
     * @return the toDay
     */
    public int getToDay() {
        return toDay;
    }

    /**
     * @param toDay the toDay to set
     */
    public void setToDay(int toDay) {
        this.toDay = toDay;
    }

    /**
     * @return the fromYear
     */
    public int getFromYear() {
        return fromYear;
    }

    /**
     * @param fromYear the fromYear to set
     */
    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    /**
     * @return the fromMonth
     */
    public int getFromMonth() {
        return fromMonth;
    }

    /**
     * @param fromMonth the fromMonth to set
     */
    public void setFromMonth(int fromMonth) {
        this.fromMonth = fromMonth;
    }

    /**
     * @return the fromDay
     */
    public int getFromDay() {
        return fromDay;
    }

    /**
     * @param fromDay the fromDay to set
     */
    public void setFromDay(int fromDay) {
        this.fromDay = fromDay;
    }

    /**
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<String> getCompetence_names() {
        return competence_names;
    }

    public void setCompetence_names(List<String> competence_names) {
        this.competence_names = competence_names;
    }

    public String getCurrentArea() {
        return currentArea;
    }

    public void setCurrentArea(String currentArea) {
        this.currentArea = currentArea;
    }

    public List<CompetenceDTO> getCompetences() {
        return competences;
    }

    public void setCompetences(List<CompetenceDTO> competences) {
        this.competences = competences;
    }
}
