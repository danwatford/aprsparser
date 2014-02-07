package com.foomoo.aprs.aprsparser.item;

import com.foomoo.aprs.aprsparser.parser.IAPRSItemSourceListener;

public interface IAPRSItemSource {
  void addListener(IAPRSItemSourceListener aListener);
  void removeListener(IAPRSItemSourceListener aListener);
}
