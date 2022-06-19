package com.springboot.configdemo.MyApplication;

import com.springboot.configdemo.MyApplication.fullstack.customer.CustomerRepo;
import com.springboot.configdemo.MyApplication.ui.TestMarkL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {
	@Autowired
	private Config myConfig;

	@Autowired
	private BookMenu menu;


	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);

//		TestMarkL testMarkL = new TestMarkL();
//		testMarkL.getTest();

//		CustomerRepo customerRepo = new CustomerRepo();
//		customerRepo.getList();
	}

	public void run(String... args) throws Exception {
		System.out.println("using environment: " + myConfig.getEnvironment());
		System.out.println("name: " + myConfig.getName());
		System.out.println("host: " + myConfig.getHost());
		System.out.println("port: " + myConfig.getPort());

//		Book Menu : to Acess Book Manager Services
//		menu.bookMenu();
	}
}
