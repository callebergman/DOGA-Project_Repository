/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Controller.RecruiterFacade;
import Model.ApplicationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Hikari
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
    private String regDate;
    private String area;
    
    
    @PostConstruct
    public void init()
    {
        applicationsList = recruiterFacade.getAllApplications();
        setFilterList(applicationsList);
    }
    /****
     *Creates a new Arraylist 
     */
    public RecruiterManager()
    {
        applicationsList = new ArrayList<ApplicationDTO>();
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
     */
    public void commit() {
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
            // Last name
            if(!lastName.equals("") && checkList[i] == false)            
                if(!filterList.get(i).getPerson().getSurname().equals(lastName))
                    checkList[i] = true;
            //Time periods
            //Registration Date
            
            //Area of expertise
            if(!area.equals("") && checkList[i] == false)
            {
                for(int p=0; p < filterList.get(i).getCompetences().size(); p++)
                {
                    // If true, means we found the competence in the list
                    if(recruiterFacade.getCompetenceName(filterList.get(i).getCompetences().get(p).getCompetence_profile_id()).equals(area))
                        p = filterList.get(i).getCompetences().size();
                    // Else, remove the person from the list
                    else if(p == filterList.get(i).getCompetences().size() - 1)
                        checkList[i] = true;                  
                }
            }               
        }
        for(int i=checkList.length - 1; i>=0; i--)
            if(checkList[i] == true)
                filterList.remove(i);              
        
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
     * @return the regDate
     */
    public String getRegDate() {
        return regDate;
    }

    /**
     * @param regDate the regDate to set
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
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
    
}
