package com.foomoo.aprs.aprsparser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.foomoo.aprs.aprsparser.datasource.AbstractAPRSDataSource;

public class SocketSourcedDecoderApp {

  public static void main(String[] args)
  {
    ApplicationContext context = new ClassPathXmlApplicationContext(
        "socketSourcedAPRSDecoder.xml");

    AbstractAPRSDataSource dataSource = context.getBean("dataSource", AbstractAPRSDataSource.class);
    dataSource.run();
  }
}
