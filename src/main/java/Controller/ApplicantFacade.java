/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hikari
 */
@Stateless
public class ApplicantFacade {
    
    @PersistenceContext(unitName = "com.mycompany_DOGA-Project_war_1.0-SNAPSHOTPU")
    private EntityManager em;
   
}