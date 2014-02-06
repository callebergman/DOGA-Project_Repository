/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Controller.ApplicantFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Calle
 */
@ManagedBean
@SessionScoped
public class FrontManager {
    
    @EJB
    private ApplicantFacade applicantFacade;
    
    private String name;
    private String lastName;    
    private String email;
    private String[] areas = new String[10];
    
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
    
    
}
