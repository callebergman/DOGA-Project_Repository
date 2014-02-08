/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Controller.ApplicantFacade;
import Model.Availability;
import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Calle
 */
//@ManagedBean
@ManagedBean(name="frontManager", eager=true)
//@Named("frontManager")
@SessionScoped
public class FrontManager implements Serializable{
    
    @EJB
    private ApplicantFacade applicantFacade;
    
    private String name;
    private String lastName;    
    private String email;
    private int years;
    private String currentArea;
    private String[] areas = new String[10];
    
    private List<Availability> availabilities;
    private String fromDate;
    private String toDate;
        
    private int toYear;
    private int toMonth;
    private int toDay;
    private int fromYear;
    private int fromMonth;
    private int fromDay;    
    private String transactionFailure;
    
    /**
     * Creates a new instance of LoginManager
     */
    public FrontManager() {
        areas[0] = "Korvgrillning";
        areas[1] = "Karuselldrift";
        areas[2] = "Diskotering";
        areas[3] = "Programmering";
        areas[4] = "Bilkörning";
        areas[5] = "Lärande";
        areas[6] = "Bakning";
        areas[7] = "Servering";
        areas[8] = "Taxering";
        areas[9] = "Servicing";
        availabilities = new ArrayList<Availability> ();
    }
    
    public void login () {
        transactionFailure = "a";
    }

   /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getYears()
    {
        return years;
    }
    
    public void setYears(int newYears)
    {
        years = newYears;
    }
    
    public String[] getAllAreas()
    {
        return areas;
    }
    
    public String[] getAreas(){
        return areas;
    }
    
    public String getCurrentArea()
    {
        return currentArea;
    }
    
    public void setCurrentArea(String newArea)
    {
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

    public String getTransactionFailure() {
        return transactionFailure;
    }

    public void setTransactionFailure(String transactionFailure) {
        this.transactionFailure = transactionFailure;
    }
    
     /**
     * Adds an availability date
     * @return jsf22Bugfix();
     */
    public void addAvailability () {
        Date fDate = null;
        Date tDate = null;
        if(fromMonth < 10 && fromDay < 10)
            setFromDate(Integer.toString(fromYear)+ "0" + Integer.toString(fromMonth)+ "0" + Integer.toString(fromDay));
        else if(fromMonth < 10 && fromDay > 9)
            setFromDate(Integer.toString(fromYear)+ "0" + Integer.toString(fromMonth)+ "" + Integer.toString(fromDay));
        else if(fromMonth > 9 && fromDay < 10)
            setFromDate(Integer.toString(fromYear)+ ""+ Integer.toString(fromMonth)+ "0" +Integer.toString(fromDay));
        else
            setFromDate(Integer.toString(fromYear)+ ""+ Integer.toString(fromMonth)+ "" + Integer.toString(fromDay));
        
        if(toMonth < 10 && toDay < 10)
            setToDate(Integer.toString(toYear)+ "0" + Integer.toString(toMonth)+ "0" + Integer.toString(toDay));
        else if(toMonth < 10 && toDay > 9)
            setToDate(Integer.toString(toYear)+ "0" + Integer.toString(toMonth)+ "" + Integer.toString(toDay));
        else if(toMonth > 9 && toDay < 10)
            setToDate(Integer.toString(toYear)+ ""+ Integer.toString(toMonth)+ "0" +Integer.toString(toDay));
        else
            setToDate(Integer.toString(toYear)+ ""+ Integer.toString(toMonth)+ "" + Integer.toString(toDay));
        
        DateFormat df = new SimpleDateFormat("ddMMyyyy");
        
        try 
        {
             fDate = (df.parse(getFromDate()));
             tDate = (df.parse(getToDate()));
        }
        catch(ParseException e)
        {
            
        }        
        //applicantFacade.addAvailability(fDate, tDate);
        availabilities.add(new Availability(new java.sql.Date (fDate.getTime()), new java.sql.Date (tDate.getTime())));
        //return jsf22Bugfix();
    }
    
     /**
     * Returns the current availabilities
     * @return the availability
     */
    public List<Availability> getAvailabilities() {
        return availabilities;
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

      /**
     * This return value is needed because of a JSF 2.2 bug. Note 3 on page 7-10
     * of the JSF 2.2 specification states that action handling methods may be
     * void. In JSF 2.2, however, a void action handling method plus an
     * if-element that evaluates to true in the faces-config navigation case
     * causes an exception.     *
     * @return an empty string.
     */
    private String jsf22Bugfix() {
        return "";
    }  
}
