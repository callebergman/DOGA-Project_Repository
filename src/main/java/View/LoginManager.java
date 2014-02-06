/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Controller.ApplicantFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Hikari
 */
@ManagedBean
@RequestScoped
public class LoginManager {

    private String userName;
    private String passwd;
    
    private String transactionFailure;
    
    /**
     * Creates a new instance of LoginManager
     */
    public LoginManager() {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getTransactionFailure() {
        return transactionFailure;
    }

    public void setTransactionFailure(String transactionFailure) {
        this.transactionFailure = transactionFailure;
    }
    
    
}
