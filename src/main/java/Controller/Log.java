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
     * @param content shows what the person did.
     */
    public void writetofile(String id, String content) throws IOException
    {
        File file = new File("filelog.txt");
        String text = id+": "+content;
        
        if(!file.exists())
        {
            file.createNewFile();
        }
        FileWriter filewriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter buff=new BufferedWriter(filewriter);
        buff.write(text);
        buff.close();
    }
}
