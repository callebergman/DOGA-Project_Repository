/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Hikari
 */
public class Competence_profileDTO
{
     private int years_of_experience;
     public Competence_profileDTO(int years_of_experience)
     {
         this.years_of_experience = years_of_experience;
     }

    /**
     * @return the years_of_experience
     */
    public int getYears_of_experience() {
        return years_of_experience;
    }

    /**
     * @param years_of_experience the years_of_experience to set
     */
    public void setYears_of_experience(int years_of_experience) {
        this.years_of_experience = years_of_experience;
    }
}
