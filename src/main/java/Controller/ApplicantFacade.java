/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Person;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hikari
 */
@Stateful
public class ApplicantFacade {
    
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;
   
    private Person person;
}