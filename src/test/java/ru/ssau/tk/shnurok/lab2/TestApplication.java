package ru.ssau.tk.shnurok.lab2;

import org.springframework.boot.SpringApplication;

public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.from(ShnurokApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
