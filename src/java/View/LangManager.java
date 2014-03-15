/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Controller.ApplicantFacade;
import Model.CompetenceDTO;
import Model.SubmissionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Hikari
 * The LangManager class handles the switch from
 * The English to Swedish page and vice versa
 */

@ManagedBean(name = "langManager")
@SessionScoped
public class LangManager {
    
    @EJB
    private ApplicantFacade applicantFacade;
    
    @ManagedProperty(value="#{frontManager}") 
    FrontManager frontManager;
    
    private SubmissionException transactionFailure;
    private SubmissionException OldTransactionFailure;
    
    private List<CompetenceDTO>    competences = new ArrayList<>();
    private List<String>    competence_names_eng = new ArrayList<>();
    private List<String>    competence_names_alang = new ArrayList<>();
    
    public String active_language = "";
    private static Map<String,Object> languages;

    @PostConstruct
    private void init() 
    {
        try {
            //applicantFacade.testMethod();
        }catch (SubmissionException e) {
            transactionFailure = e;}
        switchCompetence ();
        languages = new LinkedHashMap<String,Object>();
        languages.put("English", Locale.ENGLISH);
        languages.put("French", Locale.FRENCH);
    }
    
    
    /**
     * Switch out competence names in frontManager to which ever language is
     * currently active.
     */
    public void switchCompetence ()
    {
        try{
            competences = applicantFacade.getCompetences();
            frontManager.setCompetences(competences);
        }catch (SubmissionException e) {
            transactionFailure = e;
        }
        
        switch (active_language){             
            
            case ("French"):
                competence_names_alang = new ArrayList<>();
                for (int i=0; i < competences.size (); i++)
                    competence_names_alang.add (competences.get(i).getFr_name());
                frontManager.setCompetence_names(competence_names_alang);
                break;
            
            default:
                competence_names_eng = new ArrayList<>();
                competence_names_alang = new ArrayList<>();
                
                for (int i=0; i < competences.size (); i++)
                    competence_names_eng.add (competences.get(i).getName());
                for (int i=0; i < competences.size (); i++)
                    competence_names_alang.add (competences.get(i).getName());
                frontManager.setCompetence_names(competence_names_eng);
                break;
        }
    }
    
    /**
     * Event listner changes active language and call change language methods
     * @param event 
     * @throws java.io.IOException 
     */
    public void changeLanguage (ValueChangeEvent event) throws IOException 
    {    
        active_language = (String) event.getNewValue();
        switchCompetence ();
        
        for (Map.Entry<String, Object> entry : languages.entrySet()) {
            if(entry.getValue().toString().equals(active_language)){
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());         
            }
        }
    }
    
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
    /***
     *@return competences 
     */
    public List<CompetenceDTO> getCompetences() {
        return competences;
    }
    /**
     * @param competences sets a new competences
     */
    public void setCompetences(List<CompetenceDTO> competences) {
        this.competences = competences;
    }
    /**
     *@return competence_names_eng returns competences in english 
     */
    public List<String> getCompetence_names_eng() {
        return competence_names_eng;
    }
    /**
     *@param competence_names_eng sets a new value to competences_names_eng 
     */
    public void setCompetence_names_eng(List<String> competence_names_eng) {
        this.competence_names_eng = competence_names_eng;
    }
    /**
     *@return competence_names_alang 
     */
    public List<String> getCompetence_names_alang() {
        return competence_names_alang;
    }
    /**
     *@param competence_names_alang sets a new value to competence_names_alang 
     */
    public void setCompetence_names_alang(List<String> competence_names_alang) {
        this.competence_names_alang = competence_names_alang;
    }
    /**
     *@return active_language
     */
    public String getActive_language() {
        return active_language;
    }
    /**
     *@param active_language sets a new value to active_language 
     */
    public void setActive_language(String active_language) {
        this.active_language = active_language;
    } 
   /**
     *@return frontManager 
     */
    public FrontManager getFrontManager() {
        return frontManager;
    }
    /**
     *@param frontManager sets a new value to frontManager 
     */
    public void setFrontManager(FrontManager frontManager) {
        this.frontManager = frontManager;
    }

    public Map<String, Object> getLanguages() {
        return languages;
    }
}
