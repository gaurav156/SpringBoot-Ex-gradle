package com.springboot.configdemo.MyApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {
	@Autowired
	private Config myConfig;

	@Autowired
	private BookMenu menu;

//	@Autowired
//	private BooksRepo booksRepo;


	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);

//		TestMarkL testMarkL = new TestMarkL();
//		testMarkL.getTest();


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
