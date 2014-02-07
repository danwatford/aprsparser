package com.foomoo.aprs.aprsparser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.foomoo.aprs.aprsparser.datasource.AbstractAPRSDataSource;

public class FileSourcedDecoderApp {

  public static void main(String[] args)
  {
    ApplicationContext context = new ClassPathXmlApplicationContext(
        "fileSourcedAPRSDecoder.xml");

    AbstractAPRSDataSource dataSource = context.getBean("dataSource", AbstractAPRSDataSource.class);
    dataSource.run();
  }
}
