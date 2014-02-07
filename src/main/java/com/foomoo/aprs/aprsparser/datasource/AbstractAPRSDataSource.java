package com.foomoo.aprs.aprsparser.datasource;

import java.util.Collection;
import java.util.LinkedHashSet;


public abstract class AbstractAPRSDataSource implements IAPRSDataSource {

  private Collection<IAPRSDataSourceListener> fAPRSISDataListeners = new LinkedHashSet<IAPRSDataSourceListener>();
  
  public abstract void run();

  @Override
  public void addAPRSDataSourceListener(IAPRSDataSourceListener aListener)
  {
    fAPRSISDataListeners.add(aListener);
  }

  @Override
  public void removeAPRSDataSourceListener(IAPRSDataSourceListener aListener)
  {
    fAPRSISDataListeners.remove(aListener);
  }

  protected void fireDataReceived(String aDataReceived)
  {
    for (IAPRSDataSourceListener aprsisDataListener : fAPRSISDataListeners)
    {
      aprsisDataListener.dataReceived(aDataReceived);
    }
  }
}
