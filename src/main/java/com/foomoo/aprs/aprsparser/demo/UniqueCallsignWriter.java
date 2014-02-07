package com.foomoo.aprs.aprsparser.demo;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashSet;

import com.foomoo.aprs.aprsparser.item.IAPRSItem;
import com.foomoo.aprs.aprsparser.item.IAPRSItemSource;
import com.foomoo.aprs.aprsparser.parser.IAPRSItemSourceListener;

public class UniqueCallsignWriter {

  private HashSet<String> fCallsigns = new HashSet<String>();
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

  public void processItem(IAPRSItem aAPRSItem)
  {
    if (null != fPrintStream)
    {
      if (fCallsigns.add(aAPRSItem.getCallsign()))
      {
        fPrintStream.println(aAPRSItem.getCallsign());
      }
    }
  }
}
