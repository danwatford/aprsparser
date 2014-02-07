package com.foomoo.aprs.aprsparser.datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class DataSourceCapture {

  private PrintStream fPrintStream;
  private IAPRSDataSource fAPRSDataSource;

  private IAPRSDataSourceListener fAPRSDataSourceListener = new IAPRSDataSourceListener() {
    @Override
    public void dataReceived(String aData)
    {
      processDataReceived(aData);
    }
  };

  public void setAPRSDataSource(IAPRSDataSource aAPRSDataSource)
  {
    if (null != fAPRSDataSource)
    {
      fAPRSDataSource.removeAPRSDataSourceListener(fAPRSDataSourceListener);
    }

    fAPRSDataSource = aAPRSDataSource;

    if (null != fAPRSDataSource)
    {
      fAPRSDataSource.addAPRSDataSourceListener(fAPRSDataSourceListener);
    }
  }

  public void setOutputFile(File aOutputFile) throws FileNotFoundException
  {
    fPrintStream = new PrintStream(aOutputFile);
  }

  private void processDataReceived(String aData)
  {
    if (null != fPrintStream)
    {
      fPrintStream.println(aData);
    }
  }
}
