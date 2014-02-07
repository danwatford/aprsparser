package com.foomoo.aprs.aprsparser.item;

import java.util.ArrayList;

import com.foomoo.aprs.aprsparser.datasource.IAPRSDataSource;
import com.foomoo.aprs.aprsparser.datasource.IAPRSDataSourceListener;
import com.foomoo.aprs.aprsparser.parser.IAPRSItemSourceListener;
import com.foomoo.aprs.aprsparser.parser.IAPRSParser;
import com.foomoo.aprs.aprsparser.parser.IAPRSParser.APRSUnknownFormatException;
import com.foomoo.aprs.aprsparser.parser.IAPRSParser.APRSUnsupportedFormatException;

public class APRSItemSource implements IAPRSItemSource{

  private IAPRSDataSource fDataSource;
  private IAPRSParser fAPRSDecoder;
  
  private ArrayList<IAPRSItemSourceListener> fListeners = new ArrayList<IAPRSItemSourceListener>();

  @Override
  public void addListener(IAPRSItemSourceListener aListener)
  {
    fListeners.add(aListener);
  }

  @Override
  public void removeListener(IAPRSItemSourceListener aListener)
  {
    fListeners.remove(aListener);
  }

  private IAPRSDataSourceListener fDataSourceListener = new IAPRSDataSourceListener() {
    @Override
    public void dataReceived(String aData)
    {
      processReceivedData(aData);
    }
  };

  public void setAPRSDataSource(IAPRSDataSource aAPRSDataSource)
  {
    if (null != fDataSource)
    {
      fDataSource.removeAPRSDataSourceListener(fDataSourceListener);
    }

    fDataSource = aAPRSDataSource;

    if (null != fDataSource)
    {
      fDataSource.addAPRSDataSourceListener(fDataSourceListener);
    }
  }

  public void setAPRSDecoder(IAPRSParser aAPRSDecoder)
  {
    fAPRSDecoder = aAPRSDecoder;
  }

  private void processReceivedData(String aReadLine)
  {
    IAPRSItem decodedItem;
    try
    {
      decodedItem = fAPRSDecoder.parse(aReadLine);
      if (null != decodedItem)
      {
        for (IAPRSItemSourceListener listener : fListeners)
        {
          listener.item(decodedItem);
        }
      }
    }
    catch (APRSUnknownFormatException e)
    {
    }
    catch (APRSUnsupportedFormatException e)
    {
    }
  }
}
