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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
     * @throws java.io.IOException
     */
    public void writetofile(String id, String content) throws IOException
    {
       try {
            
             DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            FileWriter fstream = new FileWriter("logfile.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(dateFormat.format(cal.getTime()) + " " + id + " " + content);
            out.newLine();
            out.close();
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
