package com.foomoo.aprs.aprsparser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.foomoo.aprs.aprsparser.datasource.AbstractAPRSDataSource;
import com.foomoo.aprs.aprsparser.datasource.IAPRSDataSource;
import com.foomoo.aprs.aprsparser.datasource.SocketAPRSDataSource;

@Configuration
public class SocketCaptureApp {

	@Bean public SocketAPRSDataSource dataSource() {
		return new SocketAPRSDataSource();
	}
	
	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext(
			//	"socketCapture.xml");

		//AbstractAPRSDataSource dataSource = context.getBean("dataSource",
			//	AbstractAPRSDataSource.class);
		//dataSource.run();
		SocketCaptureApp app = new SocketCaptureApp();
		
		AbstractAPRSDataSource dataSource = app.dataSource();
		
		dataSource.run();
	}
}
