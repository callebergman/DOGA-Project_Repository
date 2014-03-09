/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Controller.Log;
import Controller.RecruiterFacade;
import Model.ApplicationDTO;
import Model.SubmissionException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hikari
 * RecruiterManager class handles everything on the recruiter side of the webpage
 */

@ManagedBean(name="recruiterManager", eager=true)
@SessionScoped
public class RecruiterManager {
    
    
    @EJB
    private RecruiterFacade recruiterFacade;
    
    private List<ApplicationDTO> applicationsList;
    private List<ApplicationDTO> filterList;
    
    private String firstName;
    private String lastName;
    private String fromDate;
    private String toDate;
    private String area;
    private Exception transactionFailure;
    private Exception OldTransactionFailure;
    public Log log = new Log();
    
    @PostConstruct
    public void init()
    {
        try{
            applicationsList = recruiterFacade.getAllApplications();
            setFilterList(applicationsList);
        }catch (SubmissionException e) {
            transactionFailure = e;
        }
    }
    /****
     *Creates a new Arraylist 
     */
    public RecruiterManager()
    {
        applicationsList = new ArrayList<ApplicationDTO>();
    }
    
    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        if (OldTransactionFailure == transactionFailure)
            transactionFailure = null;
        else 
            OldTransactionFailure = transactionFailure;
        return transactionFailure == null;
    }
    
    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }
    
    /****
     *Handles bugfixes
     */
    private String jsf22Bugfix() {
        return "";
    }
    
    /**
     * @return the applicationsList
     */
    public List<ApplicationDTO> getApplicationsList() {
        return applicationsList;
    }

    /**
     * @param applicationsList the applicationsList to set
     */
    public void setApplicationsList(List<ApplicationDTO> applicationsList) {
        this.applicationsList = applicationsList;
    }
    
    /**
     * Filters the list of applications after the preffered details
     * @return jsf22Bugfix ()
     * @throws java.io.IOException
     */
    public String commit() throws IOException {
        try {
            filterList = new ArrayList<ApplicationDTO>();
            filterList = recruiterFacade.getAllApplications();

            // Checklist for if an application should be filtered or not
            // True = Should not be in the list, False = should be in the list
            boolean[] checkList = new boolean[filterList.size()];
            for(int i=0; i<filterList.size(); i++)
            {
                // First name
                checkList[i] = false;
                if(!firstName.equals(""))            
                    if(!filterList.get(i).getPerson().getName().equals(firstName))
                        checkList[i] = true;
                        log.writetofile("Recruiter","Filtered by first name");
                // Last name
                if(!lastName.equals("") && checkList[i] == false)            
                    if(!filterList.get(i).getPerson().getSurname().equals(lastName))
                        checkList[i] = true;
                         log.writetofile("Recruiter","Filtered by last name");
                //Time periods
                if(checkList[i] == false && !toDate.equals("") && !fromDate.equals(""))
                {
                    for(int p = 0; p < filterList.get(i).getAvailabilitys().size(); p++)
                    {                    
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try
                        {
                        Date fdate = dateFormat.parse(filterList.get(i).getAvailabilitys().get(p).getFrom_date());
                        Date tdate = dateFormat.parse(filterList.get(i).getAvailabilitys().get(p).getTo_date());

                        Date filterToDate = dateFormat.parse(toDate);
                        Date filterFromDate = dateFormat.parse(fromDate);

                        if(fdate.before(filterFromDate) && tdate.after(filterToDate))
                        {
                            p = filterList.get(i).getAvailabilitys().size();
                            log.writetofile("Recruiter","Filtered by Date");
                        }
                        else if(p == filterList.get(i).getAvailabilitys().size() - 1)
                            checkList[i] = true;

                        }
                        catch(ParseException e){}
                    }
                }

                //Area of expertise
                if(!area.equals("") && checkList[i] == false)
                {
                    for(int p=0; p < filterList.get(i).getCompetences().size(); p++)
                    {
                        // If true, means we found the competence in the list
                        if(recruiterFacade.getCompetenceName(filterList.get(i).getCompetences().get(p).getCompetence().getCompetence_id()).equals((area).trim()))
                        {
                            p = filterList.get(i).getCompetences().size();
                            log.writetofile("Recruiter","Filtered by expertise");
                        }
                        // Else, remove the person from the list
                        else if(p == filterList.get(i).getCompetences().size() - 1)
                            checkList[i] = true;                  
                    }
                }               
            }
            for(int i=checkList.length - 1; i>=0; i--)
                if(checkList[i] == true)
                    filterList.remove(i);        
        }catch (SubmissionException e) {
            transactionFailure = e;
        }
        
        return jsf22Bugfix();
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the filterList
     */
    public List<ApplicationDTO> getFilterList() {
        return filterList;
    }

    /**
     * @param newfilterList set the filterList to a new value
     */
    public void setFilterList(List<ApplicationDTO> newFilterList) {
        this.filterList = newFilterList;
    }
    
    /**
     * Invalidates the current session
     * @return 
     * @throws java.io.IOException
     */
    public String logout () throws IOException{
        FacesContext    context = FacesContext.getCurrentInstance();
        HttpServletResponse  response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("http://localhost:8080/DOGA-Project_Repository/faces/English/front.xhtml");
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "success";   
    } 
}
