package com.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@InitBinder
	private void activateDirectFieldAccess(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
