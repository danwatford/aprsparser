package com.foomoo.aprs.aprsparser.datasource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileAPRSDataSource extends AbstractAPRSDataSource {

  private File fFile;

  public void setFile(File aFile) {fFile = aFile;}

  @Override
  public void run()
  {
    if ((null != fFile) && fFile.exists())
    {
      BufferedReader bufferedReader = null;
      try
      {
        FileInputStream fis = new FileInputStream(fFile);
        InputStreamReader isr = new InputStreamReader(fis);
        bufferedReader = new BufferedReader(isr);
        
        String readLine = bufferedReader.readLine();
        while (null != readLine)
        {
          fireDataReceived(readLine);
          readLine = bufferedReader.readLine();
        }
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      finally 
      {
    	  if (bufferedReader != null) {
    		  try {
    			  bufferedReader.close();
    		  } catch (IOException e) {
    			  e.printStackTrace();
    		  }
    	  }
      }
    }
  }
}
