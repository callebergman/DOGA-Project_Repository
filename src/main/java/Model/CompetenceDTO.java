/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigInteger;
import java.util.Collection;

/**
 *
 * @author Hikari
 */
public interface CompetenceDTO 
{
    String getName();
    
    BigInteger getCompetence_id();
        
    Collection<Competence_profile> getCompetence_profiles();
}
