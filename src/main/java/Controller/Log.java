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
 */
public class Log
{
    public void writetofile(String name, String content) throws IOException
    {
        File file = new File("filelog.txt");
        String text = name+": "+content;
        
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
