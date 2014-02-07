package com.foomoo.aprs.aprsparser.demo;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.foomoo.aprs.aprsparser.item.IAPRSItem;
import com.foomoo.aprs.aprsparser.item.IAPRSItemSource;
import com.foomoo.aprs.aprsparser.parser.IAPRSItemSourceListener;

public class LocationWriterTest {

  private static String NEWLINE = System.getProperty("line.separator");

  private IAPRSItem fAPRSItem = new IAPRSItem() {

    @Override
    public String getCallsign()
    {
      return "CALLSIGN";
    }

    @Override
    public String getSourceString()
    {
      return "SOURCESTRING";
    }

    @Override
    public char getDataType()
    {
      return '@';
    }

    @Override
    public boolean hasLocation()
    {
      return false;
    }

    @Override
    public boolean hasTimestamp()
    {
      return false;
    }

    @Override
    public double getLatitude()
    {
      return 0;
    }

    @Override
    public double getLongitude()
    {
      return 0;
    }

    @Override
    public Date getTimestamp()
    {
      return null;
    }

  };

  @Test
  public void testDecoded()
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    final ArrayList<IAPRSItemSourceListener> listeners = new ArrayList<IAPRSItemSourceListener>();
    IAPRSItemSource source = new IAPRSItemSource() {
      @Override
      public void removeListener(IAPRSItemSourceListener aListener)
      {
      }
      
      @Override
      public void addListener(IAPRSItemSourceListener aListener)
      {
        listeners.add(aListener);
      }
    };
    
    LocationWriter locationWriter = new LocationWriter();
    locationWriter.setOutputStream(baos);
    locationWriter.setAPRSItemSource(source);
    
    assertEquals(1, listeners.size());
    
    listeners.get(0).item(fAPRSItem);
    assertEquals("CALLSIGN @ (SOURCESTRING)" + NEWLINE, baos.toString());
  }
}
