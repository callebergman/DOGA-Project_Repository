/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ApplicantFacade;
import Model.ApplicationDTO;
import Model.Availability;
import Model.Competence;
import Model.Competence_profile;
import Model.Person;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.*;

/**
 *
 * @author Calle
 */
@ManagedBean(name = "frontManager")
@SessionScoped
public class FrontManager implements Serializable {

    @EJB
    private ApplicantFacade applicantFacade;
    
    @NotNull(message="Please enter your name")
    private String name;
    @NotNull(message="Please enter your last name")
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
    private String[] areas = new String[10];
    
    private List<Availability> availabilities;
    private List<Competence_profile>    competence_profiles;
    private List<Competence>    competences;
    
    private String fromDate;
    private String toDate;
    private int toYear;
    private int toMonth;
    private int toDay;
    private int fromYear;
    private int fromMonth;
    private int fromDay;
    private String transactionFailure;

    @PostConstruct
    private void init() {
        competences = applicantFacade.getCompetences();
        for (int i = 0; i < competences.size(); i++) {
            areas[i] = competences.get(i).getName();
        }
    }

    /**
     * Creates a new instance of LoginManager
     */
    public FrontManager() {
        competences = new ArrayList<Competence>();
        availabilities = new ArrayList<Availability>();
        competence_profiles = new ArrayList<Competence_profile>();
    }

    public void login() {
        transactionFailure = "a";
    }

    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    public String getTransactionFailure() {
        return transactionFailure;
    }

    public void setTransactionFailure(String transactionFailure) {
        this.transactionFailure = transactionFailure;
    }

    public void addCompetence() {
        competence_profiles.add(new Competence_profile(applicantFacade.getCompetenceID(currentArea), this.years));
    }

    /**
     * Adds an availability date
     *
     * @return jsf22Bugfix();
     */
    public void addAvailability() {
        /* Date fDate = null;
         Date tDate = null;
         */
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

        /*
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
         try 
         {
         fDate = (df.parse(getFromDate()));
         tDate = (df.parse(getToDate()));
         }
         catch(ParseException e){}        
         */
        availabilities.add(new Availability(this.fromDate, this.toDate));
        //applicantFacade.testMethod();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int newYears) {
        years = newYears;
    }

    public String[] getAreas() {
        return areas;
    }

    public String getCurrentArea() {
        return currentArea;
    }

    public void setCurrentArea(String newArea) {
        currentArea = newArea;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newLast) {
        this.lastName = newLast;
    }

    public String getEmail() {
        return email;
    }

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

    public void sendApp() {
        applicantFacade.submitApplication(new ApplicationDTO(new Person(this.name, this.lastName, this.email),
                competence_profiles, availabilities));
    }

    public String findCompetenceName(BigInteger competence_id) {
        for (int i = 0; i < competences.size(); i++) {
            if (competences.get(i).getCompetence_id() == competence_id) {
                return competences.get(i).getName();
            }
        }
        return " ";
    }
}
