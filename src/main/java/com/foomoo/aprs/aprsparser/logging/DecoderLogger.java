package com.foomoo.aprs.aprsparser.logging;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foomoo.aprs.aprsparser.parser.IAPRSParser.APRSUnknownFormatException;
import com.foomoo.aprs.aprsparser.parser.IAPRSParser.APRSUnsupportedFormatException;

public class DecoderLogger{

  public void logDecodeUnsupported(JoinPoint jp, APRSUnsupportedFormatException ex)
  {
    Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());
    logger.debug(null, ex);
  }

  public void logDecodeUnknown(JoinPoint jp, APRSUnknownFormatException ex)
  {
    Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());
    logger.info(null, ex);
  }
}
