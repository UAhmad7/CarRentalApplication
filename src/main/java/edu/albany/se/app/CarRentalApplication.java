package edu.albany.se.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class CarRentalApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CarRentalApplication.class, args);
//		SpringApplication app = new SpringApplication(CarRentalApplication.class);
//		app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
//		app.run(args);
	}
}
