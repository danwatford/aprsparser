package com.foomoo.aprs.aprsparser.demo;

import java.io.OutputStream;
import java.io.PrintStream;

import com.foomoo.aprs.aprsparser.item.IAPRSItem;
import com.foomoo.aprs.aprsparser.item.IAPRSItemSource;
import com.foomoo.aprs.aprsparser.parser.IAPRSItemSourceListener;

public class LocationWriter {

  private PrintStream fPrintStream;
  private IAPRSItemSource fAPRSItemSource;

  private IAPRSItemSourceListener fAPRSItemSourceListener = new IAPRSItemSourceListener() {
    @Override
    public void item(IAPRSItem aAPRSItem)
    {
      processItem(aAPRSItem);
    }
  };
  
  
  public void setAPRSItemSource(IAPRSItemSource aAPRSItemSource)
  {
    if (null != fAPRSItemSource)
    {
      fAPRSItemSource.removeListener(fAPRSItemSourceListener);
    }
    
    fAPRSItemSource = aAPRSItemSource;
    
    if (null != fAPRSItemSource)
    {
      fAPRSItemSource.addListener(fAPRSItemSourceListener);
    }
  }
  
  public void setOutputStream(OutputStream aOutputStream)
  {
    fPrintStream = new PrintStream(aOutputStream);
  }

  private void processItem(IAPRSItem aAPRSItem)
  {
    if (null != fPrintStream)
    {
      fPrintStream.print(aAPRSItem.getCallsign());
      fPrintStream.print(" ");
      fPrintStream.print(aAPRSItem.getDataType());

      if (aAPRSItem.hasLocation())
      {
        fPrintStream.print(" Long: ");
        fPrintStream.print(aAPRSItem.getLongitude());
        fPrintStream.print(" Lat: ");
        fPrintStream.print(aAPRSItem.getLatitude());
      }

      fPrintStream.print(" (");
      fPrintStream.print(aAPRSItem.getSourceString());
      fPrintStream.println(")");
    }
  }
}
