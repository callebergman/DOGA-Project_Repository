/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Fredrik
 * Log class handles the log writing to a file
 */
public class Log
{
    /****
     *@param id is an identifier to see who wrote what.
     * @param content shows the action that the person did.
     */
    public void writetofile(String id, String content) throws IOException
    {
       try {
            
            FileWriter fstream = new FileWriter("logfile.txt",true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(id+" "+ content);
            out.newLine();
            out.close();
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
