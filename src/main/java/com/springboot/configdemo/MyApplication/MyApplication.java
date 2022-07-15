package com.springboot.configdemo.MyApplication;

import com.springboot.configdemo.MyApplication.fullstack.email.EmailSendService;
import com.springboot.configdemo.MyApplication.fullstack.emerald.EmeraldRepo;
import com.springboot.configdemo.MyApplication.ui.TestMarkL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {
	@Autowired
	private Config myConfig;

	@Autowired
	private BookMenu menu;

//	@Autowired
//	private EmeraldRepo emeraldRepo;

//	@Autowired
//	private EmailSendService emailSendService;


	public static void main(String[] args) throws IOException {
		SpringApplication.run(MyApplication.class, args);

//		TestMarkL testMarkL = new TestMarkL();
//		testMarkL.getTest2();


	}

	public void run(String... args) throws Exception {
		System.out.println("using environment: " + myConfig.getEnvironment());
		System.out.println("name: " + myConfig.getName());
		System.out.println("host: " + myConfig.getHost());
		System.out.println("port: " + myConfig.getPort());

//		BooksRepo booksRepo = new BooksRepo();
//		System.out.println(booksRepo.getList());

//		BookJ Menu : to Acess BookJ Manager Services
//		menu.bookMenu();

//		ObjectMapper mapper = new ObjectMapper();
//		List<Book> bookList = mapper.readValue(booksRepo.getList(), new TypeReference<List<Book>>(){});
//		System.out.println(bookList);
//		System.out.println(emeraldRepo.getBookList());
//		emeraldRepo.getTest();
//		emeraldRepo.putTest();
//		emailSendService.sendSimpleEmail("cocxth1301@gmail.com", "Email Subject", "Email Body");
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8081");
			}
		};
	}
}
