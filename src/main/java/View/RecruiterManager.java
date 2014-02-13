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
    
    @PostConstruct
    public void init()
    {
        applicationsList = recruiterFacade.getAllApplications();
    }
    
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
    
}
