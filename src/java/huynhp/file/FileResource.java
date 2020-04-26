/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynhp.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Admin
 */
public class FileResource implements Serializable
{
    private Map<String, String> nameAndPath;

    public FileResource() 
    {
        
    }

    /**
     * @return the nameAndPath
     */
    public Map<String, String> getNameAndPath() 
    {
        return nameAndPath;
    }

    public FileResource(Map<String, String> nameAndPath) 
    {
        this.nameAndPath = nameAndPath;
    }
    public void loadFile(String filename, HttpServletRequest req) throws FileNotFoundException, IOException
    {
        String aPath = req.getServletContext().getRealPath("/");
        
        File file = new File(aPath + filename);
        FileReader fr = null;
        BufferedReader br = null;
        
        try
        {
            if (file.exists())
            {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                String line = "";
                while ( (line = br.readLine()) != null )
                {
                    StringTokenizer stk = new StringTokenizer(line, "-");
                    String action = stk.nextToken().trim();
                    String path = stk.nextToken().trim();
                    
                    if (this.nameAndPath == null)
                    {
                        this.nameAndPath = new HashMap<>();
                    }
                    this.nameAndPath.put(action, path);
                }
            }
        }
        finally
        {
            if (br != null)
            {
                br.close();
            }
            if (fr != null)
            {
                fr.close();
            }
        }
        
    }
    
}
