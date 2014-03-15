/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Hikari
 * entity class for credentials
 */
@Entity
public class Credential implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String userName;
    private String passWord;
    
    @OneToOne(mappedBy="credential",cascade=ALL)
    private Authentication_Table    auth_table;
    
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = sha256(passWord);
    }

    public Authentication_Table getAuth_table() {
        return auth_table;
    }

    public void setAuth_table(Authentication_Table auth_table) {
        auth_table.setCredential(this);
        this.auth_table = auth_table;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
        public static String sha256(String base) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(base.getBytes("UTF-8"));
            byte[] digest = md.digest();
            return (Base64.encode(digest)).toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
