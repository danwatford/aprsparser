package com.foomoo.aprs.aprsparser.datasource;

public interface IAPRSDataSource {
  void addAPRSDataSourceListener(IAPRSDataSourceListener aListener);
  void removeAPRSDataSourceListener(IAPRSDataSourceListener aListener);
}
