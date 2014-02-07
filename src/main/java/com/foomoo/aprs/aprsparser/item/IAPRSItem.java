package com.foomoo.aprs.aprsparser.item;

import java.util.Date;

public interface IAPRSItem {
  String getSourceString();

  String getCallsign();

  char getDataType();

  boolean hasTimestamp();

  boolean hasLocation();

  double getLongitude();

  double getLatitude();

  Date getTimestamp();
}
