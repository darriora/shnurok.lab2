package ru.ssau.tk.shnurok.lab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ShnurokApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShnurokApplication.class, args);
	}

}
