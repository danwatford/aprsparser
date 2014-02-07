package com.foomoo.aprs.aprsparser.parser;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.foomoo.aprs.aprsparser.item.IAPRSItem;

public class BasicAPRSParser implements IAPRSParser {

  private static List<Character> SUPPORTED_APRS_DATA_TYPES = Arrays
      .asList(new Character[] { '!', '/', '@', '=' });

  private static List<Character> UNSUPPORTED_APRS_DATA_TYPES = Arrays
      .asList(new Character[] { 0x1c, 0x1d, '"', '#', '$', '%', '%', '\'', '(',
          ')', '*', '+', ',', '.', ':', ';', '<', '>', '?', 'T', '[', '^', '_',
          '`', '{', '|', '}', '~' });

  @Override
  public IAPRSItem parse(final String aSourceString)
      throws APRSUnsupportedFormatException, APRSUnknownFormatException
  {
    String[] callsignSplit = aSourceString.split(">", 2);
    if (2 == callsignSplit.length)
    {
      String callsign = callsignSplit[0];
      String[] pathDataSplit = callsignSplit[1].split(":", 2);
      if (2 == pathDataSplit.length)
      {
        String data = pathDataSplit[1];

        // Check for an APRS data type as the first part of the data. If none
        // found then check for the ! data type in the first 40 characters of
        // the data.
        char dataType = data.charAt(0);
        if (!SUPPORTED_APRS_DATA_TYPES.contains(dataType)
            && !UNSUPPORTED_APRS_DATA_TYPES.contains(dataType))
        {
          int index = data.indexOf('!');
          if ((index >= 0) && (index < 40))
          {
            data = data.substring(index);
            dataType = data.charAt(0);
          }
        }

        String latitudeString = null;
        String longitudeString = null;

        switch (dataType)
        {
        case '!':
          // Position without timestamp (no APRS messaging)
          latitudeString = data.substring(1, 9);
          longitudeString = data.substring(10, 19);
          break;

        case '=':
          // Position without timestamp (with APRS messaging)
          break;

        case '/':
          // Position with timestamp (no APRS messaging)
          latitudeString = data.substring(8, 16);
          longitudeString = data.substring(17, 26);
          break;

        case '@':
          // Position with timestamp (with APRS messaging)
          break;

        default:
          if (UNSUPPORTED_APRS_DATA_TYPES.contains(dataType))
          {
            throw new APRSUnsupportedFormatException("Data Type: " + dataType
                + " in source string: " + aSourceString);
          }
          else
          {
            throw new APRSUnknownFormatException(aSourceString);
          }
        }

        Item item = new Item(aSourceString, callsign, dataType);

        if ((null != latitudeString) && (null != longitudeString))
        {
          try
          {
            item.setLocation(
                convertLatitudeDegreesDecimalMinutes(latitudeString),
                convertLongitudeDegreesDecimalMinutes(longitudeString));
          }
          catch (NumberFormatException e)
          {
            throw new APRSUnknownFormatException(aSourceString, e);
          }
          return item;
        }
      }
    }

    return null;
  }

  private double convertLatitudeDegreesDecimalMinutes(String aLatDDMString)
  {
    return convertDegreesDecimalMinutes("0"
        + aLatDDMString.substring(0, aLatDDMString.length() - 1));
  }

  private double convertLongitudeDegreesDecimalMinutes(String aLongDDMString)
  {
    return convertDegreesDecimalMinutes(aLongDDMString.substring(0,
        aLongDDMString.length() - 1));
  }

  private double convertDegreesDecimalMinutes(String aDDMString)
  {
    String degreesString = aDDMString.substring(0, 3);
    double degrees = Double.valueOf(degreesString);

    String minutesDecimalString = aDDMString.substring(3);

    // Address any ambiguity in the string.
    int spaceIndex = minutesDecimalString.indexOf(' ');
    switch (spaceIndex)
    {
    case 0:
      minutesDecimalString = "0";
      break;

    case 1:
      minutesDecimalString = minutesDecimalString.substring(0, 1) + "0";
      break;
    }
    double minutes = Double.valueOf(minutesDecimalString);

    return degrees + (minutes / 60);
  }

  private static class Item implements IAPRSItem {
    private String fSourceString;
    private String fCallsign;
    private char fDataType;

    private double fLatitude;
    private double fLongitude;

    private boolean fHasLocation;

    public Item(String aSourceString, String aCallsign, char aDataType) {
      fSourceString = aSourceString;
      fCallsign = aCallsign;
      fDataType = aDataType;
    }

    @Override
    public String getCallsign()
    {
      return fCallsign;
    }

    @Override
    public char getDataType()
    {
      return fDataType;
    }

    @Override
    public String getSourceString()
    {
      return fSourceString;
    }

    @Override
    public boolean hasLocation()
    {
      return fHasLocation;
    }

    @Override
    public boolean hasTimestamp()
    {
      return false;
    }

    public void setLocation(double aLatitude, double aLongitude)
    {
      fLatitude = aLatitude;
      fLongitude = aLongitude;
      fHasLocation = true;
    }

    @Override
    public double getLatitude()
    {
      return fLatitude;
    }

    @Override
    public double getLongitude()
    {
      return fLongitude;
    }

    @Override
    public Date getTimestamp()
    {
      return null;
    }
  }
}
