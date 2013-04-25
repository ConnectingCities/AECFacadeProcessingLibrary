package org.aec.facade;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

class FileHandler
{

    public FileHandler()
    {
        URL userDir = getClass().getProtectionDomain().getCodeSource().getLocation();
        path = userDir.getPath();
        path = path.replaceAll("\\%20", " ");
        int index = path.lastIndexOf("/");
        path = path.substring(0, index);
        path.replace("%20", " ");
    }

    public String[] load()
    {
        ArrayList output = new ArrayList();
        System.out.println("Try to read User CONFIG from");
        String filename = (new StringBuilder(String.valueOf(path))).append("/../data/").append("config_user.ini").toString();
    	System.out.println(filename);        
        try
        {
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = br.readLine()) != null) 
                output.add(line);
            in.close();
        }
        catch(Exception e)
        {
            System.out.println("USER CONFIG NOT FOUND - USE DEFAULT CONFIG INSTEAD");
            return null;
        }
        String ret[] = new String[output.size()];
        output.toArray(ret);
        return ret;
    }

    public void save(String text)
    {
        try
        {
            FileWriter fstream = new FileWriter((new StringBuilder(String.valueOf(path))).append("\\..").append("/config_user.ini").toString());
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(text);
            out.close();
        }
        catch(Exception e)
        {
            System.err.println((new StringBuilder("Error: ")).append(e.getMessage()).toString());
        }
    }

    private String path;
}
