package com.foomoo.aprs.aprsparser.parser;

import com.foomoo.aprs.aprsparser.item.IAPRSItem;

public interface IAPRSParser {
  IAPRSItem parse(String aSourceString) throws APRSUnknownFormatException,
      APRSUnsupportedFormatException;

  public class APRSUnknownFormatException extends Exception {
    private static final long serialVersionUID = -14531111149794582L;
    public APRSUnknownFormatException(String aMessage) {super(aMessage);}
    public APRSUnknownFormatException(String aMessage, Throwable aThrowable) {super(aMessage, aThrowable);}
  }

  public class APRSUnsupportedFormatException extends Exception {
    private static final long serialVersionUID = -5527533345557547500L;
    public APRSUnsupportedFormatException(String aMessage) {super(aMessage);}
  }
}
